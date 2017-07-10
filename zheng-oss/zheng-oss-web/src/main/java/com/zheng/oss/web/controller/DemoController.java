package com.zheng.oss.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.zheng.common.base.BaseController;
import com.zheng.oss.common.constant.OssConstant;
import com.zheng.oss.web.service.AliyunOssService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

/**
 * oss测试
 * Created by shuzheng on 2017/4/18.
 */
@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private AliyunOssService aliyunOssService;

    @Autowired
    private OSSClient aliyunOssClient;

    @GetMapping("/aliyun/upload1")
    public String upload1() {
        PutObjectResult putObjectResult = aliyunOssClient.putObject(OssConstant.ALIYUN_OSS_BUCKET_NAME, "text.txt", new ByteArrayInputStream("Hello OSS".getBytes()));
        return "success";
    }

    @GetMapping("/aliyun/upload2")
    public String upload2() throws FileNotFoundException {
        File file = new File("d:\\zheng.png");
        PutObjectResult putObjectResult = aliyunOssClient.putObject(OssConstant.ALIYUN_OSS_BUCKET_NAME, "file.png", file);
        return "success";
    }

    @GetMapping("/aliyun/download1")
    public String download1() throws IOException {
        StringBuffer result = new StringBuffer();
        OSSObject ossObject = aliyunOssClient.getObject(OssConstant.ALIYUN_OSS_BUCKET_NAME, "text.txt");
        InputStream content = ossObject.getObjectContent();
        if (content != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                result.append("\n" + line);
            }
            content.close();
        }
        return result.toString();
    }

    @GetMapping("/aliyun/download2")
    public String download2() throws IOException {
        return "http://" + OssConstant.ALIYUN_OSS_BUCKET_NAME + "." + OssConstant.ALIYUN_OSS_ENDPOINT + "/file.png";
    }

    @GetMapping("/aliyun/upload")
    public String upload(Model model) {
        JSONObject policy = aliyunOssService.policy();
        _log.info("policy={}", policy);
        model.addAttribute("policy", policy);
        return thymeleaf("/aliyun/upload");
    }

}
