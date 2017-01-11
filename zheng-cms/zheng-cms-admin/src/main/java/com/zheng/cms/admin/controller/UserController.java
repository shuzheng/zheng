package com.zheng.cms.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户管理
 * Created by shuzheng on 2016/12/10.
 */
@Controller
@RequestMapping("/manage/user")
public class UserController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(UserController.class);

	/**
	 * 列表
	 * @return
	 */
	@RequestMapping("/list")
	public String index() {
		return "/user/list";
	}

}