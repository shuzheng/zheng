package com.zheng.cms.web.controller;

import com.zheng.cms.dao.model.*;
import com.zheng.cms.rpc.api.CmsCategoryService;
import com.zheng.cms.rpc.api.CmsMenuService;
import com.zheng.cms.rpc.api.CmsTagService;
import com.zheng.common.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 首页控制器
 * Created by shuzheng on 2017/3/19.
 */
@Controller
public class IndexController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private CmsMenuService cmsMenuService;

	@Autowired
	private CmsCategoryService cmsCategoryService;

	@Autowired
	private CmsTagService cmsTagService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {
		// 菜单
		CmsMenuExample cmsMenuExample = new CmsMenuExample();
		cmsMenuExample.setOrderByClause("orders asc");
		List<CmsMenu> menus = cmsMenuService.selectByExample(cmsMenuExample);
		model.addAttribute("menus", menus);
		// 所有分类
		CmsCategoryExample cmsCategoryExample = new CmsCategoryExample();
		cmsCategoryExample.setOrderByClause("orders asc");
		List<CmsCategory> categories = cmsCategoryService.selectByExample(cmsCategoryExample);
		model.addAttribute("categories", categories);
		// 所有标签
		CmsTagExample cmsTagExample = new CmsTagExample();
		cmsTagExample.setOrderByClause("orders asc");
		List<CmsTag> tags = cmsTagService.selectByExample(cmsTagExample);
		model.addAttribute("tags", tags);
		return thymeleaf("/index");
	}

}