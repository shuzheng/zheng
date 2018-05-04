package com.zheng.pay.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务启动类 Created by ZhangShuzheng on 2017/3/29.
 */
public class ZhengPayRpcServiceApplication {

	private static Logger logger = LoggerFactory.getLogger(ZhengPayRpcServiceApplication.class);

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		logger.info(">>>>> zheng-pay-rpc-service 正在启动 <<<<<");
		new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
		logger.info(">>>>> zheng-pay-rpc-service 启动完成 <<<<<");
	}

}
