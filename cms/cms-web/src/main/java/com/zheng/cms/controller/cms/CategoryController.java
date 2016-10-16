package com.zheng.cms.controller.cms;

import com.zheng.cms.controller.BaseController;
import com.zheng.cms.model.User;
import com.zheng.cms.model.UserExample;
import com.zheng.cms.service.UserService;
import com.zheng.cms.util.Paginator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章类目
 * @author shuzheng
 * @date 2016年10月16日 17:23
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private UserService userService;

	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value = {""})
	public String index() {
		return "redirect:/category/list";
	}
	
	/**
	 * 列表
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String list(
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "20") int rows,
			HttpServletRequest request) {

		return "/category/list";
	}
	
}