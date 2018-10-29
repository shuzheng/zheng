package com.zheng.ucenter.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务启动类
 * Created by ZhangShuzheng on 2017/6/19.
 */
public class ZhengUcenterRpcServiceApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZhengUcenterRpcServiceApplication.class);

	public static void main(String[] args) {
		LOGGER.info(">>>>> zheng-ucenter-rpc-service 正在启动 <<<<<");
		new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
		LOGGER.info(">>>>> zheng-ucenter-rpc-service 启动完成 <<<<<");
	}

}
