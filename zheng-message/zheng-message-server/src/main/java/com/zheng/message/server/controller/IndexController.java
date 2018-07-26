package com.zheng.message.server.controller;

import com.zheng.common.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试controller
 * Created by shuzheng on 2017/2/18.
 */
@Controller
public class IndexController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@ResponseBody
	public String index() {
		return "index";
	}

}