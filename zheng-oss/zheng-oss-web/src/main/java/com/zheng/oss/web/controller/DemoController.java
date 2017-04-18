package com.zheng.oss.web.controller;

import com.aliyun.oss.OSSClient;
import com.zheng.oss.rpc.api.OssApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayInputStream;

/**
 * Created by shuzheng on 2017/4/18.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private OssApiService ossApiService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public String index(ModelMap modelMap) {
        // endpoint以杭州为例，其它region请按实际情况填写
        String endpoint = "oss-cn-shanghai.aliyuncs.com";
        // accessKey请登录https://ak-console.aliyun.com/#/查看
        String accessKeyId = "LTAIf5dDIthJN3h0";
        String accessKeySecret = "2IZVHc1Qzxul8rC0ZQGKCEjjnpvm5d";

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
