package com.zheng.cms.rpc.service.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * spring容器初始化完成事件
 * Created by shuzheng on 2017/1/7.
 */
public class ApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {

    private static Logger _log = LoggerFactory.getLogger(ApplicationContextListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // root application context
        if(null == contextRefreshedEvent.getApplicationContext().getParent()) {
            _log.info(">>>>> spring初始化完毕");
            // spring初始化完毕后，通过反射调用所有service的initMapper方法
            Map<String, Object> services = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(Service.class);
            for(Object service : services.values()) {
                try {
                    Method initMapper = service.getClass().getDeclaredMethod("initMapper");
                    initMapper.invoke(service);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            _log.info(">>>>> 初始化完成service的initMapper方法");
        }
    }

}
