package com.zheng.seckill.rpc.api;

import com.zheng.seckill.common.dto.SeckillGoodsDTO;
import com.zheng.seckill.common.dto.SeckillResultDTO;
import com.zheng.seckill.dao.model.SeckillGoods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 秒杀核心服务Mock实现
 * 用于测试和降级场景
 */
@Service
public class SeckillApiServiceMock implements SeckillApiService {
    
    private static final Logger logger = LoggerFactory.getLogger(SeckillApiServiceMock.class);

    @Override
    public List<SeckillGoodsDTO> getSeckillGoodsList() {
        logger.warn("调用秒杀服务Mock实现 - getSeckillGoodsList");
        List<SeckillGoodsDTO> list = new ArrayList<>();
        
        SeckillGoodsDTO goods1 = new SeckillGoodsDTO();
        goods1.setGoodsId(1L);
        goods1.setGoodsName("Mock秒杀商品1");
        goods1.setOriginalPrice(new BigDecimal("100.00"));
        goods1.setSeckillPrice(new BigDecimal("10.00"));
        goods1.setStockCount(100);
        goods1.setStatus(1);
        list.add(goods1);
        
        SeckillGoodsDTO goods2 = new SeckillGoodsDTO();
        goods2.setGoodsId(2L);
        goods2.setGoodsName("Mock秒杀商品2");
        goods2.setOriginalPrice(new BigDecimal("200.00"));
        goods2.setSeckillPrice(new BigDecimal("20.00"));
        goods2.setStockCount(200);
        goods2.setStatus(1);
        list.add(goods2);
        
        return list;
    }

    @Override
    public SeckillGoodsDTO getSeckillGoodsDetail(Long goodsId) {
        logger.warn("调用秒杀服务Mock实现 - getSeckillGoodsDetail, goodsId: {}", goodsId);
        SeckillGoodsDTO goods = new SeckillGoodsDTO();
        goods.setGoodsId(goodsId);
        goods.setGoodsName("Mock秒杀商品" + goodsId);
        goods.setOriginalPrice(new BigDecimal("100.00"));
        goods.setSeckillPrice(new BigDecimal("10.00"));
        goods.setStockCount(100);
        goods.setStatus(1);
        goods.setStartTime(new Date(System.currentTimeMillis() - 3600000));
        goods.setEndTime(new Date(System.currentTimeMillis() + 3600000));
        return goods;
    }

    @Override
    public SeckillResultDTO executeSeckill(Integer userId, Long goodsId) {
        logger.warn("调用秒杀服务Mock实现 - executeSeckill, userId: {}, goodsId: {}", userId, goodsId);
        return SeckillResultDTO.processing("秒杀服务正在维护中，请稍后再试");
    }

    @Override
    public SeckillResultDTO getSeckillResult(Integer userId, Long goodsId) {
        logger.warn("调用秒杀服务Mock实现 - getSeckillResult, userId: {}, goodsId: {}", userId, goodsId);
        return SeckillResultDTO.processing("秒杀结果查询服务正在维护中，请稍后再试");
    }

    @Override
    public boolean initSeckillStock(Long goodsId, Integer stockCount) {
        logger.warn("调用秒杀服务Mock实现 - initSeckillStock, goodsId: {}, stockCount: {}", goodsId, stockCount);
        return false;
    }

    @Override
    public boolean isUserSeckillSuccess(Integer userId, Long goodsId) {
        logger.warn("调用秒杀服务Mock实现 - isUserSeckillSuccess, userId: {}, goodsId: {}", userId, goodsId);
        return false;
    }

    @Override
    public String checkSeckillAvailable(Long goodsId) {
        logger.warn("调用秒杀服务Mock实现 - checkSeckillAvailable, goodsId: {}", goodsId);
        return "秒杀服务正在维护中";
    }

    @Override
    public boolean checkLimit(String key, int limit) {
        logger.warn("调用秒杀服务Mock实现 - checkLimit, key: {}, limit: {}", key, limit);
        return true;
    }
}
