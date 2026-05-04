package com.zheng.seckill.rpc.service;

import com.alibaba.fastjson.JSON;
import com.zheng.common.util.JmsUtil;
import com.zheng.common.util.RedisUtil;
import com.zheng.seckill.common.constant.SeckillConstants;
import com.zheng.seckill.common.dto.SeckillGoodsDTO;
import com.zheng.seckill.common.dto.SeckillMessageDTO;
import com.zheng.seckill.common.dto.SeckillResultDTO;
import com.zheng.seckill.common.enums.SeckillStatusEnum;
import com.zheng.seckill.common.fallback.SeckillFallbackService;
import com.zheng.seckill.dao.mapper.SeckillGoodsMapper;
import com.zheng.seckill.dao.model.SeckillGoods;
import com.zheng.seckill.rpc.api.SeckillApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Destination;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 秒杀核心服务实现类
 * 实现秒杀相关的核心业务逻辑
 * 故障类型通过参数传递给降级服务，避免ThreadLocal线程安全问题
 */
@Service("seckillApiService")
public class SeckillApiServiceImpl implements SeckillApiService {
    
    private static final Logger logger = LoggerFactory.getLogger(SeckillApiServiceImpl.class);
    
    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;
    
    @Autowired
    private JmsTemplate jmsTemplate;
    
    @Autowired(required = false)
    private Destination seckillQueueDestination;
    
    @Autowired
    private SeckillFallbackService seckillFallbackService;

    @Override
    public List<SeckillGoodsDTO> getSeckillGoodsList() {
        logger.info("获取秒杀商品列表");
        try {
            String cacheKey = "seckill:goods:list";
            String cachedList = RedisUtil.get(cacheKey);
            if (cachedList != null && !cachedList.isEmpty()) {
                List<SeckillGoodsDTO> list = JSON.parseArray(cachedList, SeckillGoodsDTO.class);
                if (list != null && !list.isEmpty()) {
                    return list;
                }
            }
            
            List<SeckillGoods> goodsList = seckillGoodsMapper.selectActiveSeckillGoods();
            List<SeckillGoodsDTO> dtoList = new ArrayList<>();
            
            for (SeckillGoods goods : goodsList) {
                SeckillGoodsDTO dto = new SeckillGoodsDTO();
                BeanUtils.copyProperties(goods, dto);
                dtoList.add(dto);
            }
            
            if (!dtoList.isEmpty()) {
                RedisUtil.set(cacheKey, JSON.toJSONString(dtoList), 60);
            }
            
            return dtoList;
        } catch (Exception e) {
            logger.error("获取秒杀商品列表失败", e);
            return (List<SeckillGoodsDTO>) seckillFallbackService.getSeckillGoodsListFallback(
                SeckillFallbackService.FAILURE_TYPE_DB);
        }
    }

