package com.zheng.message.sdk;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;

/**
 * 推送工具类
 * Created by shuzheng on 2018/6/28.
 */
public class MessageHelper {

    /**
     * 推送消息
     * @param url
     * @param publishDto
     * @return
     */
    public BaseResult publish(String url, PublishDto publishDto) {
        HttpPost httpPost = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();

            httpPost = new HttpPost(url);
            httpPost.setHeader("Content-type", "application/json; charset=utf-8");

            HttpEntity httpEntity = new StringEntity(JSONObject.toJSONString(publishDto), "utf-8");
            httpPost.setEntity(httpEntity);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity resEntity = httpResponse.getEntity();
                if (resEntity != null) {
                    String result = EntityUtils.toString(resEntity, Charset.forName("utf-8"));
                    return JSONObject.parseObject(result, BaseResult.class);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
        return new BaseResult(-1, "error", "publish error");
    }

    public static void main(String[] args) {
        String url = "http://localhost:8881/message/publish";
        PublishDto publishDto = new PublishDto();
        publishDto.setType(MessageConstants.PUBLISH_TYPE_BROADCAST);
        publishDto.setNamespace("/demo");
        publishDto.setAccessKey("1");
        publishDto.setPayload("hi");
        BaseResult result = new MessageHelper().publish(url, publishDto);
        System.out.println(result.message);
    }

}
