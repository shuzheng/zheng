package com.zheng.oss.web.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

/**
 * Created by shuzheng on 2017/4/18.
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private OSSClient aliyunOssClient;

    @GetMapping("/aliyun/put")
    public String index() {
        for (int i = 0; i < 10; i++) {
            PutObjectResult putObjectResult = aliyunOssClient.putObject("shuzheng", "test" + i, new ByteArrayInputStream(("Hello OSS" + i).getBytes()));
            System.out.println(putObjectResult);
        }
        return "success";
    }

}
