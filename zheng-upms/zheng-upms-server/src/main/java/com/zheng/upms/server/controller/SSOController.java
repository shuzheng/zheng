package com.zheng.upms.server.controller;

import com.zheng.common.base.BaseController;
import com.zheng.common.util.CookieUtil;
import com.zheng.common.util.RedisUtil;
import com.zheng.upms.server.util.SystemConstant;
import com.zheng.upms.dao.model.UpmsSystemExample;
import com.zheng.upms.rpc.api.UpmsSystemService;
import com.zheng.upms.rpc.api.UpmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * 单点登录管理
 * Created by shuzheng on 2016/12/10.
 */
@Controller
@RequestMapping("/sso")
@Api(value = "单点登录管理", description = "单点登录管理")
public class SSOController extends BaseController {

	private final static Logger _log = LoggerFactory.getLogger(SSOController.class);
	private final static int TIMEOUT = 2 * 60 * 60;
	// 全局会话key
	private final static String ZHENG_UPMS_SERVER_SESSION_ID = "zheng-upms-server-session-id";
	// token key
	private final static String ZHENG_UPMS_SERVER_TOKEN = "zheng-upms-server-token";
	// 局部会话key
	private final static String ZHENG_UPMS_CLIENT_SESSION_ID = "zheng-upms-client-session-id";
	// 单点同一个token所有局部会话key
	private final static String ZHENG_UPMS_CLIENT_SESSION_IDS = "zheng-upms-client-session-ids";

	@Autowired
	UpmsSystemService upmsSystemService;

	@Autowired
	UpmsUserService upmsUserService;

