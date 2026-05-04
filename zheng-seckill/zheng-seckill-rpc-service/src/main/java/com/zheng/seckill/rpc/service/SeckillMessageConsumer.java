package com.zheng.seckill.rpc.service;

import com.zheng.common.util.RedisUtil;
import com.zheng.pay.dao.model.PayInOrder;
import com.zheng.pay.rpc.api.PayInOrderService;
import com.zheng.seckill.common.constant.SeckillConstants;
import com.zheng.seckill.common.dto.SeckillMessageDTO;
import com.zheng.seckill.dao.mapper.SeckillGoodsMapper;
import com.zheng.seckill.dao.mapper.SeckillOrderMapper;
import com.zheng.seckill.dao.model.SeckillGoods;
import com.zheng.seckill.dao.model.SeckillOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.ObjectMessage;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 秒杀消息消费者
 * 用于异步处理秒杀下单逻辑
 * 包含创建秒杀订单、调用支付中心、订单状态更新等完整流程
 */
@Component
public class SeckillMessageConsumer {
    
    private static final Logger logger = LoggerFactory.getLogger(SeckillMessageConsumer.class);
    
    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;
    
    @Autowired
    private SeckillOrderMapper seckillOrderMapper;
    
    @Autowired(required = false)
    private PayInOrderService payInOrderService;
    
    private static final int MAX_RETRY_COUNT = 3;

    @JmsListener(destination = SeckillConstants.SECKILL_MESSAGE_QUEUE)
    @Transactional(rollbackFor = Exception.class)
    public void handleSeckillMessage(ObjectMessage message) {
        SeckillMessageDTO messageDTO = null;
        
        try {
            messageDTO = (SeckillMessageDTO) message.getObject();
            logger.info("收到秒杀消息，userId: {}, goodsId: {}, messageId: {}", 
                messageDTO.getUserId(), messageDTO.getGoodsId(), messageDTO.getMessageId());
            
            if (messageDTO.getStatus() == 2) {
                logger.info("消息已处理成功，跳过，messageId: {}", messageDTO.getMessageId());
                return;
            }
            
            boolean success = processSeckillOrder(messageDTO);
            
            if (success) {
                messageDTO.setStatus(2);
                logger.info("秒杀消息处理成功，userId: {}, goodsId: {}", 
                    messageDTO.getUserId(), messageDTO.getGoodsId());
            } else {
                handleFailure(messageDTO);
            }
            
        } catch (Exception e) {
            logger.error("处理秒杀消息失败", e);
            if (messageDTO != null) {
                handleFailure(messageDTO);
            }
        }
    }

    private boolean processSeckillOrder(SeckillMessageDTO messageDTO) {
        try {
            Integer userId = messageDTO.getUserId();
            Long goodsId = messageDTO.getGoodsId();
            
            logger.info("开始处理秒杀下单，userId: {}, goodsId: {}", userId, goodsId);
            
            SeckillOrder existingOrder = seckillOrderMapper.selectByUserIdAndGoodsId(userId, goodsId);
            if (existingOrder != null) {
                logger.info("用户已秒杀成功，跳过重复处理，userId: {}, goodsId: {}, orderId: {}", 
                    userId, goodsId, existingOrder.getId());
                
                String userKey = SeckillConstants.SECKILL_SUCCESS_USER_KEY_PREFIX + goodsId + ":" + userId;
                String userValue = RedisUtil.get(userKey);
                if (userValue == null || userValue.isEmpty()) {
                    RedisUtil.set(userKey, "1", SeckillConstants.SECKILL_STOCK_TIMEOUT);
                }
                return true;
            }
            
            int affectedRows = seckillGoodsMapper.decreaseStock(goodsId, messageDTO.getQuantity());
            if (affectedRows <= 0) {
                logger.warn("数据库库存不足，userId: {}, goodsId: {}", userId, goodsId);
                String stockKey = SeckillConstants.SECKILL_GOODS_STOCK_KEY_PREFIX + goodsId;
                RedisUtil.incr(stockKey);
                return false;
            }
            
            logger.info("创建秒杀订单，userId: {}, goodsId: {}, quantity: {}", 
                userId, goodsId, messageDTO.getQuantity());
            
            SeckillGoods seckillGoods = seckillGoodsMapper.selectByGoodsId(goodsId);
            if (seckillGoods == null) {
                logger.warn("秒杀商品不存在，goodsId: {}", goodsId);
                seckillGoodsMapper.increaseStock(goodsId, messageDTO.getQuantity());
                String stockKey = SeckillConstants.SECKILL_GOODS_STOCK_KEY_PREFIX + goodsId;
                RedisUtil.incr(stockKey);
                return false;
            }
            
            SeckillOrder seckillOrder = createSeckillOrder(messageDTO, seckillGoods);
            int orderResult = seckillOrderMapper.insertSelective(seckillOrder);
            
            if (orderResult <= 0) {
                logger.warn("秒杀订单创建失败，userId: {}, goodsId: {}", userId, goodsId);
                seckillGoodsMapper.increaseStock(goodsId, messageDTO.getQuantity());
                String stockKey = SeckillConstants.SECKILL_GOODS_STOCK_KEY_PREFIX + goodsId;
                RedisUtil.incr(stockKey);
                return false;
            }
            
            logger.info("秒杀订单创建成功，orderId: {}, orderNo: {}", 
                seckillOrder.getId(), seckillOrder.getOrderNo());
            
            PayInOrder payInOrder = null;
            if (payInOrderService != null) {
                payInOrder = createPayOrder(messageDTO, seckillOrder);
            }
            
            if (payInOrder != null && payInOrder.getPayInOrderId() != null) {
                logger.info("支付订单创建成功，payInOrderId: {}", payInOrder.getPayInOrderId());
                
                seckillOrder.setPayInOrderId(payInOrder.getPayInOrderId());
                seckillOrderMapper.updateByPrimaryKeySelective(seckillOrder);
                
                logger.info("秒杀订单回写支付订单ID成功，orderId: {}, payInOrderId: {}", 
                    seckillOrder.getId(), payInOrder.getPayInOrderId());
            } else {
                logger.warn("支付订单创建失败或未配置支付服务，秒杀订单状态保持待支付，orderId: {}", 
                    seckillOrder.getId());
            }
            
            String userKey = SeckillConstants.SECKILL_SUCCESS_USER_KEY_PREFIX + goodsId + ":" + userId;
            RedisUtil.set(userKey, "1", SeckillConstants.SECKILL_STOCK_TIMEOUT);
            
            logger.info("秒杀订单处理完成，userId: {}, goodsId: {}, orderId: {}", 
                userId, goodsId, seckillOrder.getId());
            
            return true;
            
        } catch (Exception e) {
            logger.error("处理秒杀下单失败，userId: {}, goodsId: {}", 
                messageDTO.getUserId(), messageDTO.getGoodsId(), e);
            return false;
        }
    }

