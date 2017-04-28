package com.zheng.oss.web.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.PutObjectResult;
import com.zheng.common.util.PropertiesFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.*;

/**
 * oss测试
 * Created by shuzheng on 2017/4/18.
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private OSSClient aliyunOssClient;

    private String endPoint = PropertiesFileUtil.getInstance("zheng-oss-client").get("aliyun.oss.endpoint");
    private String bucketName = PropertiesFileUtil.getInstance("zheng-oss-client").get("aliyun.oss.bucketName");

    @GetMapping("/aliyun/upload1")
    public String upload1() {
        PutObjectResult putObjectResult = aliyunOssClient.putObject(bucketName, "text.txt", new ByteArrayInputStream("Hello OSS".getBytes()));
        return "success";
    }

    @GetMapping("/aliyun/upload2")
    public String upload2() throws FileNotFoundException {
        File file = new File("C:\\Users\\shuzheng\\Documents\\zheng.png");
        PutObjectResult putObjectResult = aliyunOssClient.putObject(bucketName, "file.png", file);
        return "success";
    }

    @GetMapping("/aliyun/download1")
    public String download1() throws IOException {
        StringBuffer result = new StringBuffer();
        OSSObject ossObject = aliyunOssClient.getObject(bucketName, "text.txt");
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
        return "http://" + bucketName + "." + endPoint + "/file.png";
    }

}
