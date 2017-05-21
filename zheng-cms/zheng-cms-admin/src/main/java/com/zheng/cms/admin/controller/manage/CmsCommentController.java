package com.zheng.cms.admin.controller.manage;

import com.zheng.cms.common.constant.CmsResult;
import com.zheng.cms.common.constant.CmsResultConstant;
import com.zheng.cms.dao.model.CmsComment;
import com.zheng.cms.dao.model.CmsCommentExample;
import com.zheng.cms.rpc.api.CmsCommentService;
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
 * 评论控制器
 * Created by shuzheng on 2016/11/14.
 */
@Controller
@Api(value = "评论管理", description = "评论管理")
@RequestMapping("/manage/comment")
public class CmsCommentController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(CmsCommentController.class);
	
	@Autowired
	private CmsCommentService cmsCommentService;

	@ApiOperation(value = "评论首页")
	@RequiresPermissions("cms:comment:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/comment/index.jsp";
	}

	@ApiOperation(value = "评论列表")
	@RequiresPermissions("cms:comment:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		CmsCommentExample cmsCommentExample = new CmsCommentExample();
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			cmsCommentExample.setOrderByClause(sort + " " + order);
		}
		List<CmsComment> rows = cmsCommentService.selectByExampleWithBLOBsForOffsetPage(cmsCommentExample, offset, limit);
		long total = cmsCommentService.countByExample(cmsCommentExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增评论")
	@RequiresPermissions("cms:comment:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "/manage/comment/create.jsp";
	}

	@ApiOperation(value = "新增评论")
	@RequiresPermissions("cms:comment:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(CmsComment cmsComment) {
		long time = System.currentTimeMillis();
		cmsComment.setCtime(time);
		int count = cmsCommentService.insertSelective(cmsComment);
		return new CmsResult(CmsResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "删除评论")
	@RequiresPermissions("cms:comment:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = cmsCommentService.deleteByPrimaryKeys(ids);
		return new CmsResult(CmsResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "修改评论")
	@RequiresPermissions("cms:comment:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		CmsComment comment = cmsCommentService.selectByPrimaryKey(id);
		modelMap.put("comment", comment);
		return "/manage/comment/update.jsp";
	}

	@ApiOperation(value = "修改评论")
	@RequiresPermissions("cms:comment:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, CmsComment cmsComment) {
		cmsComment.setCommentId(id);
		int count = cmsCommentService.updateByPrimaryKeySelective(cmsComment);
		return new CmsResult(CmsResultConstant.SUCCESS, count);
	}

}