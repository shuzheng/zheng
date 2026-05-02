package com.zheng.seckill.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.zheng.seckill.common.dto.SeckillGoodsDTO;
import com.zheng.seckill.common.dto.SeckillResultDTO;
import com.zheng.seckill.rpc.api.SeckillApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 秒杀商品Controller
 * 提供秒杀商品相关的RESTful API
 */
@Controller
@RequestMapping("/seckill/goods")
public class SeckillGoodsController {
    
    private static final Logger logger = LoggerFactory.getLogger(SeckillGoodsController.class);
    
    @Autowired
    private SeckillApiService seckillApiService;

    /**
     * 获取秒杀商品列表
     * @return 秒杀商品列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getSeckillGoodsList(HttpServletRequest request) {
        logger.info("获取秒杀商品列表");
        JSONObject result = new JSONObject();
        
        try {
            List<SeckillGoodsDTO> goodsList = seckillApiService.getSeckillGoodsList();
            
            result.put("code", 200);
            result.put("message", "success");
            result.put("data", goodsList);
            
        } catch (Exception e) {
            logger.error("获取秒杀商品列表失败", e);
            result.put("code", 500);
            result.put("message", "获取秒杀商品列表失败：" + e.getMessage());
            result.put("data", null);
        }
        
        return result;
    }

    /**
     * 获取秒杀商品详情
     * @param goodsId 商品ID
     * @return 秒杀商品详情
     */
    @RequestMapping(value = "/detail/{goodsId}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getSeckillGoodsDetail(@PathVariable Long goodsId, HttpServletRequest request) {
        logger.info("获取秒杀商品详情，goodsId: {}", goodsId);
        JSONObject result = new JSONObject();
        
        try {
            if (goodsId == null) {
                result.put("code", 400);
                result.put("message", "商品ID不能为空");
                result.put("data", null);
                return result;
            }
            
            SeckillGoodsDTO goodsDTO = seckillApiService.getSeckillGoodsDetail(goodsId);
            
            result.put("code", 200);
            result.put("message", "success");
            result.put("data", goodsDTO);
            
        } catch (Exception e) {
            logger.error("获取秒杀商品详情失败，goodsId: {}", goodsId, e);
            result.put("code", 500);
            result.put("message", "获取秒杀商品详情失败：" + e.getMessage());
            result.put("data", null);
        }
        
        return result;
    }

    /**
     * 检查秒杀是否可以进行
     * @param goodsId 商品ID
     * @return 检查结果
     */
    @RequestMapping(value = "/check/{goodsId}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject checkSeckillAvailable(@PathVariable Long goodsId, HttpServletRequest request) {
        logger.info("检查秒杀是否可以进行，goodsId: {}", goodsId);
        JSONObject result = new JSONObject();
        
        try {
            if (goodsId == null) {
                result.put("code", 400);
                result.put("message", "商品ID不能为空");
                result.put("data", null);
                return result;
            }
            
            String checkResult = seckillApiService.checkSeckillAvailable(goodsId);
            
            if (checkResult == null) {
                result.put("code", 200);
                result.put("message", "可以进行秒杀");
                result.put("data", true);
            } else {
                result.put("code", 400);
                result.put("message", checkResult);
                result.put("data", false);
            }
            
        } catch (Exception e) {
            logger.error("检查秒杀可用性失败，goodsId: {}", goodsId, e);
            result.put("code", 500);
            result.put("message", "检查秒杀可用性失败：" + e.getMessage());
            result.put("data", null);
        }
        
        return result;
    }

    /**
     * 初始化秒杀商品库存（管理接口）
     * @param goodsId 商品ID
     * @param stockCount 库存数量
     * @return 初始化结果
     */
    @RequestMapping(value = "/init-stock", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject initSeckillStock(@RequestParam Long goodsId, 
                                        @RequestParam Integer stockCount,
                                        HttpServletRequest request) {
        logger.info("初始化秒杀商品库存，goodsId: {}, stockCount: {}", goodsId, stockCount);
        JSONObject result = new JSONObject();
        
        try {
            if (goodsId == null || stockCount == null || stockCount < 0) {
                result.put("code", 400);
                result.put("message", "参数错误");
                result.put("data", null);
                return result;
            }
            
            boolean success = seckillApiService.initSeckillStock(goodsId, stockCount);
            
            if (success) {
                result.put("code", 200);
                result.put("message", "初始化库存成功");
                result.put("data", true);
            } else {
                result.put("code", 500);
                result.put("message", "初始化库存失败");
                result.put("data", false);
            }
            
        } catch (Exception e) {
            logger.error("初始化秒杀商品库存失败，goodsId: {}, stockCount: {}", goodsId, stockCount, e);
            result.put("code", 500);
            result.put("message", "初始化库存失败：" + e.getMessage());
            result.put("data", null);
        }
        
        return result;
    }
}
