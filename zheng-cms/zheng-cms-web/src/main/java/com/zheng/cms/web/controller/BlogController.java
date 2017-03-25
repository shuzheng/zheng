package com.zheng.cms.web.controller;

import com.zheng.common.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 博客首页控制器
 * Created by shuzheng on 2017/3/26.
 */
@Controller
@RequestMapping(value = "/blog")
public class BlogController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(BlogController.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        return thymeleaf("/blog/index");
    }

}