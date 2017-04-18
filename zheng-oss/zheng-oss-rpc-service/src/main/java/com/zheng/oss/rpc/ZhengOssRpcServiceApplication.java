package com.zheng.oss.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务启动类
 * Created by ZhangShuzheng on 2017/4/18.
 */
public class ZhengOssRpcServiceApplication {

	private static Logger _log = LoggerFactory.getLogger(ZhengOssRpcServiceApplication.class);

	public static void main(String[] args) {
		_log.info(">>>>> zheng-oss-rpc-service 正在启动 <<<<<");
		new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
		_log.info(">>>>> zheng-oss-rpc-service 启动完成 <<<<<");
	}

}
