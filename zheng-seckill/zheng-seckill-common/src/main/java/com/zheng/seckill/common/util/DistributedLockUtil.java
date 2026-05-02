package com.zheng.seckill.common.util;

import com.zheng.seckill.common.constant.SeckillConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁工具类，用于秒杀场景
 */
@Component
public class DistributedLockUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(DistributedLockUtil.class);
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 尝试获取分布式锁
     * @param lockKey 锁key
     * @param requestId 请求标识
     * @return 是否获取成功
     */
    public boolean tryLock(String lockKey, String requestId) {
        return tryLock(lockKey, requestId, SeckillConstants.SECKILL_LOCK_TIMEOUT);
    }

    /**
     * 尝试获取分布式锁
     * @param lockKey 锁key
     * @param requestId 请求标识
     * @param expireTime 过期时间（毫秒）
     * @return 是否获取成功
     */
    public boolean tryLock(String lockKey, String requestId, long expireTime) {
        String key = SeckillConstants.SECKILL_LOCK_KEY_PREFIX + lockKey;
        try {
            Boolean success = redisTemplate.opsForValue().setIfAbsent(key, requestId, expireTime, TimeUnit.MILLISECONDS);
            return success != null && success;
        } catch (Exception e) {
            logger.error("获取分布式锁异常，lockKey: {}, requestId: {}", lockKey, requestId, e);
            return false;
        }
    }

    /**
     * 释放分布式锁
     * @param lockKey 锁key
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public boolean releaseLock(String lockKey, String requestId) {
        String key = SeckillConstants.SECKILL_LOCK_KEY_PREFIX + lockKey;
        try {
            Object value = redisTemplate.opsForValue().get(key);
            if (value != null && requestId.equals(value.toString())) {
                redisTemplate.delete(key);
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("释放分布式锁异常，lockKey: {}, requestId: {}", lockKey, requestId, e);
            return false;
        }
    }

    /**
     * 执行带分布式锁的操作
     * @param lockKey 锁key
     * @param requestId 请求标识
     * @param expireTime 过期时间（毫秒）
     * @param action 需要执行的操作
     * @param <T> 返回类型
     * @return 操作结果
     */
    public <T> T executeWithLock(String lockKey, String requestId, long expireTime, LockAction<T> action) {
        boolean locked = false;
        try {
            locked = tryLock(lockKey, requestId, expireTime);
            if (locked) {
                return action.execute();
            } else {
                throw new RuntimeException("获取分布式锁失败");
            }
        } finally {
            if (locked) {
                releaseLock(lockKey, requestId);
            }
        }
    }

    /**
     * 锁操作接口
     * @param <T> 返回类型
     */
    @FunctionalInterface
    public interface LockAction<T> {
        T execute();
    }
}
