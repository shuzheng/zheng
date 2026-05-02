package com.zheng.seckill.common.util;

import com.zheng.seckill.common.constant.SeckillConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类，用于秒杀相关操作
 */
@Component
public class RedisUtil {
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取秒杀商品库存
     * @param goodsId 商品ID
     * @return 库存数量
     */
    public Integer getSeckillStock(Long goodsId) {
        String key = SeckillConstants.SECKILL_GOODS_STOCK_KEY_PREFIX + goodsId;
        Object value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            return null;
        }
        return (Integer) value;
    }

    /**
     * 设置秒杀商品库存
     * @param goodsId 商品ID
     * @param stock 库存数量
     */
    public void setSeckillStock(Long goodsId, Integer stock) {
        String key = SeckillConstants.SECKILL_GOODS_STOCK_KEY_PREFIX + goodsId;
        redisTemplate.opsForValue().set(key, stock);
    }

    /**
     * 预减库存（原子操作）
     * @param goodsId 商品ID
     * @return 减库存后的数量，如果小于0表示库存不足
     */
    public Long decrementStock(Long goodsId) {
        String key = SeckillConstants.SECKILL_GOODS_STOCK_KEY_PREFIX + goodsId;
        return redisTemplate.opsForValue().decrement(key);
    }

    /**
     * 回滚库存
     * @param goodsId 商品ID
     */
    public void incrementStock(Long goodsId) {
        String key = SeckillConstants.SECKILL_GOODS_STOCK_KEY_PREFIX + goodsId;
        redisTemplate.opsForValue().increment(key);
    }

    /**
     * 检查用户是否已经秒杀成功
     * @param userId 用户ID
     * @param goodsId 商品ID
     * @return 是否已经秒杀成功
     */
    public boolean isUserSeckillSuccess(Integer userId, Long goodsId) {
        String key = SeckillConstants.SECKILL_SUCCESS_USER_KEY_PREFIX + goodsId + ":" + userId;
        return redisTemplate.hasKey(key);
    }

    /**
     * 标记用户秒杀成功
     * @param userId 用户ID
     * @param goodsId 商品ID
     */
    public void markUserSeckillSuccess(Integer userId, Long goodsId) {
        String key = SeckillConstants.SECKILL_SUCCESS_USER_KEY_PREFIX + goodsId + ":" + userId;
        redisTemplate.opsForValue().set(key, 1, SeckillConstants.SECKILL_STOCK_TIMEOUT, TimeUnit.SECONDS);
    }

    /**
     * 限流检查
     * @param key 限流key
     * @param limit 限制数量
     * @return 是否通过限流检查
     */
    public boolean checkLimit(String key, int limit) {
        String limitKey = SeckillConstants.SECKILL_LIMIT_KEY_PREFIX + key;
        Long count = redisTemplate.opsForValue().increment(limitKey);
        if (count == 1) {
            redisTemplate.expire(limitKey, 1, TimeUnit.SECONDS);
        }
        return count <= limit;
    }

    /**
     * 设置缓存
     * @param key 缓存key
     * @param value 缓存值
     * @param timeout 超时时间（秒）
     */
    public void set(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 获取缓存
     * @param key 缓存key
     * @return 缓存值
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除缓存
     * @param key 缓存key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 批量删除缓存
     * @param keys 缓存key集合
     */
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 检查key是否存在
     * @param key 缓存key
     * @return 是否存在
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 设置key过期时间
     * @param key 缓存key
     * @param timeout 超时时间（秒）
     */
    public void expire(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }
}
