package com.zheng.upms.admin.controller.manage;

import com.zheng.upms.dao.model.UpmsSystemExample;
import com.zheng.upms.rpc.api.UpmsSystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统controller
 * Created by shuzheng on 2016/12/18.
 */
@Controller
@Api(value = "系统管理", description = "注册系统管理")
@RequestMapping("/manage/system")
public class SystemController {

	private static Logger _log = LoggerFactory.getLogger(SystemController.class);

	@Autowired
	private UpmsSystemService upmsSystemService;

	@ApiOperation(value = "系统首页")
	@RequiresPermissions("upms.system.read")
    //@RequiresUser
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/system/index";
	}

	@ApiOperation(value = "系统列表")
	@RequiresPermissions("upms.system.read")
    //@RequiresUser
	@RequestMapping("/list")
	@ResponseBody
	public Object list() {
		UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
		upmsSystemExample.createCriteria()
				.andSystemIdGreaterThan(0);
		return upmsSystemService.selectByExample(upmsSystemExample);
	}

}