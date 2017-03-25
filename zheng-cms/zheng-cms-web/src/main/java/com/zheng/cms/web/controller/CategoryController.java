package com.zheng.cms.web.controller;

import com.zheng.cms.dao.model.*;
import com.zheng.cms.rpc.api.*;
import com.zheng.common.base.BaseController;
import com.zheng.common.util.Paginator;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 类目控制器
 * Created by shuzheng on 2017/3/26.
 */
@Controller
@RequestMapping(value = "/category")
public class CategoryController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CmsCategoryService cmsCategoryService;

	@Autowired
	private CmsArticleService cmsArticleService;

	@RequestMapping(value = "/{alias}", method = RequestMethod.GET)
	public String index(@PathVariable("alias") String alias,
						@RequestParam(required = false, defaultValue = "1", value = "page") int page,
						@RequestParam(required = false, defaultValue = "orders", value = "sort") String sort,
						@RequestParam(required = false, defaultValue = "desc", value = "order") String order,
						HttpServletRequest request,
						Model model) {
		// 当前类目
		CmsCategoryExample cmsCategoryExample = new CmsCategoryExample();
		cmsCategoryExample.createCriteria()
				.andAliasEqualTo(alias);
		CmsCategory category = cmsCategoryService.selectFirstByExample(cmsCategoryExample);
		model.addAttribute("category", category);
		// 该类目文章列表
		int rows = 10;
		CmsArticleExample cmsArticleExample = new CmsArticleExample();
		cmsArticleExample.createCriteria()
				.andStatusEqualTo((byte) 1);
		cmsArticleExample.setOffset((page - 1) * rows);
		cmsArticleExample.setLimit(rows);
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			cmsArticleExample.setOrderByClause(sort + " " + order);
		}
		List<CmsArticle> articles = cmsArticleService.selectByExample(cmsArticleExample);
		model.addAttribute("articles", articles);
		// 文章总数
		long total = cmsArticleService.countByExample(cmsArticleExample);
		// 分页
		Paginator paginator = new Paginator(total, page, rows, request);
		model.addAttribute("paginator", paginator);
		return thymeleaf("/category/index");
	}

}