package com.zheng.seckill.common.constant;

/**
 * 秒杀相关常量
 */
public class SeckillConstants {
    
    /**
     * Redis中秒杀商品库存key前缀
     */
    public static final String SECKILL_GOODS_STOCK_KEY_PREFIX = "seckill:goods:stock:";
    
    /**
     * Redis中秒杀商品信息key前缀
     */
    public static final String SECKILL_GOODS_INFO_KEY_PREFIX = "seckill:goods:info:";
    
    /**
     * Redis中秒杀成功用户key前缀
     */
    public static final String SECKILL_SUCCESS_USER_KEY_PREFIX = "seckill:success:user:";
    
    /**
     * Redis中秒杀限流key前缀
     */
    public static final String SECKILL_LIMIT_KEY_PREFIX = "seckill:limit:";
    
    /**
     * Redis中秒杀分布式锁key前缀
     */
    public static final String SECKILL_LOCK_KEY_PREFIX = "seckill:lock:";
    
    /**
     * 秒杀消息队列名称
     */
    public static final String SECKILL_MESSAGE_QUEUE = "seckill.queue";
    
    /**
     * 秒杀默认库存超时时间（秒）
     */
    public static final int SECKILL_STOCK_TIMEOUT = 60;
    
    /**
     * 秒杀默认限流数量
     */
    public static final int SECKILL_DEFAULT_LIMIT = 100;
    
    /**
     * 秒杀默认分布式锁超时时间（毫秒）
     */
    public static final int SECKILL_LOCK_TIMEOUT = 10000;
    
    /**
     * 秒杀结果状态：成功
     */
    public static final int SECKILL_STATUS_SUCCESS = 1;
    
    /**
     * 秒杀结果状态：失败
     */
    public static final int SECKILL_STATUS_FAILED = 0;
    
    /**
     * 秒杀结果状态：处理中
     */
    public static final int SECKILL_STATUS_PROCESSING = 2;
}
