package com.zheng.cms.web.controller;

import com.zheng.common.base.BaseController;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页控制器
 * Created by shuzheng on 2017/3/19.
 */
@Controller
public class IndexController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value = {"", "/index"})
	public String index() {
		return "/index";
	}

}