package com.zheng.seckill.rpc.service;

import com.zheng.common.util.RedisUtil;
import com.zheng.seckill.common.constant.SeckillConstants;
import com.zheng.seckill.dao.mapper.SeckillGoodsMapper;
import com.zheng.seckill.dao.mapper.SeckillOrderMapper;
import com.zheng.seckill.dao.model.SeckillOrder;
import com.zheng.seckill.rpc.api.SeckillOrderCallbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 秒杀订单回调服务实现类
 * 用于处理支付回调、订单状态更新等操作
 */
@Service("seckillOrderCallbackService")
public class SeckillOrderCallbackServiceImpl implements SeckillOrderCallbackService {
    
    private static final Logger logger = LoggerFactory.getLogger(SeckillOrderCallbackServiceImpl.class);
    
    @Autowired
    private SeckillOrderMapper seckillOrderMapper;
    
    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;
    
    private static final int ORDER_TIMEOUT_MINUTES = 30;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean onPaySuccess(Integer payInOrderId, String payOrderNo) {
        try {
            logger.info("处理支付成功回调，payInOrderId: {}, payOrderNo: {}", payInOrderId, payOrderNo);
            
            SeckillOrder seckillOrder = seckillOrderMapper.selectByPayInOrderId(payInOrderId);
            if (seckillOrder == null) {
                logger.warn("未找到对应的秒杀订单，payInOrderId: {}", payInOrderId);
                return false;
            }
            
            if (seckillOrder.getStatus() == 1) {
                logger.info("订单已支付，跳过重复处理，orderId: {}", seckillOrder.getId());
                return true;
            }
            
            int result = seckillOrderMapper.updatePaySuccess(
                seckillOrder.getId(), 
                payInOrderId, 
                payOrderNo
            );
            
            if (result > 0) {
                logger.info("秒杀订单支付状态更新成功，orderId: {}, payInOrderId: {}, payOrderNo: {}", 
                    seckillOrder.getId(), payInOrderId, payOrderNo);
                
                String userKey = SeckillConstants.SECKILL_SUCCESS_USER_KEY_PREFIX + 
                    seckillOrder.getGoodsId() + ":" + seckillOrder.getUserId();
                String userValue = RedisUtil.get(userKey);
                if (userValue == null || userValue.isEmpty()) {
                    RedisUtil.set(userKey, "1", SeckillConstants.SECKILL_STOCK_TIMEOUT);
                }
                
                return true;
            } else {
                logger.warn("秒杀订单支付状态更新失败，orderId: {}", seckillOrder.getId());
                return false;
            }
            
        } catch (Exception e) {
            logger.error("处理支付成功回调失败，payInOrderId: {}, payOrderNo: {}", 
                payInOrderId, payOrderNo, e);
            return false;
        }
    }

    @Override
    public SeckillOrder getSeckillOrderByPayInOrderId(Integer payInOrderId) {
        try {
            return seckillOrderMapper.selectByPayInOrderId(payInOrderId);
        } catch (Exception e) {
            logger.error("根据支付订单ID查询秒杀订单失败，payInOrderId: {}", payInOrderId, e);
            return null;
        }
    }

    @Override
    public SeckillOrder getSeckillOrderByOrderNo(String orderNo) {
        try {
            return seckillOrderMapper.selectByOrderNo(orderNo);
        } catch (Exception e) {
            logger.error("根据订单号查询秒杀订单失败，orderNo: {}", orderNo, e);
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelOrder(Long orderId) {
        try {
            logger.info("取消秒杀订单，orderId: {}", orderId);
            
            SeckillOrder seckillOrder = seckillOrderMapper.selectByPrimaryKey(orderId);
            if (seckillOrder == null) {
                logger.warn("订单不存在，orderId: {}", orderId);
                return false;
            }
            
            if (seckillOrder.getStatus() == 1) {
                logger.warn("订单已支付，无法取消，orderId: {}", orderId);
                return false;
            }
            
            if (seckillOrder.getStatus() == 2) {
                logger.info("订单已取消，跳过重复处理，orderId: {}", orderId);
                return true;
            }
            
            seckillOrder.setStatus(2);
            seckillOrder.setUpdateTime(new Date());
            int result = seckillOrderMapper.updateByPrimaryKeySelective(seckillOrder);
            
            if (result > 0) {
                logger.info("秒杀订单取消成功，orderId: {}", orderId);
                
                seckillGoodsMapper.increaseStock(seckillOrder.getGoodsId(), seckillOrder.getQuantity());
                
                String stockKey = SeckillConstants.SECKILL_GOODS_STOCK_KEY_PREFIX + seckillOrder.getGoodsId();
                RedisUtil.incr(stockKey);
                
                String userKey = SeckillConstants.SECKILL_SUCCESS_USER_KEY_PREFIX + 
                    seckillOrder.getGoodsId() + ":" + seckillOrder.getUserId();
                RedisUtil.remove(userKey);
                
                return true;
            } else {
                logger.warn("秒杀订单取消失败，orderId: {}", orderId);
                return false;
            }
            
        } catch (Exception e) {
            logger.error("取消秒杀订单失败，orderId: {}", orderId, e);
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkTimeoutOrders() {
        try {
            logger.info("开始检查超时未支付的秒杀订单");
            
            List<SeckillOrder> pendingOrders = seckillOrderMapper.selectByUserId(0, 0);
            
            int cancelledCount = 0;
            long now = System.currentTimeMillis();
            long timeoutMillis = ORDER_TIMEOUT_MINUTES * 60 * 1000;
            
            for (SeckillOrder order : pendingOrders) {
                if (order.getCreateTime() != null) {
                    long createTime = order.getCreateTime().getTime();
                    if (now - createTime > timeoutMillis) {
                        logger.info("订单超时未支付，准备取消，orderId: {}, orderNo: {}", 
                            order.getId(), order.getOrderNo());
                        
                        if (cancelOrder(order.getId())) {
                            cancelledCount++;
                        }
                    }
                }
            }
            
            logger.info("超时订单检查完成，共取消 {} 个订单", cancelledCount);
            
        } catch (Exception e) {
            logger.error("检查超时订单失败", e);
        }
    }
}
