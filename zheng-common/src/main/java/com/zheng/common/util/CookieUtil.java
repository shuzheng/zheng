package com.zheng.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie操作工具类
 * Created by shuzheng on 2016/12/31.
 */
public class CookieUtil {

    /**
     * 设置cookie
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void setCookie(HttpServletResponse response, String name, String value, String path, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(path);
        if (maxAge > 0) cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
        setCookie(response, name, value, "/", maxAge);
    }
    public static void setCookie(HttpServletResponse response, String name, String value) {
        setCookie(response, name, value, "/", 3600);
    }
    public static void setCookie(HttpServletResponse response, String name) {
        setCookie(response, name, "", "/", 3600);
    }

    /**
     * 获取cookie
     * @param request
     * @param name
     * @return
     */
    public static String getCookie(HttpServletRequest request, String name) {
        String value = null;
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    value = cookie.getValue();
                }
            }
        }
        return value;
    }

    /**
     * 删除cookie
     * @param response
     * @param name
     * @return
     */
    public static void removeCookie(HttpServletResponse response, String name) {
        setCookie(response, name, "", "/", 0);
    }

}
