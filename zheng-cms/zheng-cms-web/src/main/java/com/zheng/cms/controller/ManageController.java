package com.zheng.cms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台controller
 * @author shuzheng
 * @date 2016年10月18日
 */
@Controller
@RequestMapping("/manage")
public class ManageController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(ManageController.class);

	@RequestMapping(value = {"", "/index"})
	public String index() {
		return "/manage/index";
	}

	@RequestMapping("/login")
	public String login() {
		return "/manage/login";
	}

}