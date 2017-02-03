package com.zheng.upms.rpc.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务启动类
 * Created by ZhangShuzheng on 2017/2/3.
 */
public class ZhengUpmsRpcServiceApplication {

	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
		System.out.println(">>>>> zheng-upms-rpc-service 启动完成 <<<<<");
	}

}
