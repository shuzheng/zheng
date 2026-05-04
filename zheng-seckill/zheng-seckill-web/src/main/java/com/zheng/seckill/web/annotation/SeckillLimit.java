package com.zheng.seckill.web.annotation;

import java.lang.annotation.*;

/**
 * 限流防刷注解
 * 用于标记需要进行限流和防刷的接口
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SeckillLimit {
    
    /**
     * 限流时间窗口（秒）
     * 默认5秒
     */
    int seconds() default 5;
    
    /**
     * 最大访问次数
     * 默认5次
     */
    int maxCount() default 5;
    
    /**
     * 是否需要登录
     * 默认需要
     */
    boolean needLogin() default true;
    
    /**
     * 防刷级别
     * NORMAL: 正常限流
     * STRICT: 严格防刷，短时间内多次访问会被拉黑
     */
    LimitLevel level() default LimitLevel.NORMAL;
    
    enum LimitLevel {
        NORMAL,
        STRICT
    }
}
