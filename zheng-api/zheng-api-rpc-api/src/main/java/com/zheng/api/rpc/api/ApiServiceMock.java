package com.zheng.api.rpc.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 降级实现ApiService接口
 * Created by shuzheng on 2017/2/19.
 */
public class ApiServiceMock implements ApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiServiceMock.class);

    @Override
    public String hello(String name) {
        LOGGER.info("ApiServiceMock => hello");
        return null;
    }

}
