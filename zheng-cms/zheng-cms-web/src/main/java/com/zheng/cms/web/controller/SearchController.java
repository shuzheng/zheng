package com.zheng.cms.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.zheng.cms.dao.model.CmsArticle;
import com.zheng.cms.dao.model.CmsArticleExample;
import com.zheng.cms.rpc.api.CmsArticleService;
import com.zheng.common.base.BaseController;
import com.zheng.common.util.Paginator;

/**
 * 搜索控制器 Created by shuzheng on 2017/3/26.
 */
@Controller
@RequestMapping(value = "/search")
public class SearchController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private CmsArticleService cmsArticleService;

	@RequestMapping(value = "/{keyword}", method = RequestMethod.GET)
	public String index(@PathVariable("keyword") String keyword,
			@RequestParam(required = false, defaultValue = "1", value = "page") int page,
			@RequestParam(required = false, defaultValue = "orders", value = "sort") String sort,
			@RequestParam(required = false, defaultValue = "desc", value = "order") String order,
			HttpServletRequest request, Model model) {
		// 该关键字文章列表
		int rows = 10;
		CmsArticleExample cmsArticleExample = new CmsArticleExample();
		cmsArticleExample.createCriteria().andStatusEqualTo((byte) 1).andTitleLike(keyword);
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			cmsArticleExample.setOrderByClause(sort + " " + order);
		}
		List<CmsArticle> articles = cmsArticleService.selectByExampleForOffsetPage(cmsArticleExample, (page - 1) * rows,
				rows);
		model.addAttribute("articles", articles);
		// 文章总数
		long total = cmsArticleService.countByExample(cmsArticleExample);
		// 分页
		Paginator paginator = new Paginator(total, page, rows, request);
		model.addAttribute("paginator", paginator);
		return thymeleaf("/search/index");
	}

}