package com.zheng.seckill.common.util;

import com.zheng.seckill.common.constant.SeckillConstants;
import com.zheng.seckill.common.dto.SeckillMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.Serializable;

/**
 * 消息队列工具类，用于秒杀异步处理
 */
@Component
public class MessageQueueUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(MessageQueueUtil.class);
    
    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 发送秒杀消息到队列
     * @param messageDTO 秒杀消息
     */
    public void sendSeckillMessage(SeckillMessageDTO messageDTO) {
        try {
            jmsTemplate.send(SeckillConstants.SECKILL_MESSAGE_QUEUE, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createObjectMessage(messageDTO);
                }
            });
            logger.info("发送秒杀消息成功，userId: {}, goodsId: {}", messageDTO.getUserId(), messageDTO.getGoodsId());
        } catch (Exception e) {
            logger.error("发送秒杀消息失败，userId: {}, goodsId: {}", messageDTO.getUserId(), messageDTO.getGoodsId(), e);
            throw new RuntimeException("发送秒杀消息失败", e);
        }
    }

    /**
     * 发送消息到指定队列
     * @param queueName 队列名称
     * @param message 消息内容
     */
    public void sendMessage(String queueName, final Serializable message) {
        try {
            jmsTemplate.send(queueName, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createObjectMessage(message);
                }
            });
            logger.info("发送消息成功，queueName: {}", queueName);
        } catch (Exception e) {
            logger.error("发送消息失败，queueName: {}", queueName, e);
            throw new RuntimeException("发送消息失败", e);
        }
    }

    /**
     * 发送延迟消息
     * @param queueName 队列名称
     * @param message 消息内容
     * @param delay 延迟时间（毫秒）
     */
    public void sendDelayMessage(String queueName, final Serializable message, final long delay) {
        try {
            jmsTemplate.send(queueName, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    Message msg = session.createObjectMessage(message);
                    msg.setLongProperty("AMQ_SCHEDULED_DELAY", delay);
                    return msg;
                }
            });
            logger.info("发送延迟消息成功，queueName: {}, delay: {}ms", queueName, delay);
        } catch (Exception e) {
            logger.error("发送延迟消息失败，queueName: {}, delay: {}ms", queueName, delay, e);
            throw new RuntimeException("发送延迟消息失败", e);
        }
    }
}
