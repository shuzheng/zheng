package com.zheng.ucenter.rpc.service.impl;

import com.zheng.common.annotation.BaseService;
import com.zheng.common.base.BaseServiceImpl;
import com.zheng.ucenter.dao.mapper.UcenterUserDetailsMapper;
import com.zheng.ucenter.dao.model.UcenterUserDetails;
import com.zheng.ucenter.dao.model.UcenterUserDetailsExample;
import com.zheng.ucenter.rpc.api.UcenterUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UcenterUserDetailsService实现
* Created by shuzheng on 2017/4/27.
*/
@Service
@Transactional
@BaseService
public class UcenterUserDetailsServiceImpl extends BaseServiceImpl<UcenterUserDetailsMapper, UcenterUserDetails, UcenterUserDetailsExample> implements UcenterUserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UcenterUserDetailsServiceImpl.class);

    @Autowired
    UcenterUserDetailsMapper ucenterUserDetailsMapper;

}