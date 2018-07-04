package com.zheng.message.sdk;


/**
 * 推送消息
 * Created by shuzheng on 2018/6/20.
 */
public class PublishDto {

    // 推送类型："broadcast/topics/clients" <=> 广播、按主题、点对点
    private String type;

    // 推送命名空间
    private String namespace;

    // 鉴权key
    private String accessKey;

    // 推送订阅主题的订阅者
    private String[] topics;

    // 推送客户端
    private String[] clients;

    // 消息体
    private Object payload;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String[] getTopics() {
        return topics;
    }

    public void setTopics(String[] topics) {
        this.topics = topics;
    }

    public String[] getClients() {
        return clients;
    }

    public void setClients(String[] clients) {
        this.clients = clients;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

}
