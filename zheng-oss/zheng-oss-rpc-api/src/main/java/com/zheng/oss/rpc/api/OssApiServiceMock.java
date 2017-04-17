package com.zheng.oss.rpc.api;

import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * 降级实现OssApiService接口
 * Created by shuzheng on 2017/4/18.
 */
public class OssApiServiceMock implements OssApiService {

    private static Logger _log = LoggerFactory.getLogger(OssApiServiceMock.class);

    @Override
    public PutObjectResult putObject(String bucketName, String key, File file, ObjectMetadata metadata) {
        _log.info("OssApiServiceMock => putObject");
        return null;
    }

    @Override
    public void deleteObject(String bucketName, String key) {
        _log.info("OssApiServiceMock => deleteObject");
    }

}
