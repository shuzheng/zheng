package com.zheng.seckill.common.fallback;

import com.zheng.seckill.common.dto.SeckillResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 秒杀服务降级实现类
 * 当秒杀服务不可用时，提供友好的降级处理
 */
@Service
public class SeckillFallbackServiceImpl implements SeckillFallbackService {
    
    private static final Logger logger = LoggerFactory.getLogger(SeckillFallbackServiceImpl.class);

    @Override
    public SeckillResultDTO seckillFallback(Integer userId, Long goodsId) {
        logger.warn("秒杀服务降级处理，userId: {}, goodsId: {}", userId, goodsId);
        return SeckillResultDTO.fail("当前秒杀活动火爆，请稍后再试");
    }

    @Override
    public Object getSeckillGoodsListFallback() {
        logger.warn("获取秒杀商品列表服务降级处理");
        List<Map<String, Object>> fallbackList = new ArrayList<>();
        Map<String, Object> fallbackItem = new HashMap<>();
        fallbackItem.put("goodsId", -1L);
        fallbackItem.put("goodsName", "秒杀活动加载中");
        fallbackItem.put("status", 0);
        fallbackList.add(fallbackItem);
        return fallbackList;
    }

    @Override
    public Object getSeckillGoodsDetailFallback(Long goodsId) {
        logger.warn("获取秒杀商品详情服务降级处理，goodsId: {}", goodsId);
        Map<String, Object> fallbackDetail = new HashMap<>();
        fallbackDetail.put("goodsId", goodsId);
        fallbackDetail.put("goodsName", "商品详情加载中");
        fallbackDetail.put("status", 0);
        fallbackDetail.put("message", "当前访问人数较多，请稍后再试");
        return fallbackDetail;
    }

    @Override
    public SeckillResultDTO getSeckillResultFallback(Integer userId, Long goodsId) {
        logger.warn("查询秒杀结果服务降级处理，userId: {}, goodsId: {}", userId, goodsId);
        return SeckillResultDTO.processing("秒杀结果正在处理中，请稍后查询");
    }
}