    private SeckillOrder createSeckillOrder(SeckillMessageDTO messageDTO, SeckillGoods seckillGoods) {
        SeckillOrder order = new SeckillOrder();
        
        String orderNo = generateOrderNo(messageDTO.getUserId());
        order.setOrderNo(orderNo);
        
        order.setUserId(messageDTO.getUserId());
        order.setGoodsId(messageDTO.getGoodsId());
        order.setSeckillGoodsId(seckillGoods.getId());
        order.setGoodsName(messageDTO.getGoodsName());
        order.setGoodsImage(seckillGoods.getGoodsImage());
        order.setSeckillPrice(messageDTO.getSeckillPrice());
        order.setQuantity(messageDTO.getQuantity());
        
        BigDecimal totalAmount = messageDTO.getSeckillPrice().multiply(
            new BigDecimal(messageDTO.getQuantity()));
        order.setTotalAmount(totalAmount);
        
        order.setStatus(0);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        
        return order;
    }

    private PayInOrder createPayOrder(SeckillMessageDTO messageDTO, SeckillOrder seckillOrder) {
        try {
            logger.info("调用支付中心创建支付订单，userId: {}, goodsId: {}, orderNo: {}", 
                messageDTO.getUserId(), messageDTO.getGoodsId(), seckillOrder.getOrderNo());
            
            PayInOrder payInOrder = new PayInOrder();
            
            BigDecimal price = messageDTO.getSeckillPrice();
            if (price == null) {
                price = BigDecimal.ZERO;
            }
            long amount = price.multiply(new BigDecimal(messageDTO.getQuantity()))
                .multiply(new BigDecimal(100)).longValue();
            
            payInOrder.setAmount(amount);
            payInOrder.setStatus((byte) 0);
            payInOrder.setCtime(System.currentTimeMillis());
            
            int result = payInOrderService.insertSelective(payInOrder);
            
            if (result > 0) {
                logger.info("支付订单创建成功，userId: {}, goodsId: {}, payInOrderId: {}", 
                    messageDTO.getUserId(), messageDTO.getGoodsId(), payInOrder.getPayInOrderId());
                return payInOrder;
            } else {
                logger.warn("支付订单创建失败，userId: {}, goodsId: {}", 
                    messageDTO.getUserId(), messageDTO.getGoodsId());
                return null;
            }
            
        } catch (Exception e) {
            logger.error("调用支付中心创建支付订单失败，userId: {}, goodsId: {}", 
                messageDTO.getUserId(), messageDTO.getGoodsId(), e);
            return null;
        }
    }

    private String generateOrderNo(Integer userId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timestamp = sdf.format(new Date());
        String randomPart = String.format("%04d", (int) (Math.random() * 10000));
        String userIdPart = String.format("%04d", userId % 10000);
        return "SK" + timestamp + userIdPart + randomPart;
    }

    private void handleFailure(SeckillMessageDTO messageDTO) {
        messageDTO.incrementRetryCount();
        
        if (messageDTO.getRetryCount() >= MAX_RETRY_COUNT) {
            messageDTO.setStatus(3);
            logger.error("秒杀消息处理失败，超过最大重试次数，messageId: {}, retryCount: {}", 
                messageDTO.getMessageId(), messageDTO.getRetryCount());
            
            String stockKey = SeckillConstants.SECKILL_GOODS_STOCK_KEY_PREFIX + messageDTO.getGoodsId();
            RedisUtil.incr(stockKey);
            
        } else {
            messageDTO.setStatus(0);
            logger.warn("秒杀消息处理失败，准备重试，messageId: {}, retryCount: {}", 
                messageDTO.getMessageId(), messageDTO.getRetryCount());
        }
    }
}
