package com.zheng.upms.admin.util;

import com.zheng.common.util.JarUtil;
import com.zheng.common.util.PropertiesFileUtil;
import com.zheng.upms.admin.controller.SSOController;
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
        _log.info("===== 开始解压zhengAdmin =====");
        String version = PropertiesFileUtil.getInstance().get("zhengAdmin.version");
        _log.info("zhengAdmin.jar 版本: {}", version);
        String jarPath = servletContext.getRealPath("/WEB-INF/lib/zhengAdmin-" + version + ".jar");
        _log.info("zhengAdmin.jar 包路径: {}", jarPath);
        String resources = servletContext.getRealPath("/resources");
        _log.info("zhengAdmin.jar 解压到: {}", resources);
        JarUtil.decompress(jarPath, resources);
        _log.info("===== 解压zhengAdmin完成 =====");
    }

}
