package com.zheng.oss.web.service;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.zheng.common.util.PropertiesFileUtil;
import com.zheng.oss.common.constant.OssConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ZhangShuzheng on 2017/5/15.
 */
@Service
public class AliyunOssService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AliyunOssService.class);

	@Autowired
	private OSSClient aliyunOssClient;

	/**
	 * 签名生成
	 * @return
	 */
	public JSONObject policy() {
		JSONObject result = new JSONObject();
		// 存储目录
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dir = sdf.format(new Date());
		// 签名有效期
		long expireEndTime = System.currentTimeMillis() + OssConstant.ALIYUN_OSS_EXPIRE * 1000;
		Date expiration = new Date(expireEndTime);
		// 文件大小
		long maxSize = OssConstant.ALIYUN_OSS_MAX_SIZE * 1024 * 1024;
		// 回调
		JSONObject callback = new JSONObject();
		callback.put("callbackUrl", PropertiesFileUtil.getInstance("config").get("aliyun.oss.callback"));
		callback.put("callbackBody", "filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
		callback.put("callbackBodyType", "application/x-www-form-urlencoded");
		// 提交节点
		String action = "http://" + OssConstant.ALIYUN_OSS_BUCKET_NAME + "." + OssConstant.ALIYUN_OSS_ENDPOINT;
		try {
			PolicyConditions policyConds = new PolicyConditions();
			policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, maxSize);
			policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
			String postPolicy = aliyunOssClient.generatePostPolicy(expiration, policyConds);
			byte[] binaryData = postPolicy.getBytes("utf-8");
			String policy = BinaryUtil.toBase64String(binaryData);
			String signature = aliyunOssClient.calculatePostSignature(postPolicy);
			String callbackData = BinaryUtil.toBase64String(callback.toString().getBytes("utf-8"));
			// 返回结果
			result.put("OSSAccessKeyId", aliyunOssClient.getCredentialsProvider().getCredentials().getAccessKeyId());
			result.put("policy", policy);
			result.put("signature", signature);
			result.put("dir", dir);
			result.put("callback", callbackData);
			result.put("action", action);
		} catch (Exception e) {
			LOGGER.error("签名生成失败", e);
		}
		return result;
	}

}
