package com.zheng.upms.client.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.zheng.common.util.PropertiesFileUtil;
import com.zheng.common.util.RedisUtil;
import com.zheng.upms.client.shiro.session.UpmsSessionDao;
import com.zheng.upms.client.util.RequestParameterUtil;
import com.zheng.upms.common.constant.UpmsConstant;
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
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 重写authc过滤器
 * Created by shuzheng on 2017/3/11.
 */
public class UpmsAuthenticationFilter extends AuthenticationFilter {

    private final static Logger _log = LoggerFactory.getLogger(UpmsAuthenticationFilter.class);

    // 局部会话key
    private final static String ZHENG_UPMS_CLIENT_SESSION_ID = "zheng-upms-client-session-id";
    // 单点同一个code所有局部会话key
    private final static String ZHENG_UPMS_CLIENT_SESSION_IDS = "zheng-upms-client-session-ids";

    @Autowired
    UpmsSessionDao upmsSessionDao;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        // 判断请求类型
        String upmsType = PropertiesFileUtil.getInstance("zheng-upms-client").get("zheng.upms.type");
        session.setAttribute(UpmsConstant.UPMS_TYPE, upmsType);
        if ("client".equals(upmsType)) {
            return validateClient(request, response);
        }
        if ("server".equals(upmsType)) {
            return subject.isAuthenticated();
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        StringBuffer sso_server_url = new StringBuffer(PropertiesFileUtil.getInstance("zheng-upms-client").get("zheng.upms.sso.server.url"));
        // server需要登录
        String upmsType = PropertiesFileUtil.getInstance("zheng-upms-client").get("zheng.upms.type");
        if ("server".equals(upmsType)) {
            WebUtils.toHttp(response).sendRedirect(sso_server_url.append("/sso/login").toString());
            return false;
        }
        sso_server_url.append("/sso/index").append("?").append("appid").append("=").append(PropertiesFileUtil.getInstance("zheng-upms-client").get("zheng.upms.appID"));
        // 回跳地址
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        StringBuffer backurl = httpServletRequest.getRequestURL();
        String queryString = httpServletRequest.getQueryString();
        if (StringUtils.isNotBlank(queryString)) {
            backurl.append("?").append(queryString);
        }
        sso_server_url.append("&").append("backurl").append("=").append(URLEncoder.encode(backurl.toString(), "utf-8"));
        WebUtils.toHttp(response).sendRedirect(sso_server_url.toString());
        return false;
    }

    /**
     * 认证中心登录成功带回code
     * @param request
     */
    private boolean validateClient(ServletRequest request, ServletResponse response) {
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        int timeOut = (int) session.getTimeout() / 1000;
        // 判断局部会话是否登录
        String cacheClientSession = RedisUtil.get(ZHENG_UPMS_CLIENT_SESSION_ID + "_" + session.getId());
        if (StringUtils.isNotBlank(cacheClientSession)) {
            // 更新code有效期
            RedisUtil.set(ZHENG_UPMS_CLIENT_SESSION_ID + "_" + sessionId, cacheClientSession, timeOut);
            Jedis jedis = RedisUtil.getJedis();
            jedis.expire(ZHENG_UPMS_CLIENT_SESSION_IDS + "_" + cacheClientSession, timeOut);
            jedis.close();
            // 移除url中的code参数
            if (null != request.getParameter("code")) {
                String backUrl = RequestParameterUtil.getParameterWithOutCode(WebUtils.toHttp(request));
                HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
                try {
                    httpServletResponse.sendRedirect(backUrl.toString());
                } catch (IOException e) {
                    _log.error("局部会话已登录，移除code参数跳转出错：", e);
                }
            } else {
                return true;
            }
        }
        // 判断是否有认证中心code
        String code = request.getParameter("upms_code");
        // 已拿到code
        if (StringUtils.isNotBlank(code)) {
            // HttpPost去校验code
            try {
                StringBuffer sso_server_url = new StringBuffer(PropertiesFileUtil.getInstance("zheng-upms-client").get("zheng.upms.sso.server.url"));
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(sso_server_url.toString() + "/sso/code");

                List<NameValuePair> nameValuePairs = new ArrayList<>();
                nameValuePairs.add(new BasicNameValuePair("code", code));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse httpResponse = httpclient.execute(httpPost);
                if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity httpEntity = httpResponse.getEntity();
                    JSONObject result = JSONObject.parseObject(EntityUtils.toString(httpEntity));
                    if (1 == result.getIntValue("code") && result.getString("data").equals(code)) {
                        // code校验正确，创建局部会话
                        RedisUtil.set(ZHENG_UPMS_CLIENT_SESSION_ID + "_" + sessionId, code, timeOut);
                        // 保存code对应的局部会话sessionId，方便退出操作
                        RedisUtil.sadd(ZHENG_UPMS_CLIENT_SESSION_IDS + "_" + code, sessionId, timeOut);
                        _log.debug("当前code={}，对应的注册系统个数：{}个", code, RedisUtil.getJedis().scard(ZHENG_UPMS_CLIENT_SESSION_IDS + "_" + code));
                        // 移除url中的token参数
                        String backUrl = RequestParameterUtil.getParameterWithOutCode(WebUtils.toHttp(request));
                        // 返回请求资源
                        try {
                            // client无密认证
                            String username = request.getParameter("upms_username");
                            subject.login(new UsernamePasswordToken(username, ""));
                            HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
                            httpServletResponse.sendRedirect(backUrl.toString());
                            return true;
                        } catch (IOException e) {
                            _log.error("已拿到code，移除code参数跳转出错：", e);
                        }
                    } else {
                        _log.warn(result.getString("data"));
                    }
                }
            } catch (IOException e) {
                _log.error("验证token失败：", e);
            }
        }
        return false;
    }

}
