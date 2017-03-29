package com.zheng.pay.rpc.api;

import com.zheng.common.base.BaseServiceMock;
import com.zheng.pay.dao.mapper.PayTypeMapper;
import com.zheng.pay.dao.model.PayType;
import com.zheng.pay.dao.model.PayTypeExample;

/**
* 降级实现PayTypeService接口
* Created by shuzheng on 2017/3/29.
*/
public class PayTypeServiceMock extends BaseServiceMock<PayTypeMapper, PayType, PayTypeExample> implements PayTypeService {

}
