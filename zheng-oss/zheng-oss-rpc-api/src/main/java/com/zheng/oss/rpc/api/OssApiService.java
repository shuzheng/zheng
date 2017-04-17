package com.zheng.oss.rpc.api;

import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

import java.io.File;

/**
 * oss系统接口
 * Created by shuzheng on 2017/4/18.
 */
public interface OssApiService {

    /**
     * 上传指定文件到OSS中指定的{@link Bucket}。
     * @param bucketName Bucket名称。
     * @param key object的key。
     * @param file 指定上传文件。
     * @param metadata object的元信息{@link ObjectMetadata}，若该元信息未包含Content-Length，则采用chunked编码传输请求数据。
     * @return
     */
    PutObjectResult putObject(String bucketName, String key, File file, ObjectMetadata metadata);

    /**
     * 删除指定的{@link OSSObject}。
     * @param bucketName Bucket名称。
     * @param key Object key。
     */
    void deleteObject(String bucketName, String key);

}