    @Override
    public SeckillGoodsDTO getSeckillGoodsDetail(Long goodsId) {
        logger.info("获取秒杀商品详情，goodsId: {}", goodsId);
        try {
            String cacheKey = SeckillConstants.SECKILL_GOODS_INFO_KEY_PREFIX + goodsId;
            String cachedGoods = RedisUtil.get(cacheKey);
            if (cachedGoods != null && !cachedGoods.isEmpty()) {
                SeckillGoodsDTO dto = JSON.parseObject(cachedGoods, SeckillGoodsDTO.class);
                if (dto != null) {
                    return dto;
                }
            }
            
            SeckillGoods goods = seckillGoodsMapper.selectByGoodsId(goodsId);
            if (goods == null) {
                throw new RuntimeException("秒杀商品不存在");
            }
            
            SeckillGoodsDTO dto = new SeckillGoodsDTO();
            BeanUtils.copyProperties(goods, dto);
            
            RedisUtil.set(cacheKey, JSON.toJSONString(dto), 300);
            
            return dto;
        } catch (redis.clients.jedis.exceptions.JedisConnectionException e) {
            logger.error("Redis连接失败，获取秒杀商品详情从数据库读取，goodsId: {}", goodsId, e);
            try {
                SeckillGoods goods = seckillGoodsMapper.selectByGoodsId(goodsId);
                if (goods == null) {
                    throw new RuntimeException("秒杀商品不存在");
                }
                SeckillGoodsDTO dto = new SeckillGoodsDTO();
                BeanUtils.copyProperties(goods, dto);
                return dto;
            } catch (Exception ex) {
                logger.error("数据库也连接失败，goodsId: {}", goodsId, ex);
                return (SeckillGoodsDTO) seckillFallbackService.getSeckillGoodsDetailFallback(
                    goodsId, SeckillFallbackService.FAILURE_TYPE_DB);
            }
        } catch (Exception e) {
            logger.error("获取秒杀商品详情失败，goodsId: {}", goodsId, e);
            return (SeckillGoodsDTO) seckillFallbackService.getSeckillGoodsDetailFallback(
                goodsId, SeckillFallbackService.FAILURE_TYPE_DB);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SeckillResultDTO executeSeckill(Integer userId, Long goodsId) {
        logger.info("执行秒杀操作，userId: {}, goodsId: {}", userId, goodsId);
        
        try {
            String limitKey = "seckill:limit:" + goodsId;
            if (!checkLimit(limitKey, SeckillConstants.SECKILL_DEFAULT_LIMIT)) {
                logger.warn("秒杀限流，userId: {}, goodsId: {}", userId, goodsId);
                return SeckillResultDTO.fail(SeckillStatusEnum.LIMIT_EXCEEDED.getMessage());
            }
            
            String checkResult = checkSeckillAvailable(goodsId);
            if (checkResult != null) {
                logger.warn("秒杀不可用，userId: {}, goodsId: {}, reason: {}", userId, goodsId, checkResult);
                return SeckillResultDTO.fail(checkResult);
            }
            
            if (isUserSeckillSuccess(userId, goodsId)) {
                logger.warn("重复秒杀，userId: {}, goodsId: {}", userId, goodsId);
                return SeckillResultDTO.fail(SeckillStatusEnum.REPEAT_SECKILL.getMessage());
            }
            
            String stockKey = SeckillConstants.SECKILL_GOODS_STOCK_KEY_PREFIX + goodsId;
            Long stock = RedisUtil.decr(stockKey);
            if (stock == null) {
                logger.error("Redis操作失败，无法减库存，userId: {}, goodsId: {}", userId, goodsId);
                return seckillFallbackService.seckillFallback(
                    userId, goodsId, SeckillFallbackService.FAILURE_TYPE_REDIS);
            }
            
            if (stock < 0) {
                RedisUtil.incr(stockKey);
                logger.warn("库存不足，userId: {}, goodsId: {}", userId, goodsId);
                return SeckillResultDTO.fail(SeckillStatusEnum.STOCK_NOT_ENOUGH.getMessage());
            }
            
            try {
                String userKey = SeckillConstants.SECKILL_SUCCESS_USER_KEY_PREFIX + goodsId + ":" + userId;
                RedisUtil.set(userKey, "1", SeckillConstants.SECKILL_STOCK_TIMEOUT);
                
                SeckillGoodsDTO goodsDTO = getSeckillGoodsDetail(goodsId);
                SeckillMessageDTO messageDTO = new SeckillMessageDTO(
                    userId, 
                    goodsId, 
                    goodsDTO.getGoodsName(), 
                    goodsDTO.getSeckillPrice(), 
                    1
                );
                messageDTO.setMessageId(UUID.randomUUID().toString());
                
                try {
                    if (seckillQueueDestination != null) {
                        JmsUtil.sendMessage(jmsTemplate, seckillQueueDestination, messageDTO);
                    } else {
                        jmsTemplate.convertAndSend(SeckillConstants.SECKILL_MESSAGE_QUEUE, messageDTO);
                    }
                    logger.info("秒杀消息已发送，userId: {}, goodsId: {}", userId, goodsId);
                } catch (Exception e) {
                    logger.error("消息队列发送失败，userId: {}, goodsId: {}", userId, goodsId, e);
                    RedisUtil.incr(stockKey);
                    RedisUtil.remove(userKey);
                    return seckillFallbackService.seckillFallback(
                        userId, goodsId, SeckillFallbackService.FAILURE_TYPE_MQ);
                }
                
                return SeckillResultDTO.processing("秒杀请求已提交，正在处理中，请稍后查询结果");
                
            } catch (Exception e) {
                RedisUtil.incr(stockKey);
                logger.error("秒杀处理异常，回滚库存，userId: {}, goodsId: {}", userId, goodsId, e);
                throw e;
            }
            
        } catch (redis.clients.jedis.exceptions.JedisConnectionException e) {
            logger.error("Redis连接失败，秒杀失败，userId: {}, goodsId: {}", userId, goodsId, e);
            return seckillFallbackService.seckillFallback(
                userId, goodsId, SeckillFallbackService.FAILURE_TYPE_REDIS);
        } catch (Exception e) {
            logger.error("执行秒杀操作失败，userId: {}, goodsId: {}", userId, goodsId, e);
            return seckillFallbackService.seckillFallback(
                userId, goodsId, SeckillFallbackService.FAILURE_TYPE_DB);
        }
    }

    @Override
    public SeckillResultDTO getSeckillResult(Integer userId, Long goodsId) {
        logger.info("查询秒杀结果，userId: {}, goodsId: {}", userId, goodsId);
        
        try {
            if (!isUserSeckillSuccess(userId, goodsId)) {
                String stockKey = SeckillConstants.SECKILL_GOODS_STOCK_KEY_PREFIX + goodsId;
                String stockStr = RedisUtil.get(stockKey);
                if (stockStr != null) {
                    try {
                        Integer stock = Integer.parseInt(stockStr);
                        if (stock < 0) {
                            return SeckillResultDTO.fail(SeckillStatusEnum.STOCK_NOT_ENOUGH.getMessage());
                        }
                    } catch (NumberFormatException e) {
                    }
                }
                return SeckillResultDTO.processing("秒杀结果正在处理中，请稍后再试");
            }
            
            SeckillGoodsDTO goodsDTO = getSeckillGoodsDetail(goodsId);
            
            return SeckillResultDTO.success(
                1L,
                goodsId,
                goodsDTO.getGoodsName(),
                goodsDTO.getSeckillPrice(),
                new Date(),
                userId,
                "SK" + System.currentTimeMillis()
            );
            
        } catch (Exception e) {
            logger.error("查询秒杀结果失败，userId: {}, goodsId: {}", userId, goodsId, e);
            return seckillFallbackService.getSeckillResultFallback(
                userId, goodsId, SeckillFallbackService.FAILURE_TYPE_DB);
        }
    }

    @Override
    public boolean initSeckillStock(Long goodsId, Integer stockCount) {
        logger.info("初始化秒杀商品库存，goodsId: {}, stockCount: {}", goodsId, stockCount);
        
        try {
            SeckillGoods goods = seckillGoodsMapper.selectByGoodsId(goodsId);
            if (goods == null) {
                throw new RuntimeException("秒杀商品不存在");
            }
            
            goods.setStockCount(stockCount);
            seckillGoodsMapper.updateByPrimaryKeySelective(goods);
            
            String stockKey = SeckillConstants.SECKILL_GOODS_STOCK_KEY_PREFIX + goodsId;
            RedisUtil.set(stockKey, String.valueOf(stockCount));
            
            String cacheKey = SeckillConstants.SECKILL_GOODS_INFO_KEY_PREFIX + goodsId;
            RedisUtil.remove(cacheKey);
            
            String listCacheKey = "seckill:goods:list";
            RedisUtil.remove(listCacheKey);
            
            logger.info("初始化秒杀商品库存成功，goodsId: {}, stockCount: {}", goodsId, stockCount);
            return true;
            
        } catch (Exception e) {
            logger.error("初始化秒杀商品库存失败，goodsId: {}, stockCount: {}", goodsId, stockCount, e);
            return false;
        }
    }

    @Override
    public boolean isUserSeckillSuccess(Integer userId, Long goodsId) {
        String userKey = SeckillConstants.SECKILL_SUCCESS_USER_KEY_PREFIX + goodsId + ":" + userId;
        String value = RedisUtil.get(userKey);
        return value != null && !value.isEmpty();
    }

    @Override
    public String checkSeckillAvailable(Long goodsId) {
        try {
            SeckillGoodsDTO goodsDTO = getSeckillGoodsDetail(goodsId);
            
            if (goodsDTO.getStatus() != 1) {
                return "秒杀商品已下架";
            }
            
            Date now = new Date();
            if (now.before(goodsDTO.getStartTime())) {
                return SeckillStatusEnum.NOT_STARTED.getMessage();
            }
            if (now.after(goodsDTO.getEndTime())) {
                return SeckillStatusEnum.ENDED.getMessage();
            }
            
            String stockKey = SeckillConstants.SECKILL_GOODS_STOCK_KEY_PREFIX + goodsId;
            String stockStr = RedisUtil.get(stockKey);
            if (stockStr == null || stockStr.isEmpty()) {
                if (goodsDTO.getStockCount() <= 0) {
                    return SeckillStatusEnum.STOCK_NOT_ENOUGH.getMessage();
                }
                initSeckillStock(goodsId, goodsDTO.getStockCount());
            } else {
                try {
                    Integer stock = Integer.parseInt(stockStr);
                    if (stock <= 0) {
                        return SeckillStatusEnum.STOCK_NOT_ENOUGH.getMessage();
                    }
                } catch (NumberFormatException e) {
                    if (goodsDTO.getStockCount() <= 0) {
                        return SeckillStatusEnum.STOCK_NOT_ENOUGH.getMessage();
                    }
                }
            }
            
            return null;
            
        } catch (Exception e) {
            logger.error("检查秒杀可用性失败，goodsId: {}", goodsId, e);
            return "系统异常，请稍后再试";
        }
    }

    @Override
    public boolean checkLimit(String key, int limit) {
        try {
            String limitKey = SeckillConstants.SECKILL_LIMIT_KEY_PREFIX + key;
            Long count = RedisUtil.incr(limitKey);
            if (count == null) {
                return true;
            }
            if (count == 1) {
                RedisUtil.set(limitKey, "1", 1);
            }
            return count <= limit;
        } catch (Exception e) {
            logger.error("限流检查失败，key: {}", key, e);
            return true;
        }
    }
}
