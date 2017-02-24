package com.zheng.upms.client.filter;

import com.zheng.common.util.CookieUtil;
import com.zheng.common.util.RedisUtil;
import com.zheng.upms.client.filter.util.RequestParameterUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuzheng on 2016/12/10.
 */
public class SSOFilter implements Filter {

    private final static Logger _log = LoggerFactory.getLogger(SSOFilter.class);

    // 局部会话key
    private final static String ZHENG_UPMS_CLIENT_SESSION_ID = "zheng-upms-client-session-id";
    // 单点同一个token所有局部会话key
    private final static String ZHENG_UPMS_CLIENT_SESSION_IDS = "zheng-upms-client-session-ids";
    private String SYSTEM_NAME = "system_name";
    private String SSO_SERVER_URL = "sso_server_url";
    private String SSO_DEBUG = "sso_debug";
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 是否开发模式，为true则直接放行
        String sso_debug = filterConfig.getInitParameter(SSO_DEBUG);
        if (null != sso_debug && "true".equals(sso_debug)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 分配子系统登录sessionId，首次获取后缓存到cookie，防止session丢失
        String clientSessionId = CookieUtil.getCookie(request, ZHENG_UPMS_CLIENT_SESSION_ID);
        if (StringUtils.isBlank(clientSessionId)) {
            clientSessionId = request.getSession().getId();
            CookieUtil.setCookie(response, ZHENG_UPMS_CLIENT_SESSION_ID, clientSessionId);
        }

        // 判断局部会话是否登录
        if (null != clientSessionId && !StringUtils.isBlank(RedisUtil.get(ZHENG_UPMS_CLIENT_SESSION_ID + "_" + clientSessionId))) {
            // 移除url中的token参数
            if (null != request.getParameter("token")) {
                String backUrl = RequestParameterUtil.getParameterWithOutToken(request);
                response.sendRedirect(backUrl.toString());
            } else {
                filterChain.doFilter(request, response);
            }
            return;
        }
        // 未登录
        else {
            // 认证中心地址
            StringBuffer sso_server_url = new StringBuffer(filterConfig.getInitParameter(SSO_SERVER_URL));
            // 判断是否有认证中心token
            String token = request.getParameter("token");
            // 已拿到token
            if (!StringUtils.isBlank(token)) {
                // HttpPost去校验token
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(sso_server_url.toString() + "/sso/token");

                    List<NameValuePair> nvps = new ArrayList<>();
                    nvps.add(new BasicNameValuePair("token", token));
                    httpPost.setEntity(new UrlEncodedFormEntity(nvps));

                    HttpResponse httpResponse = httpclient.execute(httpPost);
                    if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        HttpEntity httpEntity = httpResponse.getEntity();
                        String result = EntityUtils.toString(httpEntity);
                        if (result.equals("success")) {
                            // token校验正确，创建局部会话
                            RedisUtil.set(ZHENG_UPMS_CLIENT_SESSION_ID + "_" + clientSessionId, token);
                            // 保存token对应的局部会话sessionId，方便退出登录操作
                            RedisUtil.getJedis().sadd(ZHENG_UPMS_CLIENT_SESSION_IDS + "_" + token, clientSessionId);
                            _log.debug("当前token={}，对应的注册系统个数：{}个", token, RedisUtil.getJedis().scard(ZHENG_UPMS_CLIENT_SESSION_IDS + "_" + token));
                            // 移除url中的token参数
                            String backUrl = RequestParameterUtil.getParameterWithOutToken(request);
                            // 返回请求资源
                            response.sendRedirect(backUrl.toString());
                            return;
                        }
                    }
                } catch (IOException e) {
                    _log.error("验证token失败：", e);
                }
                // 跳转认证中心登录页
            }
            // 无token，跳转sso-server认证中心登录，并带上回调地址和系统名称参数
            sso_server_url.append("/sso/index").append("?").append(SYSTEM_NAME).append("=").append(filterConfig.getInitParameter(SYSTEM_NAME));
            StringBuffer backurl = request.getRequestURL();
            String queryString = request.getQueryString();
            if (!StringUtils.isBlank(queryString)) {
                backurl.append("?").append(queryString);
            }
            sso_server_url.append("&").append("backurl").append("=").append(URLEncoder.encode(backurl.toString(), "utf-8"));

            _log.debug("未登录，跳转认证中心:{}", sso_server_url);
            response.sendRedirect(sso_server_url.toString());
        }

    }

    @Override
    public void destroy() {

    }

}
