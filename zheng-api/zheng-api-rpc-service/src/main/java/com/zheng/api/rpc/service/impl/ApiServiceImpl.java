package com.zheng.api.rpc.service.impl;

import com.zheng.api.rpc.api.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 实现ApiService接口
 * Created by shuzheng on 2017/2/19.
 */
public class ApiServiceImpl implements ApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiServiceImpl.class);

    @Override
    public String hello(String name) {
        return "hello," + name + "!";
    }

}
