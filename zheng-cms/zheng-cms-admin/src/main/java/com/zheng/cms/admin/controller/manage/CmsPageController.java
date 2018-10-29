package com.zheng.cms.admin.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.zheng.cms.common.constant.CmsResult;
import com.zheng.cms.common.constant.CmsResultConstant;
import com.zheng.cms.dao.model.CmsPage;
import com.zheng.cms.dao.model.CmsPageExample;
import com.zheng.cms.rpc.api.CmsPageService;
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
 * 单页控制器
 * Created by shuzheng on 2017/3/18.
 */
@Controller
@Api(value = "单页管理", description = "单页管理")
@RequestMapping("/manage/page")
public class CmsPageController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CmsPageController.class);
	
	@Autowired
	private CmsPageService cmsPageService;

	@ApiOperation(value = "评论首页")
	@RequiresPermissions("cms:page:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/page/index.jsp";
	}

	@ApiOperation(value = "评论列表")
	@RequiresPermissions("cms:page:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		CmsPageExample cmsPageExample = new CmsPageExample();
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			cmsPageExample.setOrderByClause(sort + " " + order);
		}
		List<CmsPage> rows = cmsPageService.selectByExampleForOffsetPage(cmsPageExample, offset, limit);
		long total = cmsPageService.countByExample(cmsPageExample);
		Map<String, Object> result = new HashMap<>(2);
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增单页")
	@RequiresPermissions("cms:page:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "/manage/page/create.jsp";
	}

	@ApiOperation(value = "新增单页")
	@RequiresPermissions("cms:page:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(CmsPage cmsPage) {
		ComplexResult result = FluentValidator.checkAll()
				.on(cmsPage.getTitle(), new LengthValidator(1, 20, "标题"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new CmsResult(CmsResultConstant.INVALID_LENGTH, result.getErrors());
		}
		long time = System.currentTimeMillis();
		cmsPage.setCtime(time);
		cmsPage.setOrders(time);
		int count = cmsPageService.insertSelective(cmsPage);
		return new CmsResult(CmsResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "删除单页")
	@RequiresPermissions("cms:page:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = cmsPageService.deleteByPrimaryKeys(ids);
		return new CmsResult(CmsResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "修改单页")
	@RequiresPermissions("cms:page:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		CmsPage page = cmsPageService.selectByPrimaryKey(id);
		modelMap.put("page", page);
		return "/manage/page/update.jsp";
	}

	@ApiOperation(value = "修改单页")
	@RequiresPermissions("cms:page:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, CmsPage cmsPage) {
		ComplexResult result = FluentValidator.checkAll()
				.on(cmsPage.getTitle(), new LengthValidator(1, 20, "标题"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new CmsResult(CmsResultConstant.INVALID_LENGTH, result.getErrors());
		}
		cmsPage.setPageId(id);
		int count = cmsPageService.updateByPrimaryKeySelective(cmsPage);
		return new CmsResult(CmsResultConstant.SUCCESS, count);
	}

}