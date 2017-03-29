package com.zheng.pay.rpc.api;

import com.zheng.common.base.BaseServiceMock;
import com.zheng.pay.dao.mapper.PayPayMapper;
import com.zheng.pay.dao.model.PayPay;
import com.zheng.pay.dao.model.PayPayExample;

/**
* 降级实现PayPayService接口
* Created by shuzheng on 2017/3/29.
*/
public class PayPayServiceMock extends BaseServiceMock<PayPayMapper, PayPay, PayPayExample> implements PayPayService {

}
