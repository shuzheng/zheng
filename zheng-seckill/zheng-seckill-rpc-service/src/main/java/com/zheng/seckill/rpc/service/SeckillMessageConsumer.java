package com.zheng.seckill.rpc.service;

import com.zheng.seckill.common.constant.SeckillConstants;
import com.zheng.seckill.common.dto.SeckillMessageDTO;
import com.zheng.seckill.common.util.RedisUtil;
import com.zheng.seckill.dao.mapper.SeckillGoodsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.ObjectMessage;
import java.util.Date;

/**
 * 秒杀消息消费者
 * 用于异步处理秒杀下单逻辑
 */
@Component
public class SeckillMessageConsumer {
    
    private static final Logger logger = LoggerFactory.getLogger(SeckillMessageConsumer.class);
    
    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;
    
    @Autowired
    private RedisUtil redisUtil;
    
    /**
     * 最大重试次数
     */
    private static final int MAX_RETRY_COUNT = 3;

    /**
     * 处理秒杀消息
     * @param message 消息对象
     */
    @JmsListener(destination = SeckillConstants.SECKILL_MESSAGE_QUEUE)
    @Transactional(rollbackFor = Exception.class)
    public void handleSeckillMessage(ObjectMessage message) {
        SeckillMessageDTO messageDTO = null;
        
        try {
            // 1. 获取消息内容
            messageDTO = (SeckillMessageDTO) message.getObject();
            logger.info("收到秒杀消息，userId: {}, goodsId: {}, messageId: {}", 
                messageDTO.getUserId(), messageDTO.getGoodsId(), messageDTO.getMessageId());
            
            // 2. 检查消息状态
            if (messageDTO.getStatus() == 2) {
                logger.info("消息已处理成功，跳过，messageId: {}", messageDTO.getMessageId());
                return;
            }
            
            // 3. 执行秒杀下单逻辑
            boolean success = processSeckillOrder(messageDTO);
            
            if (success) {
                // 4. 标记消息处理成功
                messageDTO.setStatus(2);
                logger.info("秒杀消息处理成功，userId: {}, goodsId: {}", 
                    messageDTO.getUserId(), messageDTO.getGoodsId());
            } else {
                // 5. 处理失败，检查重试次数
                handleFailure(messageDTO);
            }
            
        } catch (Exception e) {
            logger.error("处理秒杀消息失败", e);
            if (messageDTO != null) {
                handleFailure(messageDTO);
            }
        }
    }

    /**
     * 处理秒杀下单逻辑
     * @param messageDTO 秒杀消息
     * @return 是否处理成功
     */
    private boolean processSeckillOrder(SeckillMessageDTO messageDTO) {
        try {
            Integer userId = messageDTO.getUserId();
            Long goodsId = messageDTO.getGoodsId();
            
            logger.info("开始处理秒杀下单，userId: {}, goodsId: {}", userId, goodsId);
            
            // 1. 验证用户是否已经秒杀成功（防止重复处理）
            if (redisUtil.isUserSeckillSuccess(userId, goodsId)) {
                logger.info("用户已秒杀成功，跳过重复处理，userId: {}, goodsId: {}", userId, goodsId);
                return true;
            }
            
            // 2. 扣减数据库库存
            int affectedRows = seckillGoodsMapper.decreaseStock(goodsId, messageDTO.getQuantity());
            if (affectedRows <= 0) {
                logger.warn("数据库库存不足，userId: {}, goodsId: {}", userId, goodsId);
                // 回滚Redis库存
                redisUtil.incrementStock(goodsId);
                return false;
            }
            
            // 3. 创建订单（这里简化处理，实际应该调用订单服务）
            // 实际项目中应该创建订单记录，并调用支付中心
            logger.info("创建秒杀订单，userId: {}, goodsId: {}, quantity: {}", 
                userId, goodsId, messageDTO.getQuantity());
            
            // 4. 标记用户秒杀成功
            redisUtil.markUserSeckillSuccess(userId, goodsId);
            
            // 5. 发送订单创建成功通知（可以通过消息队列或其他方式）
            logger.info("秒杀订单创建成功，userId: {}, goodsId: {}", userId, goodsId);
            
            return true;
            
        } catch (Exception e) {
            logger.error("处理秒杀下单失败，userId: {}, goodsId: {}", 
                messageDTO.getUserId(), messageDTO.getGoodsId(), e);
            return false;
        }
    }

    /**
     * 处理失败消息
     * @param messageDTO 秒杀消息
     */
    private void handleFailure(SeckillMessageDTO messageDTO) {
        messageDTO.incrementRetryCount();
        
        if (messageDTO.getRetryCount() >= MAX_RETRY_COUNT) {
            // 超过最大重试次数，标记为失败
            messageDTO.setStatus(3);
            logger.error("秒杀消息处理失败，超过最大重试次数，messageId: {}, retryCount: {}", 
                messageDTO.getMessageId(), messageDTO.getRetryCount());
            
            // 回滚Redis库存
            redisUtil.incrementStock(messageDTO.getGoodsId());
            
            // 记录失败日志或发送告警
            // 实际项目中可以发送邮件、短信或其他告警方式
        } else {
            // 标记为待处理，可以重新入队
            messageDTO.setStatus(0);
            logger.warn("秒杀消息处理失败，准备重试，messageId: {}, retryCount: {}", 
                messageDTO.getMessageId(), messageDTO.getRetryCount());
        }
    }
}
