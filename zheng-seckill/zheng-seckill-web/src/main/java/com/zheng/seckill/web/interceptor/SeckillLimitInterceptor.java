package com.zheng.seckill.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.zheng.common.util.RedisUtil;
import com.zheng.seckill.web.annotation.SeckillLimit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 秒杀限流防刷拦截器
 * 用于防止恶意刷接口和限流
 */
public class SeckillLimitInterceptor implements HandlerInterceptor {
    
    private static final Logger logger = LoggerFactory.getLogger(SeckillLimitInterceptor.class);
    
    private static final String BLACKLIST_PREFIX = "seckill:blacklist:";
    private static final String ACCESS_COUNT_PREFIX = "seckill:access:count:";
    private static final String BLACKLIST_DURATION = "seckill:blacklist:duration";
    
    private int blacklistDuration = 60;
    private int maxBlacklistCount = 10;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            SeckillLimit seckillLimit = handlerMethod.getMethodAnnotation(SeckillLimit.class);
            
            if (seckillLimit == null) {
                return true;
            }
            
            String userId = getUserId(request);
            
            if (seckillLimit.needLogin() && userId == null) {
                renderError(response, 401, "用户未登录，请先登录");
                return false;
            }
            
            String ip = getIpAddress(request);
            String key = generateKey(userId, ip, request.getRequestURI());
            
            if (isBlacklisted(key)) {
                renderError(response, 429, "操作过于频繁，请稍后再试");
                return false;
            }
            
            boolean allowed = checkAccessLimit(key, seckillLimit.seconds(), seckillLimit.maxCount());
            
            if (!allowed) {
                if (seckillLimit.level() == SeckillLimit.LimitLevel.STRICT) {
                    recordAccessCount(key);
                }
                renderError(response, 429, "请求过于频繁，请稍后再试");
                return false;
            }
            
            return true;
        }
        
        return true;
    }

    private String getUserId(HttpServletRequest request) {
        Object userIdObj = request.getAttribute("userId");
        if (userIdObj != null) {
            return String.valueOf(userIdObj);
        }
        
        Object sessionUserId = request.getSession().getAttribute("userId");
        if (sessionUserId != null) {
            return String.valueOf(sessionUserId);
        }
        
        String token = request.getHeader("Authorization");
        if (token != null && !token.isEmpty()) {
            return token;
        }
        
        return null;
    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }

    private String generateKey(String userId, String ip, String uri) {
        if (userId != null) {
            return "user:" + userId + ":" + uri;
        }
        return "ip:" + ip + ":" + uri;
    }

    private boolean isBlacklisted(String key) {
        String blacklistKey = BLACKLIST_PREFIX + key;
        String value = RedisUtil.get(blacklistKey);
        return value != null && !value.isEmpty();
    }

    private boolean checkAccessLimit(String key, int seconds, int maxCount) {
        String countKey = ACCESS_COUNT_PREFIX + key;
        
        Long count = RedisUtil.incr(countKey);
        if (count == null) {
            return true;
        }
        
        if (count == 1) {
            RedisUtil.set(countKey, "1", seconds);
        }
        
        return count <= maxCount;
    }

    private void recordAccessCount(String key) {
        String blacklistCountKey = BLACKLIST_PREFIX + "count:" + key;
        Long count = RedisUtil.incr(blacklistCountKey);
        
        if (count == 1) {
            RedisUtil.set(blacklistCountKey, "1", 3600);
        }
        
        if (count >= maxBlacklistCount) {
            String blacklistKey = BLACKLIST_PREFIX + key;
            RedisUtil.set(blacklistKey, "1", blacklistDuration);
            logger.warn("用户/IP被加入黑名单，key: {}, 违规次数: {}", key, count);
        }
    }

    private void renderError(HttpServletResponse response, int code, String message) throws IOException {
        response.setStatus(200);
        response.setContentType("application/json;charset=UTF-8");
        
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("message", message);
        result.put("data", null);
        
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(result));
        writer.flush();
        writer.close();
    }

    public void setBlacklistDuration(int blacklistDuration) {
        this.blacklistDuration = blacklistDuration;
    }

    public void setMaxBlacklistCount(int maxBlacklistCount) {
        this.maxBlacklistCount = maxBlacklistCount;
    }
}
