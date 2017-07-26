package com.zheng.ucenter.rpc.service.impl;

import com.zheng.ucenter.rpc.api.UcenterApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UcenterApiService实现
 * Created by shuzheng on 2017/6/19.
 */
@Service
@Transactional
public class UcenterApiServiceImpl implements UcenterApiService {

    private static Logger _log = LoggerFactory.getLogger(UcenterApiServiceImpl.class);

}