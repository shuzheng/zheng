package com.zheng.seckill.rpc.service;

import com.zheng.common.util.RedisUtil;
import com.zheng.pay.dao.model.PayInOrder;
import com.zheng.pay.rpc.api.PayInOrderService;
import com.zheng.seckill.common.constant.SeckillConstants;
import com.zheng.seckill.common.dto.SeckillMessageDTO;
import com.zheng.seckill.dao.mapper.SeckillGoodsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.ObjectMessage;
import java.math.BigDecimal;

/**
 * 秒杀消息消费者
 * 用于异步处理秒杀下单逻辑
 */
@Component
public class SeckillMessageConsumer {
    
    private static final Logger logger = LoggerFactory.getLogger(SeckillMessageConsumer.class);
    
    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;
    
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
            
            String userKey = SeckillConstants.SECKILL_SUCCESS_USER_KEY_PREFIX + goodsId + ":" + userId;
            String userValue = RedisUtil.get(userKey);
            if (userValue != null && !userValue.isEmpty()) {
                logger.info("用户已秒杀成功，跳过重复处理，userId: {}, goodsId: {}", userId, goodsId);
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
            
            boolean payOrderCreated = createPayOrder(messageDTO);
            if (!payOrderCreated) {
                logger.warn("支付订单创建失败，回滚数据库库存，userId: {}, goodsId: {}", userId, goodsId);
                seckillGoodsMapper.increaseStock(goodsId, messageDTO.getQuantity());
                String stockKey = SeckillConstants.SECKILL_GOODS_STOCK_KEY_PREFIX + goodsId;
                RedisUtil.incr(stockKey);
                return false;
            }
            
            RedisUtil.set(userKey, "1", SeckillConstants.SECKILL_STOCK_TIMEOUT);
            
            logger.info("秒杀订单创建成功，userId: {}, goodsId: {}", userId, goodsId);
            
            return true;
            
        } catch (Exception e) {
            logger.error("处理秒杀下单失败，userId: {}, goodsId: {}", 
                messageDTO.getUserId(), messageDTO.getGoodsId(), e);
            return false;
        }
    }

    private boolean createPayOrder(SeckillMessageDTO messageDTO) {
        try {
            if (payInOrderService == null) {
                logger.warn("PayInOrderService未注入，跳过创建支付订单，userId: {}, goodsId: {}", 
                    messageDTO.getUserId(), messageDTO.getGoodsId());
                return true;
            }
            
            logger.info("调用支付中心创建支付订单，userId: {}, goodsId: {}", 
                messageDTO.getUserId(), messageDTO.getGoodsId());
            
            PayInOrder payInOrder = new PayInOrder();
            
            BigDecimal price = messageDTO.getSeckillPrice();
            if (price == null) {
                price = BigDecimal.ZERO;
            }
            long amount = price.multiply(new BigDecimal(100)).longValue();
            
            payInOrder.setAmount(amount);
            payInOrder.setStatus((byte) 0);
            payInOrder.setCtime(System.currentTimeMillis());
            
            int result = payInOrderService.insertSelective(payInOrder);
            
            if (result > 0) {
                logger.info("支付订单创建成功，userId: {}, goodsId: {}, payInOrderId: {}", 
                    messageDTO.getUserId(), messageDTO.getGoodsId(), payInOrder.getPayInOrderId());
                return true;
            } else {
                logger.warn("支付订单创建失败，userId: {}, goodsId: {}", 
                    messageDTO.getUserId(), messageDTO.getGoodsId());
                return false;
            }
            
        } catch (Exception e) {
            logger.error("调用支付中心创建支付订单失败，userId: {}, goodsId: {}", 
                messageDTO.getUserId(), messageDTO.getGoodsId(), e);
            return false;
        }
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
