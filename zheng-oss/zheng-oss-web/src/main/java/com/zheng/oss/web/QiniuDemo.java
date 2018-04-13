package com.zheng.oss.web;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.IOException;

/**
 * Created by shuzheng on 2016/11/12.
 */
public class QiniuDemo {
	//设置好账号的ACCESS_KEY和SECRET_KEY
	String accessKey = "";
	String secretKey = "";
	//要上传的空间
	String bucketname = "zheng";
	//上传到七牛后保存的文件名
	String key = "my-java.png";
	//上传文件的路径
	String filePath = "C:\\Users\\admin\\Pictures\\zsz\\20161108161228.png";

	//密钥配置
	Auth auth = Auth.create(accessKey, secretKey);
	//创建上传对象
	UploadManager uploadManager = new UploadManager();

	//简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public String getUpToken(){
		return auth.uploadToken(bucketname);
	}

	public void upload() throws IOException {
		try {
			//调用put方法上传
			Response res = uploadManager.put(filePath, key, getUpToken());
			//打印返回的信息
			System.out.println(res.bodyString());
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			System.out.println(r.toString());
			try {
				//响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				//ignore
			}
		}
	}
	public static void main(String[] args) throws IOException {
		new QiniuDemo().upload();
	}
}
