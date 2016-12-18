package com.zheng.upms.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 后台controller
 * Created by shuzheng on 2016/12/18.
 */
@Controller
@RequestMapping("/manage")
public class ManageController {

	private static Logger _log = LoggerFactory.getLogger(ManageController.class);

	@RequestMapping(value = {"", "/index"})
	public String index() {
		return "/manage/index";
	}

}