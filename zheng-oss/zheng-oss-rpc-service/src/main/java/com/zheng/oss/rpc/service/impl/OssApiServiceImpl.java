package com.zheng.oss.rpc.service.impl;

import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.zheng.oss.rpc.api.OssApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

/**
* OssApiService实现
* Created by shuzheng on 2017/4/18.
*/
@Service
@Transactional
public class OssApiServiceImpl implements OssApiService {

    private static Logger _log = LoggerFactory.getLogger(OssApiServiceImpl.class);


    @Override
    public PutObjectResult putObject(String bucketName, String key, File file, ObjectMetadata metadata) {
        return null;
    }

    @Override
    public void deleteObject(String bucketName, String key) {

    }

}