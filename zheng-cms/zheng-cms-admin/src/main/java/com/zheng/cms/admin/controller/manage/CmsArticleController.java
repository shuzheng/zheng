package com.zheng.cms.admin.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.zheng.cms.common.constant.CmsResult;
import com.zheng.cms.common.constant.CmsResultConstant;
import com.zheng.cms.dao.model.CmsArticle;
import com.zheng.cms.dao.model.CmsArticleExample;
import com.zheng.cms.dao.model.CmsTopic;
import com.zheng.cms.dao.model.CmsTopicExample;
import com.zheng.cms.rpc.api.CmsArticleService;
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
 * 文章控制器
 * Created by shuzheng on 2016/11/14.
 */
@Controller
@Api(value = "文章管理", description = "文章管理")
@RequestMapping("/manage/article")
public class CmsArticleController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(CmsArticleController.class);
	
	@Autowired
	private CmsArticleService cmsArticleService;

	@Autowired
	private CmsTopicService cmsTopicService;

	@ApiOperation(value = "文章首页")
	@RequiresPermissions("cms:article:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/article/index.jsp";
	}

	@ApiOperation(value = "文章列表")
	@RequiresPermissions("cms:article:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		CmsArticleExample cmsArticleExample = new CmsArticleExample();
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			cmsArticleExample.setOrderByClause(sort + " " + order);
		}
		List<CmsArticle> rows = cmsArticleService.selectByExampleForOffsetPage(cmsArticleExample, offset, limit);
		long total = cmsArticleService.countByExample(cmsArticleExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增文章")
	@RequiresPermissions("cms:article:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		CmsTopicExample cmsTopicExample = new CmsTopicExample();
		cmsTopicExample.setOrderByClause("ctime desc");
		List<CmsTopic> cmsTopics = cmsTopicService.selectByExample(cmsTopicExample);
		modelMap.put("cmsTopics", cmsTopics);
		return "/manage/article/create.jsp";
	}

	@ApiOperation(value = "新增文章")
	@RequiresPermissions("cms:article:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(CmsArticle cmsArticle) {
		ComplexResult result = FluentValidator.checkAll()
				.on(cmsArticle.getTitle(), new LengthValidator(1, 200, "标题"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new CmsResult(CmsResultConstant.INVALID_LENGTH, result.getErrors());
		}
		long time = System.currentTimeMillis();
		cmsArticle.setCtime(time);
		cmsArticle.setOrders(time);
		cmsArticle.setReadnumber(0);
		int count = cmsArticleService.insertSelective(cmsArticle);
		return new CmsResult(CmsResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "删除文章")
	@RequiresPermissions("cms:article:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = cmsArticleService.deleteByPrimaryKeys(ids);
		return new CmsResult(CmsResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "修改文章")
	@RequiresPermissions("cms:article:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		CmsTopicExample cmsTopicExample = new CmsTopicExample();
		cmsTopicExample.setOrderByClause("ctime desc");
		List<CmsTopic> cmsTopics = cmsTopicService.selectByExample(cmsTopicExample);
		CmsArticle article = cmsArticleService.selectByPrimaryKey(id);
		modelMap.put("cmsTopics", cmsTopics);
		modelMap.put("article", article);
		return "/manage/article/update.jsp";
	}

	@ApiOperation(value = "修改文章")
	@RequiresPermissions("cms:article:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, CmsArticle cmsArticle) {
		ComplexResult result = FluentValidator.checkAll()
				.on(cmsArticle.getTitle(), new LengthValidator(1, 200, "标题"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new CmsResult(CmsResultConstant.INVALID_LENGTH, result.getErrors());
		}
		cmsArticle.setArticleId(id);
		int count = cmsArticleService.updateByPrimaryKeySelective(cmsArticle);
		return new CmsResult(CmsResultConstant.SUCCESS, count);
	}

}