package com.zheng.seckill.rpc.api;

import com.zheng.seckill.dao.model.SeckillOrder;

/**
 * 秒杀订单回调服务接口
 * 用于处理支付回调、订单状态更新等操作
 */
public interface SeckillOrderCallbackService {

    /**
     * 支付成功回调
     * 当用户支付成功后，更新秒杀订单状态
     * @param payInOrderId 支付中心订单ID
     * @param payOrderNo 支付平台订单号（如支付宝/微信交易号）
     * @return 是否处理成功
     */
    boolean onPaySuccess(Integer payInOrderId, String payOrderNo);

    /**
     * 根据支付订单ID查询秒杀订单
     * @param payInOrderId 支付中心订单ID
     * @return 秒杀订单
     */
    SeckillOrder getSeckillOrderByPayInOrderId(Integer payInOrderId);

    /**
     * 根据订单号查询秒杀订单
     * @param orderNo 秒杀订单号
     * @return 秒杀订单
     */
    SeckillOrder getSeckillOrderByOrderNo(String orderNo);

    /**
     * 取消订单
     * @param orderId 订单ID
     * @return 是否取消成功
     */
    boolean cancelOrder(Long orderId);

    /**
     * 订单超时检查
     * 用于定时任务检查超时未支付的订单并取消
     */
    void checkTimeoutOrders();
}
