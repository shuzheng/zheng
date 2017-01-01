package com.zheng.upms.client.filter;

import com.zheng.common.util.CookieUtil;
import com.zheng.common.util.RedisUtil;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuzheng on 2016/12/10.
 */
public class SSOFilter implements Filter {

    private final static Logger _log = LoggerFactory.getLogger(SSOFilter.class);

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
        // 分配单点登录sessionId，不使用session获取会话id，改为cookie，防止session丢失
        String sessionId = request.getSession().getId();

        // 已登录
        if (!StringUtils.isEmpty(RedisUtil.get(sessionId + "_token"))) {
            filterChain.doFilter(request, response);
            return;
        }
        // 未登录
        else {
            // 认证中心地址
            StringBuffer sso_server_url = new StringBuffer(filterConfig.getInitParameter(SSO_SERVER_URL));
            sso_server_url.append("/sso");
            // 判断是否有认证中心token
            String token = request.getParameter("token");
            // 已拿到token
            if (!StringUtils.isEmpty(token)) {
                // HttpPost去校验token
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(sso_server_url.toString() + "/token");

                    List<NameValuePair> nvps = new ArrayList<>();
                    nvps.add(new BasicNameValuePair("token", token));
                    httpPost.setEntity(new UrlEncodedFormEntity(nvps));

                    HttpResponse httpResponse = httpclient.execute(httpPost);
                    if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        HttpEntity httpEntity = httpResponse.getEntity();
                        String result = EntityUtils.toString(httpEntity);
                        if (result.equals("success")) {
                            // token校验正确，创建局部会话
                            RedisUtil.set(sessionId + "_token", token);
                            // 保存token对应的局部会话sessionId，方便退出登录操作
                            RedisUtil.getJedis().sadd(token + "_subSessionIds", sessionId);
                            _log.info("当前token={}，对应的注册系统有：{}个", token, RedisUtil.getJedis().scard(token + "_subSessionIds"));
                            // 移除url中的token参数
                            // TODO
                            // 返回请求资源
                            filterChain.doFilter(request, response);
                            return;
                        }
                    }
                } catch (IOException e) {
                    _log.error("验证token失败：", e);
                }
                // 跳转认证中心登录页
            }
            // 无token，跳转sso-server认证中心登录，并带上回调地址和系统名称参数
            sso_server_url.append("?").append(SYSTEM_NAME).append("=").append(filterConfig.getInitParameter(SYSTEM_NAME));
            StringBuffer backurl = request.getRequestURL();
            String queryString = request.getQueryString();
            if (!StringUtils.isEmpty(queryString)) {
                backurl.append("?").append(queryString);
            }
            sso_server_url.append("&").append("backurl").append("=").append(URLEncoder.encode(backurl.toString(), "utf-8"));

            _log.info("未登录，跳转认证中心:{}", sso_server_url);
            response.sendRedirect(sso_server_url.toString());
        }

    }

    @Override
    public void destroy() {

    }

}
