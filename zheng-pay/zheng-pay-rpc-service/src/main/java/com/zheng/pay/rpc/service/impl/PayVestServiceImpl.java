package com.zheng.pay.rpc.service.impl;

import com.zheng.common.annotation.BaseService;
import com.zheng.common.base.BaseServiceImpl;
import com.zheng.pay.dao.mapper.PayVestMapper;
import com.zheng.pay.dao.model.PayVest;
import com.zheng.pay.dao.model.PayVestExample;
import com.zheng.pay.rpc.api.PayVestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* PayVestService实现
* Created by shuzheng on 2017/3/29.
*/
@Service
@Transactional
@BaseService
public class PayVestServiceImpl extends BaseServiceImpl<PayVestMapper, PayVest, PayVestExample> implements PayVestService {

    private static Logger _log = LoggerFactory.getLogger(PayVestServiceImpl.class);

    @Autowired
    PayVestMapper payVestMapper;

}