	@ApiOperation(value = "认证中心首页")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request) throws Exception {
		String system_name = request.getParameter("system_name");
		String backurl = request.getParameter("backurl");

		// 判断请求认证系统是否注册
		UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
		upmsSystemExample.createCriteria()
				.andNameEqualTo(system_name);
		int count = upmsSystemService.countByExample(upmsSystemExample);
		if (StringUtils.isEmpty(system_name) || 0 == count) {
			_log.info("未注册的系统：{}", system_name);
			return "/500";
		}
		return "redirect:/sso/login?backurl=" + URLEncoder.encode(backurl, "utf-8");
	}

	@ApiOperation(value = "登录")
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response) {
		// 分配单点登录sessionId，首次获取后缓存到cookie，防止session丢失
		String serverSessionId = CookieUtil.getCookie(request, ZHENG_UPMS_SERVER_SESSION_ID);
		if (StringUtils.isEmpty(serverSessionId)) {
			serverSessionId = request.getSession().getId();
			CookieUtil.setCookie(response, ZHENG_UPMS_SERVER_SESSION_ID, serverSessionId);
		}
		// 有回跳路径的访问判断是否已登录，如果已登录，则回跳
		String backurl = request.getParameter("backurl");
		String token = RedisUtil.get(ZHENG_UPMS_SERVER_SESSION_ID + "_" + serverSessionId);
		// token校验值
		if (!StringUtils.isEmpty(token)) {
			// 回跳
			String redirectUrl = backurl;
			if (StringUtils.isEmpty(backurl)) {
				redirectUrl = "/";
			} else {
				if (backurl.contains("?")) {
					redirectUrl += "&token=" + token;
				} else {
					redirectUrl += "?token=" + token;
				}
			}
			_log.info("认证中心帐号通过，带token回跳：{}", redirectUrl);
			return "redirect:" + redirectUrl;
		}
		return "/sso/login";
	}

	@ApiOperation(value = "登录")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Object login(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String backurl = request.getParameter("backurl");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Map result = new HashMap<>();
		String data = "";
		if (StringUtils.isEmpty(username)) {
			result.put("result", false);
			result.put("data", SystemConstant.NO_USERNAME);
			return result;
		}
		if (StringUtils.isEmpty(password)) {
			result.put("result", false);
			result.put("data", SystemConstant.NO_PASSWORD);
			return result;
		}
		// 使用shiro认证
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
		try {
			//usernamePasswordToken.setRememberMe(false);
			subject.login(usernamePasswordToken);
		} catch (UnknownAccountException e) {
			result.put("result", false);
			result.put("data", SystemConstant.ERROR_USERNAME);
			return result;
		} catch (IncorrectCredentialsException e) {
			result.put("result", false);
			result.put("data", SystemConstant.ERROR_PASSWORD);
			return result;
		} catch (LockedAccountException e) {
			result.put("result", false);
			result.put("data", SystemConstant.INVALID_ACCOUNT);
			return result;
		}
		// 分配单点登录sessionId，首次获取后缓存到cookie，防止session丢失
		String serverSessionId = CookieUtil.getCookie(request, ZHENG_UPMS_SERVER_SESSION_ID);
		if (StringUtils.isEmpty(serverSessionId)) {
			serverSessionId = request.getSession().getId();
			CookieUtil.setCookie(response, ZHENG_UPMS_SERVER_SESSION_ID, serverSessionId);
		}
		// 默认验证帐号密码正确，创建token
		String token = UUID.randomUUID().toString();
		// 全局会话sessionId
		RedisUtil.set(ZHENG_UPMS_SERVER_SESSION_ID + "_" + serverSessionId, token, TIMEOUT);
		// token校验值
		RedisUtil.set(ZHENG_UPMS_SERVER_TOKEN + "_" + token, token, TIMEOUT);
		// 回调子系统
		if (StringUtils.isEmpty(backurl)) {
			result.put("result", true);
			result.put("data", "/");
		} else {
			String redirectUrl = backurl;
			if (backurl.contains("?")) {
				redirectUrl += "&token=" + token;
			} else {
				redirectUrl += "?token=" + token;
			}
			_log.info("认证中心帐号通过，带token回跳：{}", redirectUrl);
			result.put("result", true);
			result.put("data", redirectUrl);
		}
		return result;
	}

	@ApiOperation(value = "校验token")
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	@ResponseBody
	public String token(HttpServletRequest request) {
		String tokenParam = request.getParameter("token");
		String token = RedisUtil.get(ZHENG_UPMS_SERVER_TOKEN + "_" + tokenParam);
		if (StringUtils.isEmpty(tokenParam) || !tokenParam.equals(token)) {
			return "failed";
		}
		return "success";
	}

	@ApiOperation(value = "退出登录")
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		// shiro退出登录
		SecurityUtils.getSubject().logout();

		String serverSessionId = CookieUtil.getCookie(request, ZHENG_UPMS_SERVER_SESSION_ID);
		// 当前全局会话token
		String token = RedisUtil.get(ZHENG_UPMS_SERVER_SESSION_ID + "_" + serverSessionId);
		// 清除全局会话
		RedisUtil.remove(ZHENG_UPMS_SERVER_SESSION_ID + "_" + serverSessionId);
		// 清除token校验值
		RedisUtil.remove(ZHENG_UPMS_SERVER_TOKEN + "_" + token);
		// 清除所有局部会话
		Jedis jedis = RedisUtil.getJedis();
		Set<String> clientSessionIds = jedis.smembers(ZHENG_UPMS_CLIENT_SESSION_IDS + "_" + token);
		for (String clientSessionId : clientSessionIds) {
			jedis.del(ZHENG_UPMS_CLIENT_SESSION_ID + "_" + clientSessionId);
			jedis.srem(ZHENG_UPMS_CLIENT_SESSION_IDS + "_" + token, clientSessionId);
		}
		// 清除全局会话sessionId
		CookieUtil.removeCookie(response, ZHENG_UPMS_SERVER_SESSION_ID);
		_log.info("当前token={}，对应的注册系统个数：{}个", token, jedis.scard(ZHENG_UPMS_CLIENT_SESSION_IDS + "_" + token));
		// 跳回原地址
		String redirectUrl = request.getHeader("Referer");
		_log.info("跳回退出登录请求地址：{}", redirectUrl);
		return "redirect:" + redirectUrl;
	}

}