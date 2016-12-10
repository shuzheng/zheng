package com.zheng.upms.admin.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * 单点登录管理
 * Created by shuzheng on 2016/12/10.
 */
@Controller
@RequestMapping("/sso")
public class SSOController {

	private static Logger _log = LoggerFactory.getLogger(SSOController.class);

	/**
	 * 认证中心首页
	 * @return
	 */
	@RequestMapping("")
	public String index(HttpServletRequest request) throws Exception {
		String system_name = request.getParameter("system_name");
		String backurl = request.getParameter("backurl");
		if (StringUtils.isEmpty(system_name) || !system_name.equals("zheng-cms-admin")) {
			_log.info("未注册的系统：{}", system_name);
			return "/404";
		}
		// 判断是否存在全局会话
		if (null == request.getSession().getAttribute("isLogin")) {
			return "redirect:/sso/login?backurl=" + URLEncoder.encode(backurl, "utf-8");
		}
		_log.info("认证中心验证为已登录，跳回：{}", backurl);
		return "redirect:" + backurl;
	}

	/**
	 * 登录页get
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "/sso/login";
	}

	/**
	 * 登录页post
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request) {
		String backurl = request.getParameter("backurl");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (StringUtils.isEmpty(username)) {
			_log.info("帐号不能为空！");
			return "/404";
		}
		if (StringUtils.isEmpty(password)) {
			_log.info("密码不能为空！");
			return "/404";
		}
		// 默认验证帐号密码正确，创建token
		HttpSession session = request.getSession();
		_log.info("子系统sessionId：{}", session.getId());
		String token = UUID.randomUUID().toString().replace("-", "");
		session.setAttribute("isLogin", true);
		session.setAttribute(session.getId(), token);
		String redirectUrl = backurl;
		if (backurl.contains("?")) {
			redirectUrl += "&token=" + token;
		} else {
			redirectUrl += "?token=" + token;
		}
		_log.info("认证中心帐号通过，带token回跳：{}", redirectUrl);
		return "redirect:" + redirectUrl;
	}

}