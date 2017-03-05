package com.zheng.upms.server.controller;

import com.zheng.common.base.BaseController;
import com.zheng.common.util.RedisUtil;
import com.zheng.upms.common.constant.UpmsResult;
import com.zheng.upms.common.constant.UpmsResultConstant;
import com.zheng.upms.dao.model.UpmsSystemExample;
import com.zheng.upms.rpc.api.UpmsSystemService;
import com.zheng.upms.rpc.api.UpmsUserService;
import com.zheng.upms.server.shiro.UpmsSession;
import com.zheng.upms.server.shiro.UpmsSessionDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
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

	@Autowired
	UpmsSessionDao upmsSessionDao;

	@ApiOperation(value = "认证中心首页")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request) throws Exception {
		String system_name = request.getParameter("system_name");
		String backurl = request.getParameter("backurl");
		if (StringUtils.isBlank(system_name)) {
			throw new RuntimeException("无效访问！");
		}
		// 判断请求认证系统是否注册
		UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
		upmsSystemExample.createCriteria()
			.andNameEqualTo(system_name);
		int count = upmsSystemService.countByExample(upmsSystemExample);
		if (0 == count) {
			throw new RuntimeException(String.format("未注册的系统:%s", system_name));
		}
		return "redirect:/sso/login?backurl=" + URLEncoder.encode(backurl, "utf-8");
	}

	@ApiOperation(value = "登录")
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		String backurl = request.getParameter("backurl");
		Subject subject = SecurityUtils.getSubject();
		String serverSessionId = subject.getSession().getId().toString();
		// 有回跳路径的访问判断是否已登录，如果已登录，则回跳
		String token = RedisUtil.get(ZHENG_UPMS_SERVER_SESSION_ID + "_" + serverSessionId);
		// token校验值
		if (!StringUtils.isBlank(token)) {
			// 回跳
			String redirectUrl = backurl;
			if (StringUtils.isBlank(backurl)) {
				redirectUrl = "/";
			} else {
				if (backurl.contains("?")) {
					redirectUrl += "&token=" + token;
				} else {
					redirectUrl += "?token=" + token;
				}
			}
			_log.debug("认证中心帐号通过，带token回跳：{}", redirectUrl);
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
		String rememberMe = request.getParameter("rememberMe");

		if (StringUtils.isBlank(username)) {
			return new UpmsResult(UpmsResultConstant.EMPTY_USERNAME, "帐号不能为空！");
		}
		if (StringUtils.isBlank(password)) {
			return new UpmsResult(UpmsResultConstant.EMPTY_PASSWORD, "密码不能为空！");
		}
		// 使用shiro认证
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
		try {
			if (BooleanUtils.toBoolean(rememberMe)) {
				usernamePasswordToken.setRememberMe(true);
			} else {
				usernamePasswordToken.setRememberMe(false);
			}
			subject.login(usernamePasswordToken);
		} catch (UnknownAccountException e) {
			return new UpmsResult(UpmsResultConstant.INVALID_USERNAME, "帐号不存在！");
		} catch (IncorrectCredentialsException e) {
			return new UpmsResult(UpmsResultConstant.INVALID_PASSWORD, "密码错误！");
		} catch (LockedAccountException e) {
			return new UpmsResult(UpmsResultConstant.INVALID_ACCOUNT, "帐号已锁定！");
		}
		// serverSessionId
		String serverSessionId = subject.getSession().getId().toString();
		// 更新session状态
		upmsSessionDao.updateStatus(serverSessionId, UpmsSession.OnlineStatus.on_line);
		// 默认验证帐号密码正确，创建token
		String token = UUID.randomUUID().toString();
		// 全局会话sessionId
		RedisUtil.set(ZHENG_UPMS_SERVER_SESSION_ID + "_" + serverSessionId, token, (int) subject.getSession().getTimeout() / 1000);
		// token校验值
		RedisUtil.set(ZHENG_UPMS_SERVER_TOKEN + "_" + token, token, (int) subject.getSession().getTimeout() / 1000);
		// 回跳登录前地址
		if (StringUtils.isBlank(backurl)) {
			SavedRequest savedRequest = WebUtils.getSavedRequest(request);
			backurl = null == savedRequest ? "/" : savedRequest.getRequestURI();
			return new UpmsResult(UpmsResultConstant.SUCCESS, backurl);
		} else {
			String redirectUrl = backurl;
			if (backurl.contains("?")) {
				redirectUrl += "&token=" + token;
			} else {
				redirectUrl += "?token=" + token;
			}
			_log.debug("认证中心帐号通过，带token回跳：{}", redirectUrl);
			return new UpmsResult(UpmsResultConstant.SUCCESS, redirectUrl);
		}
	}

	@ApiOperation(value = "校验token")
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	@ResponseBody
	public String token(HttpServletRequest request) {
		String tokenParam = request.getParameter("token");
		String token = RedisUtil.get(ZHENG_UPMS_SERVER_TOKEN + "_" + tokenParam);
		if (StringUtils.isBlank(tokenParam) || !tokenParam.equals(token)) {
			return "failed";
		}
		return "success";
	}

	@ApiOperation(value = "退出登录")
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		// shiro退出登录
		SecurityUtils.getSubject().logout();
		// 跳回原地址
		String redirectUrl = request.getHeader("Referer");
		if (null == redirectUrl) {
			redirectUrl = "/";
		}
		return "redirect:" + redirectUrl;
	}

}