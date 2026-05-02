package com.zheng.seckill.rpc.service;

import com.zheng.seckill.common.constant.SeckillConstants;
import com.zheng.seckill.common.dto.SeckillGoodsDTO;
import com.zheng.seckill.common.dto.SeckillMessageDTO;
import com.zheng.seckill.common.dto.SeckillResultDTO;
import com.zheng.seckill.common.enums.SeckillStatusEnum;
import com.zheng.seckill.common.fallback.SeckillFallbackService;
import com.zheng.seckill.common.util.DistributedLockUtil;
import com.zheng.seckill.common.util.MessageQueueUtil;
import com.zheng.seckill.common.util.RedisUtil;
import com.zheng.seckill.dao.mapper.SeckillGoodsMapper;
import com.zheng.seckill.dao.model.SeckillGoods;
import com.zheng.seckill.dao.model.SeckillGoodsExample;
import com.zheng.seckill.rpc.api.SeckillApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 秒杀核心服务实现类
 * 实现秒杀相关的核心业务逻辑
 */
@Service("seckillApiService")
public class SeckillApiServiceImpl implements SeckillApiService {
    
    private static final Logger logger = LoggerFactory.getLogger(SeckillApiServiceImpl.class);
    
    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;
    
    @Autowired
    private RedisUtil redisUtil;
    
    @Autowired
    private DistributedLockUtil distributedLockUtil;
    
    @Autowired
    private MessageQueueUtil messageQueueUtil;
    
    @Autowired
    private SeckillFallbackService seckillFallbackService;

    @Override
    public List<SeckillGoodsDTO> getSeckillGoodsList() {
        logger.info("获取秒杀商品列表");
        try {
            // 先从Redis缓存获取
            String cacheKey = "seckill:goods:list";
            Object cachedList = redisUtil.get(cacheKey);
            if (cachedList != null) {
                return (List<SeckillGoodsDTO>) cachedList;
            }
            
            // 从数据库查询
            List<SeckillGoods> goodsList = seckillGoodsMapper.selectActiveSeckillGoods();
            List<SeckillGoodsDTO> dtoList = new ArrayList<>();
            
            for (SeckillGoods goods : goodsList) {
                SeckillGoodsDTO dto = new SeckillGoodsDTO();
                BeanUtils.copyProperties(goods, dto);
                dtoList.add(dto);
            }
            
            // 缓存到Redis，有效期60秒
            redisUtil.set(cacheKey, dtoList, 60);
            
            return dtoList;
        } catch (Exception e) {
            logger.error("获取秒杀商品列表失败", e);
            // 降级处理
            return (List<SeckillGoodsDTO>) seckillFallbackService.getSeckillGoodsListFallback();
        }
    }

