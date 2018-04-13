package com.zheng.ucenter.web.interceptor;

import com.zheng.common.util.PropertiesFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 公共拦截器
 * Created by shuzheng on 2017/3/25.
 */
public class UcenterWebInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(UcenterWebInterceptor.class);

//    @Autowired
//    private CmsMenuService cmsMenuService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 过滤ajax
        if (null != request.getHeader("X-Requested-With") && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
            return true;
        }
        // zheng-ui静态资源配置信息
        String appName = PropertiesFileUtil.getInstance().get("app.name");
        String uiPath = PropertiesFileUtil.getInstance().get("zheng.ui.path");
        request.setAttribute("appName", appName);
        request.setAttribute("uiPath", uiPath);
//        // 菜单
//        CmsMenuExample cmsMenuExample = new CmsMenuExample();
//        cmsMenuExample.setOrderByClause("orders asc");
//        List<CmsMenu> menus = cmsMenuService.selectByExample(cmsMenuExample);
//        request.setAttribute("menus", menus);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

}
