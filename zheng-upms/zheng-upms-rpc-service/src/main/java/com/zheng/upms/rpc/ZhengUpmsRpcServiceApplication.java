package com.zheng.upms.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务启动类
 * Created by ZhangShuzheng on 2017/2/3.
 */
public class ZhengUpmsRpcServiceApplication {

	private static Logger _log = LoggerFactory.getLogger(ZhengUpmsRpcServiceApplication.class);

	public static void main(String[] args) {
		_log.info(">>>>> zheng-upms-rpc-service 正在启动 <<<<<");
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

        for(String beanName : beanDefinitionNames){
            System.out.println("beanName = " + beanName);
        }

//        int[] a = new int[5];
//        try {
//            Thread.sleep(1000 * 1200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        _log.info(">>>>> zheng-upms-rpc-service 启动完成 <<<<<");
	}

}