    @Override
    public SeckillGoodsDTO getSeckillGoodsDetail(Long goodsId) {
        logger.info("获取秒杀商品详情，goodsId: {}", goodsId);
        try {
            // 先从Redis缓存获取
            String cacheKey = SeckillConstants.SECKILL_GOODS_INFO_KEY_PREFIX + goodsId;
            Object cachedGoods = redisUtil.get(cacheKey);
            if (cachedGoods != null) {
                return (SeckillGoodsDTO) cachedGoods;
            }
            
            // 从数据库查询
            SeckillGoods goods = seckillGoodsMapper.selectByGoodsId(goodsId);
            if (goods == null) {
                throw new RuntimeException("秒杀商品不存在");
            }
            
            SeckillGoodsDTO dto = new SeckillGoodsDTO();
            BeanUtils.copyProperties(goods, dto);
            
            // 缓存到Redis，有效期5分钟
            redisUtil.set(cacheKey, dto, 300);
            
            return dto;
        } catch (Exception e) {
            logger.error("获取秒杀商品详情失败，goodsId: {}", goodsId, e);
            // 降级处理
            return (SeckillGoodsDTO) seckillFallbackService.getSeckillGoodsDetailFallback(goodsId);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SeckillResultDTO executeSeckill(Integer userId, Long goodsId) {
        logger.info("执行秒杀操作，userId: {}, goodsId: {}", userId, goodsId);
        
        try {
            // 1. 限流检查
            String limitKey = "seckill:limit:" + goodsId;
            if (!checkLimit(limitKey, SeckillConstants.SECKILL_DEFAULT_LIMIT)) {
                logger.warn("秒杀限流，userId: {}, goodsId: {}", userId, goodsId);
                return SeckillResultDTO.fail(SeckillStatusEnum.LIMIT_EXCEEDED.getMessage());
            }
            
            // 2. 检查秒杀是否可以进行
            String checkResult = checkSeckillAvailable(goodsId);
            if (checkResult != null) {
                logger.warn("秒杀不可用，userId: {}, goodsId: {}, reason: {}", userId, goodsId, checkResult);
                return SeckillResultDTO.fail(checkResult);
            }
            
            // 3. 检查用户是否已经秒杀成功
            if (isUserSeckillSuccess(userId, goodsId)) {
                logger.warn("重复秒杀，userId: {}, goodsId: {}", userId, goodsId);
                return SeckillResultDTO.fail(SeckillStatusEnum.REPEAT_SECKILL.getMessage());
            }
            
            // 4. Redis预减库存（原子操作）
            Long stock = redisUtil.decrementStock(goodsId);
            if (stock < 0) {
                // 库存不足，回滚Redis库存
                redisUtil.incrementStock(goodsId);
                logger.warn("库存不足，userId: {}, goodsId: {}", userId, goodsId);
                return SeckillResultDTO.fail(SeckillStatusEnum.STOCK_NOT_ENOUGH.getMessage());
            }
            
            try {
                // 5. 标记用户秒杀成功（防止重复秒杀）
                redisUtil.markUserSeckillSuccess(userId, goodsId);
                
                // 6. 发送秒杀消息到消息队列，异步处理下单
                SeckillGoodsDTO goodsDTO = getSeckillGoodsDetail(goodsId);
                SeckillMessageDTO messageDTO = new SeckillMessageDTO(
                    userId, 
                    goodsId, 
                    goodsDTO.getGoodsName(), 
                    goodsDTO.getSeckillPrice(), 
                    1
                );
                messageDTO.setMessageId(UUID.randomUUID().toString());
                
                messageQueueUtil.sendSeckillMessage(messageDTO);
                
                logger.info("秒杀消息已发送，userId: {}, goodsId: {}", userId, goodsId);
                
                // 7. 返回处理中状态，让客户端轮询结果
                return SeckillResultDTO.processing("秒杀请求已提交，正在处理中，请稍后查询结果");
                
            } catch (Exception e) {
                // 发生异常，回滚Redis库存
                redisUtil.incrementStock(goodsId);
                logger.error("秒杀处理异常，回滚库存，userId: {}, goodsId: {}", userId, goodsId, e);
                throw e;
            }
            
        } catch (Exception e) {
            logger.error("执行秒杀操作失败，userId: {}, goodsId: {}", userId, goodsId, e);
            // 降级处理
            return seckillFallbackService.seckillFallback(userId, goodsId);
        }
    }

    @Override
    public SeckillResultDTO getSeckillResult(Integer userId, Long goodsId) {
        logger.info("查询秒杀结果，userId: {}, goodsId: {}", userId, goodsId);
        
        try {
            // 1. 检查用户是否已经秒杀成功
            if (!isUserSeckillSuccess(userId, goodsId)) {
                // 检查Redis中是否有库存标记
                String stockKey = SeckillConstants.SECKILL_GOODS_STOCK_KEY_PREFIX + goodsId;
                Integer stock = redisUtil.getSeckillStock(goodsId);
                if (stock != null && stock < 0) {
                    return SeckillResultDTO.fail(SeckillStatusEnum.STOCK_NOT_ENOUGH.getMessage());
                }
                return SeckillResultDTO.processing("秒杀结果正在处理中，请稍后再试");
            }
            
            // 2. 查询订单信息（这里简化处理，实际应该查询订单表）
            // 实际项目中应该从数据库查询订单信息
            SeckillGoodsDTO goodsDTO = getSeckillGoodsDetail(goodsId);
            
            return SeckillResultDTO.success(
                1L,  // 订单ID（实际应该从数据库获取）
                goodsId,
                goodsDTO.getGoodsName(),
                goodsDTO.getSeckillPrice(),
                new Date(),
                userId,
                "SK" + System.currentTimeMillis()  // 订单号
            );
            
        } catch (Exception e) {
            logger.error("查询秒杀结果失败，userId: {}, goodsId: {}", userId, goodsId, e);
            // 降级处理
            return seckillFallbackService.getSeckillResultFallback(userId, goodsId);
        }
    }

    @Override
    public boolean initSeckillStock(Long goodsId, Integer stockCount) {
        logger.info("初始化秒杀商品库存，goodsId: {}, stockCount: {}", goodsId, stockCount);
        
        try {
            // 1. 更新数据库库存
            SeckillGoods goods = seckillGoodsMapper.selectByGoodsId(goodsId);
            if (goods == null) {
                throw new RuntimeException("秒杀商品不存在");
            }
            
            goods.setStockCount(stockCount);
            seckillGoodsMapper.updateByPrimaryKeySelective(goods);
            
            // 2. 初始化Redis库存
            redisUtil.setSeckillStock(goodsId, stockCount);
            
            // 3. 清除相关缓存
            String cacheKey = SeckillConstants.SECKILL_GOODS_INFO_KEY_PREFIX + goodsId;
            redisUtil.delete(cacheKey);
            
            String listCacheKey = "seckill:goods:list";
            redisUtil.delete(listCacheKey);
            
            logger.info("初始化秒杀商品库存成功，goodsId: {}, stockCount: {}", goodsId, stockCount);
            return true;
            
        } catch (Exception e) {
            logger.error("初始化秒杀商品库存失败，goodsId: {}, stockCount: {}", goodsId, stockCount, e);
            return false;
        }
    }

    @Override
    public boolean isUserSeckillSuccess(Integer userId, Long goodsId) {
        return redisUtil.isUserSeckillSuccess(userId, goodsId);
    }

    @Override
    public String checkSeckillAvailable(Long goodsId) {
        try {
            SeckillGoodsDTO goodsDTO = getSeckillGoodsDetail(goodsId);
            
            // 检查状态
            if (goodsDTO.getStatus() != 1) {
                return "秒杀商品已下架";
            }
            
            // 检查时间
            Date now = new Date();
            if (now.before(goodsDTO.getStartTime())) {
                return SeckillStatusEnum.NOT_STARTED.getMessage();
            }
            if (now.after(goodsDTO.getEndTime())) {
                return SeckillStatusEnum.ENDED.getMessage();
            }
            
            // 检查库存
            Integer stock = redisUtil.getSeckillStock(goodsId);
            if (stock == null) {
                // Redis中没有库存信息，从数据库获取并初始化
                if (goodsDTO.getStockCount() <= 0) {
                    return SeckillStatusEnum.STOCK_NOT_ENOUGH.getMessage();
                }
                // 初始化Redis库存
                initSeckillStock(goodsId, goodsDTO.getStockCount());
            } else if (stock <= 0) {
                return SeckillStatusEnum.STOCK_NOT_ENOUGH.getMessage();
            }
            
            return null;  // 可以进行秒杀
            
        } catch (Exception e) {
            logger.error("检查秒杀可用性失败，goodsId: {}", goodsId, e);
            return "系统异常，请稍后再试";
        }
    }

    @Override
    public boolean checkLimit(String key, int limit) {
        return redisUtil.checkLimit(key, limit);
    }
}
