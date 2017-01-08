package com.zheng.cms.admin.controller;

import com.zheng.cms.dao.model.User;
import com.zheng.cms.dao.model.UserExample;
import com.zheng.cms.service.UserService;
import com.zheng.common.util.Paginator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
 * 用户管理
 * Created by shuzheng on 2016/12/10.
 */
@Controller
@RequestMapping("/manage/user")
public class UserController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(UserController.class);

	/**
	 * 列表
	 * @return
	 */
	@RequestMapping("/list")
	public String index() {
		return "/user/list";
	}

}