package com.zheng.cms.web.controller;

import com.zheng.cms.dao.model.CmsMenu;
import com.zheng.cms.dao.model.CmsMenuExample;
import com.zheng.cms.rpc.api.CmsMenuService;
import com.zheng.common.base.BaseController;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@RequestMapping(value = {"", "/index"})
	public String index(Model model) {
		// 菜单
		CmsMenuExample cmsMenuExample = new CmsMenuExample();
		cmsMenuExample.setOrderByClause("orders asc");
		List<CmsMenu> menus = cmsMenuService.selectByExample(cmsMenuExample);
		model.addAttribute("menus", menus);
		return "/index";
	}

}