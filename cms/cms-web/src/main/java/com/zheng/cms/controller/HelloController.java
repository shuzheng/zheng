package com.zheng.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zheng.cms.model.User;
import com.zheng.cms.service.impl.UserServiceImpl;

/**
 * 示例controller
 * @author shuzheng
 * @date 2016年7月6日 下午6:16:00
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

	private static Logger logger = LoggerFactory.getLogger(HelloController.class);

	private UserServiceImpl userService;

	public void setUserServiceImpl(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	@RequestMapping("/index")
	public String index() {
		// 视图渲染，/WEB-INF/jsp/hello/world.jsp
		return "/hello/world";
	}
	
	// 方法级别的RequestMapping， 限制并缩小了URL路径匹配，同类级别的标签协同工作，最终确定拦截到的URL由那个方法处理
	@RequestMapping("/world")
	public String world() {
		// 视图渲染，/WEB-INF/jsp/hello/world.jsp
		return "/hello/world";
	}

	// 本方法将处理 /hello/view?courseId=123 形式的URL
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String viewCourse(@RequestParam("courseId") Integer courseId, Model model) {

		User user = userService.getMapper().selectByPrimaryKey(courseId);
		model.addAttribute(user);
		return "course_overview";
	}

	// 本方法将处理 /hello/view2/123 形式的URL
	@RequestMapping("/view2/{courseId}")
	public String viewCourse2(@PathVariable("courseId") Integer courseId, Map<String, Object> map) {

		User user = userService.getMapper().selectByPrimaryKey(courseId);
		map.put("user", user);
		return "course_overview";
	}

	// 本方法将处理 /hello/view3?courseId=123 形式的URL
	@RequestMapping("/view3")
	public String viewCourse3(HttpServletRequest request) {

		Integer courseId = Integer.valueOf(request.getParameter("courseId"));
		User user = userService.getMapper().selectByPrimaryKey(courseId);
		request.setAttribute("user", user);

		return "course_overview";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET, params = "add")
	public String createCourse() {
		return "course_admin/edit";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String doSave(@ModelAttribute User user) {

		logger.debug("Info of Course:");
		logger.debug(ReflectionToStringBuilder.toString(user));

		// 在此进行业务操作，比如数据库持久化
		user.setId(123);
		return "redirect:view2/" + user.getId();
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String showUploadPage(@RequestParam(value = "multi", required = false) Boolean multi) {
		if (multi != null && multi) {
			return "course_admin/multifile";
		}
		return "course_admin/file";
	}

	@RequestMapping(value = "/doUpload", method = RequestMethod.POST)
	public String doUploadFile(@RequestParam("file") MultipartFile file) throws IOException {

		if (!file.isEmpty()) {
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File("c:\\temp\\imooc\\", System.currentTimeMillis() + file.getOriginalFilename()));
		}

		return "success";
	}

	@RequestMapping(value = "/doUpload2", method = RequestMethod.POST)
	public String doUploadFile2(MultipartHttpServletRequest multiRequest) throws IOException {

		Iterator<String> filesNames = multiRequest.getFileNames();
		while (filesNames.hasNext()) {
			String fileName = filesNames.next();
			MultipartFile file = multiRequest.getFile(fileName);
			if (!file.isEmpty()) {
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File("c:\\temp\\imooc\\", System.currentTimeMillis() + file.getOriginalFilename()));
			}

		}

		return "success";
	}

	@RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
	public @ResponseBody
	User getCourseInJson(@PathVariable Integer courseId) {
		return userService.getMapper().selectByPrimaryKey(courseId);
	}

	@RequestMapping(value = "/jsontype/{courseId}", method = RequestMethod.GET)
	public ResponseEntity<User> getCourseInJson2(@PathVariable Integer courseId) {
		User user = userService.getMapper().selectByPrimaryKey(courseId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}