package com.zheng.api.rpc.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 降级实现ApiService接口
 * Created by shuzheng on 2017/2/19.
 */
public class ApiServiceMock implements ApiService {

    private static Logger logger = LoggerFactory.getLogger(ApiServiceMock.class);

    @Override
    public String hello(String name) {
        logger.info("ApiServiceMock => hello");
        return null;
    }

}
