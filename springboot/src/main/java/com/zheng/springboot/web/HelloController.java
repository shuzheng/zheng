package com.zheng.springboot.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ZhangShuzheng on 2016/11/16.
 */
@Controller
public class HelloController {

	@ApiOperation(value="测试首页", notes="测试首页get请求")
	@ApiImplicitParam(name = "map", value = "ModelMap实体map", required = false, dataType = "ModelMap")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap map) {
		map.addAttribute("host", "http://www.zhangshuzheng.cn");
		return "/index";
	}

}
