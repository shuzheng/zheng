package com.zheng.oss.common.constant;

/**
 * oss系统接口结果常量枚举类
 * Created by shuzheng on 2017/4/18.
 */
public enum OssResultConstant {

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

    OssResultConstant(int code, String message) {
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
