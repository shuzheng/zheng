package com.zheng.pay.rpc.api;

import com.zheng.common.base.BaseServiceMock;
import com.zheng.pay.dao.mapper.PayMchMapper;
import com.zheng.pay.dao.model.PayMch;
import com.zheng.pay.dao.model.PayMchExample;

/**
* 降级实现PayMchService接口
* Created by shuzheng on 2017/3/29.
*/
public class PayMchServiceMock extends BaseServiceMock<PayMchMapper, PayMch, PayMchExample> implements PayMchService {

}
