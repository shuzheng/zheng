package com.zheng.ucenter.rpc.service.impl;

import com.zheng.common.annotation.BaseService;
import com.zheng.common.base.BaseServiceImpl;
import com.zheng.ucenter.dao.mapper.UcenterUserLogMapper;
import com.zheng.ucenter.dao.model.UcenterUserLog;
import com.zheng.ucenter.dao.model.UcenterUserLogExample;
import com.zheng.ucenter.rpc.api.UcenterUserLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UcenterUserLogService实现
* Created by shuzheng on 2017/4/27.
*/
@Service
@Transactional
@BaseService
public class UcenterUserLogServiceImpl extends BaseServiceImpl<UcenterUserLogMapper, UcenterUserLog, UcenterUserLogExample> implements UcenterUserLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UcenterUserLogServiceImpl.class);

    @Autowired
    UcenterUserLogMapper ucenterUserLogMapper;

}