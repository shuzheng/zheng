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
 * 支持区分不同的故障场景：Redis、MQ、DB
 * 故障类型通过参数传递，避免ThreadLocal线程安全问题
 */
@Service
public class SeckillFallbackServiceImpl implements SeckillFallbackService {
    
    private static final Logger logger = LoggerFactory.getLogger(SeckillFallbackServiceImpl.class);

    @Override
    public String getFallbackMessage(String failureType) {
        if (failureType == null) {
            failureType = FAILURE_TYPE_UNKNOWN;
        }
        if (FAILURE_TYPE_REDIS.equals(failureType)) {
            return "Redis缓存服务暂时不可用，请稍后再试";
        } else if (FAILURE_TYPE_MQ.equals(failureType)) {
            return "消息队列服务暂时不可用，请稍后再试";
        } else if (FAILURE_TYPE_DB.equals(failureType)) {
            return "数据库服务暂时不可用，请稍后再试";
        } else {
            return "秒杀活动火爆，请稍后再试";
        }
    }

    @Override
    public SeckillResultDTO seckillFallback(Integer userId, Long goodsId, String failureType) {
        if (failureType == null) {
            failureType = FAILURE_TYPE_UNKNOWN;
        }
        logger.warn("秒杀服务降级处理，userId: {}, goodsId: {}, failureType: {}", 
            userId, goodsId, failureType);
        
        String message = getFallbackMessage(failureType);
        return SeckillResultDTO.fail(message);
    }

    @Override
    public Object getSeckillGoodsListFallback(String failureType) {
        if (failureType == null) {
            failureType = FAILURE_TYPE_UNKNOWN;
        }
        logger.warn("获取秒杀商品列表服务降级处理，failureType: {}", failureType);
        
        List<Map<String, Object>> fallbackList = new ArrayList<>();
        
        if (FAILURE_TYPE_REDIS.equals(failureType)) {
            Map<String, Object> fallbackItem = new HashMap<>();
            fallbackItem.put("goodsId", -1L);
            fallbackItem.put("goodsName", "秒杀商品列表加载中");
            fallbackItem.put("status", 0);
            fallbackItem.put("message", "Redis缓存暂时不可用，正在从数据库加载...");
            fallbackList.add(fallbackItem);
        } else if (FAILURE_TYPE_DB.equals(failureType)) {
            Map<String, Object> fallbackItem = new HashMap<>();
            fallbackItem.put("goodsId", -1L);
            fallbackItem.put("goodsName", "秒杀商品列表加载失败");
            fallbackItem.put("status", 0);
            fallbackItem.put("message", "数据库服务暂时不可用，请稍后再试");
            fallbackList.add(fallbackItem);
        } else {
            Map<String, Object> fallbackItem = new HashMap<>();
            fallbackItem.put("goodsId", -1L);
            fallbackItem.put("goodsName", "秒杀商品列表加载中");
            fallbackItem.put("status", 0);
            fallbackList.add(fallbackItem);
        }
        
        return fallbackList;
    }

    @Override
    public Object getSeckillGoodsDetailFallback(Long goodsId, String failureType) {
        if (failureType == null) {
            failureType = FAILURE_TYPE_UNKNOWN;
        }
        logger.warn("获取秒杀商品详情服务降级处理，goodsId: {}, failureType: {}", goodsId, failureType);
        
        Map<String, Object> fallbackDetail = new HashMap<>();
        fallbackDetail.put("goodsId", goodsId);
        
        if (FAILURE_TYPE_REDIS.equals(failureType)) {
            fallbackDetail.put("goodsName", "秒杀商品详情加载中");
            fallbackDetail.put("status", 0);
            fallbackDetail.put("message", "Redis缓存暂时不可用，正在从数据库加载...");
        } else if (FAILURE_TYPE_DB.equals(failureType)) {
            fallbackDetail.put("goodsName", "秒杀商品详情加载失败");
            fallbackDetail.put("status", 0);
            fallbackDetail.put("message", "数据库服务暂时不可用，请稍后再试");
        } else {
            fallbackDetail.put("goodsName", "秒杀商品详情加载中");
            fallbackDetail.put("status", 0);
            fallbackDetail.put("message", "当前访问人数较多，请稍后再试");
        }
        
        return fallbackDetail;
    }

    @Override
    public SeckillResultDTO getSeckillResultFallback(Integer userId, Long goodsId, String failureType) {
        if (failureType == null) {
            failureType = FAILURE_TYPE_UNKNOWN;
        }
        logger.warn("查询秒杀结果服务降级处理，userId: {}, goodsId: {}, failureType: {}", 
            userId, goodsId, failureType);
        
        SeckillResultDTO result;
        
        if (FAILURE_TYPE_REDIS.equals(failureType) || FAILURE_TYPE_DB.equals(failureType)) {
            result = SeckillResultDTO.processing("秒杀结果查询服务暂时不可用，请稍后再试");
        } else {
            result = SeckillResultDTO.processing("秒杀结果正在处理中，请稍后查询");
        }
        
        return result;
    }
}
