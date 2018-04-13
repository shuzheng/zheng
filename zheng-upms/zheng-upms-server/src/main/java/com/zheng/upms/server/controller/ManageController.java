package com.zheng.upms.server.controller;

import com.zheng.common.base.BaseController;
import com.zheng.upms.dao.model.*;
import com.zheng.upms.rpc.api.UpmsApiService;
import com.zheng.upms.rpc.api.UpmsSystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 后台controller
 * Created by ZhangShuzheng on 2017/01/19.
 */
@Controller
@RequestMapping("/manage")
@Api(value = "后台管理", description = "后台管理")
public class ManageController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ManageController.class);

	@Autowired
	private UpmsSystemService upmsSystemService;

	@Autowired
	private UpmsApiService upmsApiService;

	@ApiOperation(value = "后台首页")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		// 已注册系统
		UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
		upmsSystemExample.createCriteria()
				.andStatusEqualTo((byte) 1);
		List<UpmsSystem> upmsSystems = upmsSystemService.selectByExample(upmsSystemExample);
		modelMap.put("upmsSystems", upmsSystems);
		// 当前登录用户权限
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);
		List<UpmsPermission> upmsPermissions = upmsApiService.selectUpmsPermissionByUpmsUserId(upmsUser.getUserId());
		modelMap.put("upmsPermissions", upmsPermissions);
		return "/manage/index.jsp";
	}

}