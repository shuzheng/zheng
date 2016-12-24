package com.zheng.upms.admin.controller;

import com.zheng.upms.dao.model.UpmsSystemExample;
import com.zheng.upms.rpc.api.UpmsSystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统controller
 * Created by shuzheng on 2016/12/18.
 */
@Controller
@RequestMapping("/system")
public class SystemController {

	private static Logger _log = LoggerFactory.getLogger(SystemController.class);

	@Autowired
	private UpmsSystemService upmsSystemService;

	@RequestMapping("/index")
	public String index() {
		return "/system/index";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Object list() {
		UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
		upmsSystemExample.createCriteria()
				.andSystemIdGreaterThan(0);
		return upmsSystemService.getMapper().selectByExample(upmsSystemExample);
	}

}