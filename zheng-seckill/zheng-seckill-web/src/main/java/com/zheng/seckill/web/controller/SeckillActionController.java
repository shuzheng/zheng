package com.zheng.seckill.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.zheng.seckill.common.dto.SeckillResultDTO;
import com.zheng.seckill.rpc.api.SeckillApiService;
import com.zheng.seckill.web.annotation.SeckillLimit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 秒杀操作Controller
 * 提供秒杀相关的RESTful API
 * 需要SSO鉴权才能访问
 */
@Controller
@RequestMapping("/seckill/action")
public class SeckillActionController {
    
    private static final Logger logger = LoggerFactory.getLogger(SeckillActionController.class);
    
    @Autowired
    private SeckillApiService seckillApiService;

    /**
     * 执行秒杀操作
     * 限流策略：5秒内最多3次，严格防刷
     * @param goodsId 商品ID
     * @param request HTTP请求
     * @return 秒杀结果
     */
    @SeckillLimit(seconds = 5, maxCount = 3, needLogin = true, level = SeckillLimit.LimitLevel.STRICT)
    @RequestMapping(value = "/execute/{goodsId}", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject executeSeckill(@PathVariable Long goodsId, HttpServletRequest request) {
        logger.info("执行秒杀操作，goodsId: {}", goodsId);
        JSONObject result = new JSONObject();
        
        try {
            if (goodsId == null) {
                result.put("code", 400);
                result.put("message", "商品ID不能为空");
                result.put("data", null);
                return result;
            }
            
            Integer userId = getCurrentUserId(request);
            
            if (userId == null) {
                result.put("code", 401);
                result.put("message", "用户未登录，请先登录");
                result.put("data", null);
                return result;
            }
            
            SeckillResultDTO seckillResult = seckillApiService.executeSeckill(userId, goodsId);
            
            result.put("code", 200);
            result.put("message", seckillResult.getMessage());
            result.put("data", seckillResult);
            
        } catch (Exception e) {
            logger.error("执行秒杀操作失败，goodsId: {}", goodsId, e);
            result.put("code", 500);
            result.put("message", "秒杀失败：" + e.getMessage());
            result.put("data", null);
        }
        
        return result;
    }

    /**
     * 查询秒杀结果
     * 限流策略：5秒内最多10次，正常限流
     * @param goodsId 商品ID
     * @param request HTTP请求
     * @return 秒杀结果
     */
    @SeckillLimit(seconds = 5, maxCount = 10, needLogin = true, level = SeckillLimit.LimitLevel.NORMAL)
    @RequestMapping(value = "/result/{goodsId}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getSeckillResult(@PathVariable Long goodsId, HttpServletRequest request) {
        logger.info("查询秒杀结果，goodsId: {}", goodsId);
        JSONObject result = new JSONObject();
        
        try {
            if (goodsId == null) {
                result.put("code", 400);
                result.put("message", "商品ID不能为空");
                result.put("data", null);
                return result;
            }
            
            Integer userId = getCurrentUserId(request);
            
            if (userId == null) {
                result.put("code", 401);
                result.put("message", "用户未登录，请先登录");
                result.put("data", null);
                return result;
            }
            
            SeckillResultDTO seckillResult = seckillApiService.getSeckillResult(userId, goodsId);
            
            result.put("code", 200);
            result.put("message", seckillResult.getMessage());
            result.put("data", seckillResult);
            
        } catch (Exception e) {
            logger.error("查询秒杀结果失败，goodsId: {}", goodsId, e);
            result.put("code", 500);
            result.put("message", "查询秒杀结果失败：" + e.getMessage());
            result.put("data", null);
        }
        
        return result;
    }

    /**
     * 检查用户是否已经秒杀成功
     * 限流策略：5秒内最多5次，正常限流
     * @param goodsId 商品ID
     * @param request HTTP请求
     * @return 检查结果
     */
    @SeckillLimit(seconds = 5, maxCount = 5, needLogin = true, level = SeckillLimit.LimitLevel.NORMAL)
    @RequestMapping(value = "/check-success/{goodsId}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject checkUserSeckillSuccess(@PathVariable Long goodsId, HttpServletRequest request) {
        logger.info("检查用户是否已经秒杀成功，goodsId: {}", goodsId);
        JSONObject result = new JSONObject();
        
        try {
            if (goodsId == null) {
                result.put("code", 400);
                result.put("message", "商品ID不能为空");
                result.put("data", null);
                return result;
            }
            
            Integer userId = getCurrentUserId(request);
            
            if (userId == null) {
                result.put("code", 401);
                result.put("message", "用户未登录，请先登录");
                result.put("data", null);
                return result;
            }
            
            boolean success = seckillApiService.isUserSeckillSuccess(userId, goodsId);
            
            result.put("code", 200);
            result.put("message", success ? "用户已秒杀成功" : "用户未秒杀成功");
            result.put("data", success);
            
        } catch (Exception e) {
            logger.error("检查用户是否秒杀成功失败，goodsId: {}", goodsId, e);
            result.put("code", 500);
            result.put("message", "检查失败：" + e.getMessage());
            result.put("data", null);
        }
        
        return result;
    }

    /**
     * 获取当前登录用户ID
     * 实际项目中应该从SSO鉴权中获取
     * @param request HTTP请求
     * @return 用户ID
     */
    private Integer getCurrentUserId(HttpServletRequest request) {
        Object userIdObj = request.getAttribute("userId");
        if (userIdObj != null) {
            return (Integer) userIdObj;
        }
        
        Object sessionUserId = request.getSession().getAttribute("userId");
        if (sessionUserId != null) {
            return (Integer) sessionUserId;
        }
        
        return null;
    }
}
