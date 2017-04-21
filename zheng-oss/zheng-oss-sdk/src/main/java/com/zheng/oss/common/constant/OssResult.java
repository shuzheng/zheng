package com.zheng.oss.common.constant;

import com.zheng.common.base.BaseResult;

/**
 * oss系统常量枚举类
 * Created by shuzheng on 2017/4/18.
 */
public class OssResult extends BaseResult {

    public OssResult(OssResultConstant ossResultConstant, Object data) {
        super(ossResultConstant.getCode(), ossResultConstant.getMessage(), data);
    }

}
