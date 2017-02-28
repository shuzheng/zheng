package com.zheng.upms.server.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.zheng.common.base.BaseController;
import com.zheng.common.validator.LengthValidator;
import com.zheng.upms.common.constant.UpmsResult;
import com.zheng.upms.common.constant.UpmsResultConstant;
import com.zheng.upms.dao.model.UpmsSystem;
import com.zheng.upms.dao.model.UpmsSystemExample;
import com.zheng.upms.rpc.api.UpmsSystemService;
import com.zheng.upms.server.shiro.UpmsSessionDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会话管理controller
 * Created by shuzheng on 2017/2/28.
 */
@Controller
@Api(value = "系统管理", description = "系统管理")
@RequestMapping("/manage/session")
public class UpmsSessionController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(UpmsSessionController.class);

	@Autowired
	private UpmsSessionDao sessionDAO;

	@ApiOperation(value = "系统首页")
	@RequiresPermissions("upms:session:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/session/index";
	}

	@ApiOperation(value = "系统列表")
	@RequiresPermissions("upms:session:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit) {
		return sessionDAO.getActiveSessions(offset, limit);
	}

	@ApiOperation(value = "删除系统")
	@RequiresPermissions("upms:session:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = sessionDAO.deleteByPrimaryKeys(ids);
		return new UpmsResult(UpmsResultConstant.SUCCESS, count);
	}

}