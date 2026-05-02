package com.zheng.seckill.common.fallback;

import com.zheng.seckill.common.dto.SeckillResultDTO;

/**
 * 秒杀服务降级接口
 * 用于防止雪崩效应，当秒杀服务不可用时提供降级处理
 */
public interface SeckillFallbackService {
    
    /**
     * 秒杀服务降级处理
     * 当秒杀服务调用失败时，返回友好的降级结果
     * @param userId 用户ID
     * @param goodsId 商品ID
     * @return 降级处理结果
     */
    SeckillResultDTO seckillFallback(Integer userId, Long goodsId);
    
    /**
     * 秒杀商品列表服务降级处理
     * 当获取秒杀商品列表失败时，返回缓存或默认数据
     * @return 降级处理结果
     */
    Object getSeckillGoodsListFallback();
    
    /**
     * 秒杀商品详情服务降级处理
     * 当获取秒杀商品详情失败时，返回缓存或默认数据
     * @param goodsId 商品ID
     * @return 降级处理结果
     */
    Object getSeckillGoodsDetailFallback(Long goodsId);
    
    /**
     * 秒杀结果查询服务降级处理
     * 当查询秒杀结果失败时，返回友好提示
     * @param userId 用户ID
     * @param goodsId 商品ID
     * @return 降级处理结果
     */
    SeckillResultDTO getSeckillResultFallback(Integer userId, Long goodsId);
}
