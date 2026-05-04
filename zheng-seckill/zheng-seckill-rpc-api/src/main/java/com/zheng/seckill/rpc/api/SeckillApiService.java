package com.zheng.seckill.rpc.api;

import com.zheng.seckill.common.dto.SeckillGoodsDTO;
import com.zheng.seckill.common.dto.SeckillResultDTO;
import com.zheng.seckill.dao.model.SeckillGoods;

import java.util.List;

/**
 * 秒杀核心服务接口
 * 提供秒杀相关的核心业务功能
 */
public interface SeckillApiService {

    /**
     * 获取秒杀商品列表
     * @return 秒杀商品列表
     */
    List<SeckillGoodsDTO> getSeckillGoodsList();

    /**
     * 获取秒杀商品详情
     * @param goodsId 商品ID
     * @return 秒杀商品详情
     */
    SeckillGoodsDTO getSeckillGoodsDetail(Long goodsId);

    /**
     * 执行秒杀操作
     * @param userId 用户ID
     * @param goodsId 商品ID
     * @return 秒杀结果
     */
    SeckillResultDTO executeSeckill(Integer userId, Long goodsId);

    /**
     * 查询秒杀结果
     * @param userId 用户ID
     * @param goodsId 商品ID
     * @return 秒杀结果
     */
    SeckillResultDTO getSeckillResult(Integer userId, Long goodsId);

    /**
     * 初始化秒杀商品库存到Redis
     * @param goodsId 商品ID
     * @param stockCount 库存数量
     * @return 是否成功
     */
    boolean initSeckillStock(Long goodsId, Integer stockCount);

    /**
     * 检查用户是否已经秒杀成功
     * @param userId 用户ID
     * @param goodsId 商品ID
     * @return 是否已经秒杀成功
     */
    boolean isUserSeckillSuccess(Integer userId, Long goodsId);

    /**
     * 检查秒杀是否可以进行
     * @param goodsId 商品ID
     * @return 检查结果，返回null表示可以进行，否则返回错误信息
     */
    String checkSeckillAvailable(Long goodsId);

    /**
     * 限流检查
     * @param key 限流key
     * @param limit 限制数量
     * @return 是否通过限流检查
     */
    boolean checkLimit(String key, int limit);
}
