package com.zheng.upms.client.filter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by shuzheng on 2016/12/10.
 */
public class SSOFilter implements Filter {

    private static Logger _log = LoggerFactory.getLogger(SSOFilter.class);

    private String SYSTEM_NAME = "system_name";
    private String SSO_SERVER_URL = "sso_server_url";
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        _log.info("浏览器sessionId：{}", session.getId());

        // 已登录
        if (null != session.getAttribute("isLogin")) {
            filterChain.doFilter(request, response);
            return;
        }
        // 未登录
        else {
            // 判断是否有是认证中心验证后回跳
            String token = request.getParameter("token");
            // 无token，跳到认证中心登录
            if (StringUtils.isEmpty(token)) {
                // 跳转sso-server认证中心，并带上回调地址和系统名称参数
                // 认证中心地址
                StringBuffer sso_server_url = new StringBuffer(filterConfig.getInitParameter(SSO_SERVER_URL));
                sso_server_url.append("/sso");
                // 参数system_name
                sso_server_url.append("?").append(SYSTEM_NAME).append("=").append(filterConfig.getInitParameter(SYSTEM_NAME));
                // 参数backurl
                StringBuffer backurl = request.getRequestURL();
                String queryString = request.getQueryString();
                if (!StringUtils.isEmpty(queryString)) {
                    backurl.append("?").append(queryString);
                }
                sso_server_url.append("&").append("backurl").append("=").append(URLEncoder.encode(backurl.toString(), "utf-8"));
                _log.info("未登录，跳转认证中心:{}", sso_server_url);
                response.sendRedirect(sso_server_url.toString());
            }
            // 已拿到token
            else {
                // HttpPost去校验token
                // ... 默认校验正确

                // token校验正确，创建局部会话
                session.setAttribute("isLogin", true);
                filterChain.doFilter(request, response);
                return;
            }
        }

    }

    @Override
    public void destroy() {

    }

}
