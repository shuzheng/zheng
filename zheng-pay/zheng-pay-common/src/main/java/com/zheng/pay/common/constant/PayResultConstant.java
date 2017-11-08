package com.zheng.pay.common.constant;

/**
 * pay系统接口结果常量枚举类
 * Created by shuzheng on 2017/3/29.
 */
public enum PayResultConstant {

    /**
     * 失败
     */
    FAILED(0, "failed"),

    /**
     * 成功
     */
    SUCCESS(1, "success");

    public int code;

    public String message;

    PayResultConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
