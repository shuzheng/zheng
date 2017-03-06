package com.zheng.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * 启动解压zhengAdmin-x.x.x.jar到resources目录
 * Created by shuzheng on 2016/12/18.
 */
public class ZhengAdminUtil implements InitializingBean, ServletContextAware {

    private static Logger _log = LoggerFactory.getLogger(ZhengAdminUtil.class);

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        _log.info("===== 开始解压zheng-ui =====");
        String version = PropertiesFileUtil.getInstance().get("zheng-ui.version");
        _log.info("zheng-ui.jar 版本: {}", version);
        String jarPath = servletContext.getRealPath("/WEB-INF/lib/zheng-ui-" + version + ".jar");
        _log.info("zheng-ui.jar 包路径: {}", jarPath);
        String resources = servletContext.getRealPath("/") + "/resources/zheng-ui";
        _log.info("zheng-ui.jar 解压到: {}", resources);
        JarUtil.decompress(jarPath, resources);
        _log.info("===== 解压zheng-ui完成 =====");
    }

}
