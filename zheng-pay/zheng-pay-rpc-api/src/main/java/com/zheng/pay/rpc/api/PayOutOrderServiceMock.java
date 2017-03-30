package com.zheng.pay.rpc.api;

import com.zheng.common.base.BaseServiceMock;
import com.zheng.pay.dao.mapper.PayOutOrderMapper;
import com.zheng.pay.dao.model.PayOutOrder;
import com.zheng.pay.dao.model.PayOutOrderExample;

/**
* 降级实现PayOutOrderService接口
* Created by shuzheng on 2017/3/29.
*/
public class PayOutOrderServiceMock extends BaseServiceMock<PayOutOrderMapper, PayOutOrder, PayOutOrderExample> implements PayOutOrderService {

}
