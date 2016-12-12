package com.zheng.upms.app1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by shuzheng on 2016/12/12.
 */
@Controller
@RequestMapping("/user")
public class UserController {

	private static Logger _log = LoggerFactory.getLogger(UserController.class);

	/**
	 * 列表
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public String list() {
		return "app1 : <a href='http://upms.zhangshuzheng.cn:1111/sso/logout'>退出登录</a>";
	}

}