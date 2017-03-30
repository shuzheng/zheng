package com.zheng.pay.rpc.api;

import com.zheng.common.base.BaseServiceMock;
import com.zheng.pay.dao.mapper.PayVestMapper;
import com.zheng.pay.dao.model.PayVest;
import com.zheng.pay.dao.model.PayVestExample;

/**
* 降级实现PayVestService接口
* Created by shuzheng on 2017/3/29.
*/
public class PayVestServiceMock extends BaseServiceMock<PayVestMapper, PayVest, PayVestExample> implements PayVestService {

}
