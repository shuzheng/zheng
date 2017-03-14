package com.zheng.upms.common.constant;

/**
 * upms系统接口结果常量枚举类
 * Created by shuzheng on 2017/2/18.
 */
public enum UpmsResultConstant {

    FAILED(0, "failed"),
    SUCCESS(1, "success"),

    INVALID_LENGTH(10001, "Invalid length"),

    EMPTY_USERNAME(10101, "Username cannot be empty"),
    EMPTY_PASSWORD(10102, "Password cannot be empty"),
    INVALID_USERNAME(10103, "Account does not exist"),
    INVALID_PASSWORD(10104, "Password error"),
    INVALID_ACCOUNT(10105, "Invalid account");

    public int code;

    public String message;

    UpmsResultConstant(int code, String message) {
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
