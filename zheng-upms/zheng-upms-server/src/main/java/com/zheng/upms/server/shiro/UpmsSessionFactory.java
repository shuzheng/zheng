package com.zheng.upms.server.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * session工厂
 * Created by shuzheng on 2017/2/27.
 */
public class UpmsSessionFactory implements SessionFactory {

    @Override
    public Session createSession(SessionContext sessionContext) {
        UpmsSession session = new UpmsSession();
        if (sessionContext != null && sessionContext instanceof WebSessionContext) {
            WebSessionContext webSessionContext = (WebSessionContext) sessionContext;
            HttpServletRequest request = (HttpServletRequest) webSessionContext.getServletRequest();
            if (request != null) {
                session.setHost(request.getRemoteAddr());
                session.setUserAgent(request.getHeader("User-Agent"));
            }
        }
        return session;
    }

    /**
     * 获取ip工具类，除了getRemoteAddr，其他ip均可伪造
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("Cdn-Src-Ip");    // 网宿cdn的真实ip
        if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");   // 蓝讯cdn的真实ip
        }
        if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");  // 获取代理ip
        }
        if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP"); // 获取代理ip
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP"); // 获取代理ip
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr(); // 获取真实ip
        }
        return ip;
    }

}
