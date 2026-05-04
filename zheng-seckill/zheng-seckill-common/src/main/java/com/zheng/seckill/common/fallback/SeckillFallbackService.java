package com.zheng.seckill.common.fallback;

import com.zheng.seckill.common.dto.SeckillResultDTO;

/**
 * 秒杀服务降级接口
 * 用于防止雪崩效应，当秒杀服务不可用时提供降级处理
 * 支持区分不同的故障场景：Redis、MQ、DB
 */
public interface SeckillFallbackService {
    
    String FAILURE_TYPE_REDIS = "Redis";
    String FAILURE_TYPE_MQ = "MQ";
    String FAILURE_TYPE_DB = "DB";
    String FAILURE_TYPE_UNKNOWN = "UNKNOWN";

    /**
     * 获取降级消息
     * @param failureType 故障类型：Redis/MQ/DB/UNKNOWN
     * @return 降级消息
     */
    String getFallbackMessage(String failureType);

    /**
     * 秒杀服务降级处理
     * 当秒杀服务调用失败时，返回友好的降级结果
     * @param userId 用户ID
     * @param goodsId 商品ID
     * @param failureType 故障类型
     * @return 降级处理结果
     */
    SeckillResultDTO seckillFallback(Integer userId, Long goodsId, String failureType);
    
    /**
     * 秒杀商品列表服务降级处理
     * 当获取秒杀商品列表失败时，返回缓存或默认数据
     * @param failureType 故障类型
     * @return 降级处理结果
     */
    Object getSeckillGoodsListFallback(String failureType);
    
    /**
     * 秒杀商品详情服务降级处理
     * 当获取秒杀商品详情失败时，返回缓存或默认数据
     * @param goodsId 商品ID
     * @param failureType 故障类型
     * @return 降级处理结果
     */
    Object getSeckillGoodsDetailFallback(Long goodsId, String failureType);
    
    /**
     * 秒杀结果查询服务降级处理
     * 当查询秒杀结果失败时，返回友好提示
     * @param userId 用户ID
     * @param goodsId 商品ID
     * @param failureType 故障类型
     * @return 降级处理结果
     */
    SeckillResultDTO getSeckillResultFallback(Integer userId, Long goodsId, String failureType);
}
