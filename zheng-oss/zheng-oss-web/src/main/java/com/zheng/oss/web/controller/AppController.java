package com.zheng.oss.web.controller;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayInputStream;

/**
 * Created by shuzheng on 2016/11/12.
 */
@Controller
@RequestMapping("/app")
public class AppController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public String index(ModelMap modelMap) {
        // endpoint以杭州为例，其它region请按实际情况填写
        String endpoint = "";
        // accessKey请登录https://ak-console.aliyun.com/#/查看
        String accessKeyId = "";
        String accessKeySecret = "";

        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // 使用访问OSS
        String content = "Hello OSS";
        ossClient.putObject("shuzheng", "test", new ByteArrayInputStream(content.getBytes()));

        // 关闭ossClient
        ossClient.shutdown();
        return "success";
    }

}
