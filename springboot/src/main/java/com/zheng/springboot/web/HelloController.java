package com.zheng.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ZhangShuzheng on 2016/11/16.
 */
@Controller
public class HelloController {

	@RequestMapping("/index")
	public String index(ModelMap map) {
		map.addAttribute("host", "http://blog.didispace.com");
		return "/index";
	}

}
