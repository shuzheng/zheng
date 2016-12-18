package com.zheng.upms.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统controller
 * Created by shuzheng on 2016/12/18.
 */
@Controller
@RequestMapping("/system")
public class SystemController {

	private static Logger _log = LoggerFactory.getLogger(SystemController.class);

	@RequestMapping("/index")
	public String index() {
		return "/system/index";
	}

}