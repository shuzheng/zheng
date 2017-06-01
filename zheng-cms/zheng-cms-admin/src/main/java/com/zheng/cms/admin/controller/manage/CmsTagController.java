package com.zheng.cms.admin.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.zheng.cms.common.constant.CmsResult;
import com.zheng.cms.common.constant.CmsResultConstant;
import com.zheng.cms.dao.model.CmsTag;
import com.zheng.cms.dao.model.CmsTagExample;
import com.zheng.cms.rpc.api.CmsTagService;
import com.zheng.common.base.BaseController;
import com.zheng.common.validator.LengthValidator;
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
 * 标签控制器
 * Created by shuzheng on 2016/11/14.
 */
@Controller
@Api(value = "标签管理", description = "标签管理")
@RequestMapping("/manage/tag")
public class CmsTagController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(CmsTagController.class);
	
	@Autowired
	private CmsTagService cmsTagService;

	@ApiOperation(value = "评论首页")
	@RequiresPermissions("cms:tag:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/tag/index.jsp";
	}

	@ApiOperation(value = "评论列表")
	@RequiresPermissions("cms:tag:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		CmsTagExample cmsTagExample = new CmsTagExample();
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			cmsTagExample.setOrderByClause(sort + " " + order);
		}
		List<CmsTag> rows = cmsTagService.selectByExampleForOffsetPage(cmsTagExample, offset, limit);
		long total = cmsTagService.countByExample(cmsTagExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增标签")
	@RequiresPermissions("cms:tag:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "/manage/tag/create.jsp";
	}

	@ApiOperation(value = "新增标签")
	@RequiresPermissions("cms:tag:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(CmsTag cmsTag) {
		ComplexResult result = FluentValidator.checkAll()
				.on(cmsTag.getName(), new LengthValidator(1, 20, "名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new CmsResult(CmsResultConstant.INVALID_LENGTH, result.getErrors());
		}
		long time = System.currentTimeMillis();
		cmsTag.setCtime(time);
		cmsTag.setOrders(time);
		int count = cmsTagService.insertSelective(cmsTag);
		return new CmsResult(CmsResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "删除标签")
	@RequiresPermissions("cms:tag:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = cmsTagService.deleteByPrimaryKeys(ids);
		return new CmsResult(CmsResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "修改标签")
	@RequiresPermissions("cms:tag:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		CmsTag tag = cmsTagService.selectByPrimaryKey(id);
		modelMap.put("tag", tag);
		return "/manage/tag/update.jsp";
	}

	@ApiOperation(value = "修改标签")
	@RequiresPermissions("cms:tag:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, CmsTag cmsTag) {
		ComplexResult result = FluentValidator.checkAll()
				.on(cmsTag.getName(), new LengthValidator(1, 20, "名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new CmsResult(CmsResultConstant.INVALID_LENGTH, result.getErrors());
		}
		cmsTag.setTagId(id);
		int count = cmsTagService.updateByPrimaryKeySelective(cmsTag);
		return new CmsResult(CmsResultConstant.SUCCESS, count);
	}

}