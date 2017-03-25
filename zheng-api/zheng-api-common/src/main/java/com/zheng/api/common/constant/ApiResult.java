package com.zheng.api.common.constant;

import com.zheng.common.base.BaseResult;

/**
 * api系统常量枚举类
 * Created by shuzheng on 2017/2/19.
 */
public class ApiResult extends BaseResult {

    public ApiResult(int code, String message, Object data) {
        super(code, message, data);
    }

    public ApiResult(ApiResultConstant apiResultConstant, Object data) {
        super(apiResultConstant.getCode(), apiResultConstant.getMessage(), data);
    }

}
