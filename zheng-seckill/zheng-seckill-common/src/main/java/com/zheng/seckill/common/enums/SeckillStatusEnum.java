package com.zheng.seckill.common.enums;

/**
 * 秒杀状态枚举
 */
public enum SeckillStatusEnum {
    
    /**
     * 秒杀成功
     */
    SUCCESS(1, "秒杀成功"),
    
    /**
     * 秒杀失败
     */
    FAILED(0, "秒杀失败"),
    
    /**
     * 处理中
     */
    PROCESSING(2, "处理中"),
    
    /**
     * 库存不足
     */
    STOCK_NOT_ENOUGH(-1, "库存不足"),
    
    /**
     * 重复秒杀
     */
    REPEAT_SECKILL(-2, "重复秒杀"),
    
    /**
     * 秒杀未开始
     */
    NOT_STARTED(-3, "秒杀未开始"),
    
    /**
     * 秒杀已结束
     */
    ENDED(-4, "秒杀已结束"),
    
    /**
     * 限流
     */
    LIMIT_EXCEEDED(-5, "限流"),
    
    /**
     * 系统异常
     */
    SYSTEM_ERROR(-6, "系统异常");

    private final int code;
    private final String message;

    SeckillStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static SeckillStatusEnum getByCode(int code) {
        for (SeckillStatusEnum status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }
}
