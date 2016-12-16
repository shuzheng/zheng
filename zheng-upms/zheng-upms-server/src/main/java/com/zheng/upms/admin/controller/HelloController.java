package com.zheng.upms.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 示例controller
 * Created by shuzheng on 2016/12/16.
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

	private static Logger _log = LoggerFactory.getLogger(HelloController.class);

	@RequestMapping("")
	@ResponseBody
	public String index() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
	}

}