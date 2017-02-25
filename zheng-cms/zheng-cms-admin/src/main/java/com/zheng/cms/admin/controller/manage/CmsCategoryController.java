package com.zheng.cms.admin.controller.manage;

import com.zheng.cms.common.constant.CmsResult;
import com.zheng.cms.common.constant.CmsResultConstant;
import com.zheng.cms.dao.model.CmsCategory;
import com.zheng.cms.dao.model.CmsCategoryExample;
import com.zheng.cms.rpc.api.CmsCategoryService;
import com.zheng.common.base.BaseController;
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
 * 类目控制器
 * Created by shuzheng on 2016/11/14.
 */
@Controller
@RequestMapping("/manage/category")
@Api(value = "类目管理", description = "类目管理")
public class CmsCategoryController extends BaseController {

	private final static Logger _log = LoggerFactory.getLogger(CmsCategoryController.class);
	
	@Autowired
	private CmsCategoryService cmsCategoryService;

	@ApiOperation(value = "类目首页")
	@RequiresPermissions("cms:category:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/category/index";
	}

	@ApiOperation(value = "类目列表")
	@RequiresPermissions("cms:category:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		CmsCategoryExample cmsCategoryExample = new CmsCategoryExample();
		cmsCategoryExample.setOffset(offset);
		cmsCategoryExample.setLimit(limit);
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			cmsCategoryExample.setOrderByClause(sort + " " + order);
		}
		List<CmsCategory> rows = cmsCategoryService.selectByExample(cmsCategoryExample);
		long total = cmsCategoryService.countByExample(cmsCategoryExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增类目")
	@RequiresPermissions("cms:category:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "/manage/category/create";
	}

	@ApiOperation(value = "新增类目")
	@RequiresPermissions("cms:category:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(CmsCategory cmsCategory) {
		long time = System.currentTimeMillis();
		cmsCategory.setCtime(time);
		cmsCategory.setOrders(time);
		int count = cmsCategoryService.insertSelective(cmsCategory);
		return new CmsResult(CmsResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "删除类目")
	@RequiresPermissions("cms:category:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = cmsCategoryService.deleteByPrimaryKeys(ids);
		return new CmsResult(CmsResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "修改类目")
	@RequiresPermissions("cms:category:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		CmsCategory category = cmsCategoryService.selectByPrimaryKey(id);
		modelMap.put("category", category);
		return "/manage/category/update";
	}

	@ApiOperation(value = "修改类目")
	@RequiresPermissions("cms:category:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, CmsCategory cmsCategory) {
		cmsCategory.setCategoryId(id);
		int count = cmsCategoryService.updateByPrimaryKeySelective(cmsCategory);
		return new CmsResult(CmsResultConstant.SUCCESS, count);
	}

}