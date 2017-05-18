package com.zheng.cms.admin.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.zheng.cms.common.constant.CmsResult;
import com.zheng.cms.common.constant.CmsResultConstant;
import com.zheng.cms.dao.model.CmsTopic;
import com.zheng.cms.dao.model.CmsTopicExample;
import com.zheng.cms.rpc.api.CmsTopicService;
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
 * 专题控制器
 * Created by shuzheng on 2017/3/18.
 */
@Controller
@Api(value = "专题管理", description = "专题管理")
@RequestMapping("/manage/topic")
public class CmsTopicController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(CmsTopicController.class);
	
	@Autowired
	private CmsTopicService cmsTopicService;

	@ApiOperation(value = "评论首页")
	@RequiresPermissions("cms:topic:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/topic/index.jsp";
	}

	@ApiOperation(value = "评论列表")
	@RequiresPermissions("cms:topic:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		CmsTopicExample cmsTopicExample = new CmsTopicExample();
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			cmsTopicExample.setOrderByClause(sort + " " + order);
		}
		List<CmsTopic> rows = cmsTopicService.selectByExampleForOffsetPage(cmsTopicExample, offset, limit);
		long total = cmsTopicService.countByExample(cmsTopicExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增专题")
	@RequiresPermissions("cms:topic:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "/manage/topic/create.jsp";
	}

	@ApiOperation(value = "新增专题")
	@RequiresPermissions("cms:topic:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(CmsTopic cmsTopic) {
		ComplexResult result = FluentValidator.checkAll()
				.on(cmsTopic.getTitle(), new LengthValidator(1, 100, "标题"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new CmsResult(CmsResultConstant.INVALID_LENGTH, result.getErrors());
		}
		long time = System.currentTimeMillis();
		cmsTopic.setCtime(time);
		int count = cmsTopicService.insertSelective(cmsTopic);
		return new CmsResult(CmsResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "删除专题")
	@RequiresPermissions("cms:topic:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = cmsTopicService.deleteByPrimaryKeys(ids);
		return new CmsResult(CmsResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "修改专题")
	@RequiresPermissions("cms:topic:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		CmsTopic topic = cmsTopicService.selectByPrimaryKey(id);
		modelMap.put("topic", topic);
		return "/manage/topic/update.jsp";
	}

	@ApiOperation(value = "修改专题")
	@RequiresPermissions("cms:topic:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, CmsTopic cmsTopic) {
		ComplexResult result = FluentValidator.checkAll()
				.on(cmsTopic.getTitle(), new LengthValidator(1, 100, "标题"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new CmsResult(CmsResultConstant.INVALID_LENGTH, result.getErrors());
		}
		cmsTopic.setTopicId(id);
		int count = cmsTopicService.updateByPrimaryKeySelective(cmsTopic);
		return new CmsResult(CmsResultConstant.SUCCESS, count);
	}

}