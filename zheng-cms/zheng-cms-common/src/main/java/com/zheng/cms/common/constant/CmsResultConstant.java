package com.zheng.cms.common.constant;

/**
 * cms系统接口结果常量枚举类
 * Created by shuzheng on 2017/2/19.
 */
public enum CmsResultConstant {

    /**
     * 失败
     */
    FAILED(0, "failed"),

    /**
     * 成功
     */
    SUCCESS(1, "success"),

    /**
     * 文件类型不支持
     */
    FILE_TYPE_ERROR(20001, "File type not supported!"),

    /**
     * 无效长度
     */
    INVALID_LENGTH(20002, "Invalid length"),

    /**
     * 无效参数
     */
    INVALID_PARAMETER(20003, "Invalid parameter");

    public int code;

    public String message;

    CmsResultConstant(int code, String message) {
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
