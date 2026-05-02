package com.zheng.seckill.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.zheng.seckill.common.dto.SeckillResultDTO;
import com.zheng.seckill.rpc.api.SeckillApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
     * @param goodsId 商品ID
     * @param request HTTP请求
     * @return 秒杀结果
     */
    @RequestMapping(value = "/execute/{goodsId}", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject executeSeckill(@PathVariable Long goodsId, HttpServletRequest request) {
        logger.info("执行秒杀操作，goodsId: {}", goodsId);
        JSONObject result = new JSONObject();
        
        try {
            // 1. 验证参数
            if (goodsId == null) {
                result.put("code", 400);
                result.put("message", "商品ID不能为空");
                result.put("data", null);
                return result;
            }
            
            // 2. 获取当前登录用户ID（从SSO鉴权中获取）
            // 实际项目中应该从Session或Token中获取用户ID
            // 这里简化处理，假设用户ID已经通过SSO鉴权并存储在request中
            Integer userId = getCurrentUserId(request);
            
            if (userId == null) {
                result.put("code", 401);
                result.put("message", "用户未登录，请先登录");
                result.put("data", null);
                return result;
            }
            
            // 3. 执行秒杀操作
            SeckillResultDTO seckillResult = seckillApiService.executeSeckill(userId, goodsId);
            
            // 4. 构建返回结果
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
     * @param goodsId 商品ID
     * @param request HTTP请求
     * @return 秒杀结果
     */
    @RequestMapping(value = "/result/{goodsId}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getSeckillResult(@PathVariable Long goodsId, HttpServletRequest request) {
        logger.info("查询秒杀结果，goodsId: {}", goodsId);
        JSONObject result = new JSONObject();
        
        try {
            // 1. 验证参数
            if (goodsId == null) {
                result.put("code", 400);
                result.put("message", "商品ID不能为空");
                result.put("data", null);
                return result;
            }
            
            // 2. 获取当前登录用户ID
            Integer userId = getCurrentUserId(request);
            
            if (userId == null) {
                result.put("code", 401);
                result.put("message", "用户未登录，请先登录");
                result.put("data", null);
                return result;
            }
            
            // 3. 查询秒杀结果
            SeckillResultDTO seckillResult = seckillApiService.getSeckillResult(userId, goodsId);
            
            // 4. 构建返回结果
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
     * @param goodsId 商品ID
     * @param request HTTP请求
     * @return 检查结果
     */
    @RequestMapping(value = "/check-success/{goodsId}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject checkUserSeckillSuccess(@PathVariable Long goodsId, HttpServletRequest request) {
        logger.info("检查用户是否已经秒杀成功，goodsId: {}", goodsId);
        JSONObject result = new JSONObject();
        
        try {
            // 1. 验证参数
            if (goodsId == null) {
                result.put("code", 400);
                result.put("message", "商品ID不能为空");
                result.put("data", null);
                return result;
            }
            
            // 2. 获取当前登录用户ID
            Integer userId = getCurrentUserId(request);
            
            if (userId == null) {
                result.put("code", 401);
                result.put("message", "用户未登录，请先登录");
                result.put("data", null);
                return result;
            }
            
            // 3. 检查用户是否已经秒杀成功
            boolean success = seckillApiService.isUserSeckillSuccess(userId, goodsId);
            
            // 4. 构建返回结果
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
        // 实际项目中应该从Session或Token中获取用户ID
        // 这里简化处理，假设用户ID存储在request的attribute中
        // 或者从SSO的认证信息中获取
        
        // 示例：从request中获取用户ID
        Object userIdObj = request.getAttribute("userId");
        if (userIdObj != null) {
            return (Integer) userIdObj;
        }
        
        // 示例：从Session中获取用户ID
        Object sessionUserId = request.getSession().getAttribute("userId");
        if (sessionUserId != null) {
            return (Integer) sessionUserId;
        }
        
        // 测试用：如果没有获取到用户ID，返回null表示未登录
        // 实际项目中应该抛出异常或返回401
        return null;
    }
}
