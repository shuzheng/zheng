package com.zheng.upms.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页controller
 * Created by ZhangShuzheng on 2017/01/19.
 */
@Controller
public class IndexController {

	/**
	 * 默认页跳转到后台
	 * @return
	 */
	@RequestMapping(value = {"", "/index"})
	public String index() {
		return "redirect:/manage";
	}

}