package com.zheng.cms.web.controller;

import com.zheng.cms.dao.model.User;
import com.zheng.cms.rpc.api.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * 示例controller
 * @author shuzheng
 * @date 2016年7月6日 下午6:16:00
 */
@Controller
@RequestMapping("/hello")
public class HelloController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(HelloController.class);

	@Autowired
	private UserService userService;
	
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
	public String viewCourse(@RequestParam("courseId") Integer courseId, ModelMap modelMap) {

		User user = userService.selectByPrimaryKey(courseId);
		modelMap.put("user", user);
		return "course_overview";
	}

	// 本方法将处理 /hello/view2/123 形式的URL
	@RequestMapping("/view2/{courseId}")
	public String viewCourse2(@PathVariable("courseId") Integer courseId, Map<String, Object> map) {

		User user = userService.selectByPrimaryKey(courseId);
		map.put("user", user);
		return "course_overview";
	}

	// 本方法将处理 /hello/view3?courseId=123 形式的URL
	@RequestMapping("/view3")
	public String viewCourse3(HttpServletRequest request) {

		Integer courseId = Integer.valueOf(request.getParameter("courseId"));
		User user = userService.selectByPrimaryKey(courseId);
		request.setAttribute("user", user);

		return "course_overview";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET, params = "add")
	public String createCourse() {
		return "course_admin/edit";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String doSave(@ModelAttribute User user) {

		_log.debug("Info of Course:");
		_log.debug(ReflectionToStringBuilder.toString(user));

		// 在此进行业务操作，比如数据库持久化
		user.setUserId(123);
		return "redirect:view2/" + user.getUserId();
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
		return userService.selectByPrimaryKey(courseId);
	}

	@RequestMapping(value = "/jsontype/{courseId}", method = RequestMethod.GET)
	public ResponseEntity<User> getCourseInJson2(@PathVariable Integer courseId) {
		User user = userService.selectByPrimaryKey(courseId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}