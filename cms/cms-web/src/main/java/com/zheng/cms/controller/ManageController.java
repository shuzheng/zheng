package com.zheng.cms.controller;

import com.zheng.common.util.EhCacheUtil;
import com.zheng.common.util.PropertiesFileUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台controller
 * @author shuzheng
 * @date 2016年10月18日
 */
@Controller
@RequestMapping("/manage")
public class ManageController {

	private static Logger _log = LoggerFactory.getLogger(ManageController.class);

	@RequestMapping(value = {"", "/index"})
	@ResponseBody
	public Object index() {
		return "/manage/index";
	}

	@RequestMapping("/login")
	@ResponseBody
	public Object login() {
		return "/manage/login";
	}

}
