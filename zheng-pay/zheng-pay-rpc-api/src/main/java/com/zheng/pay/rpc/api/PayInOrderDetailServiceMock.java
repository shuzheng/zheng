package com.zheng.pay.rpc.api;

import com.zheng.common.base.BaseServiceMock;
import com.zheng.pay.dao.mapper.PayInOrderDetailMapper;
import com.zheng.pay.dao.model.PayInOrderDetail;
import com.zheng.pay.dao.model.PayInOrderDetailExample;

/**
* 降级实现PayInOrderDetailService接口
* Created by shuzheng on 2017/3/29.
*/
public class PayInOrderDetailServiceMock extends BaseServiceMock<PayInOrderDetailMapper, PayInOrderDetail, PayInOrderDetailExample> implements PayInOrderDetailService {

}
