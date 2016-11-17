package com.zheng.springboot.web;

import com.zheng.springboot.domain.User;
import com.zheng.springboot.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by ZhangShuzheng on 2016/11/16.
 */
@RestController
@RequestMapping(value="/users")     // 通过这里配置使下面的映射都在/users下
public class UserController {

	@Autowired
	private UserService userSerivce;

	// 创建线程安全的Map
	static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

	@ApiOperation(value="获取用户列表", notes="")
	@RequestMapping(value={""}, method=RequestMethod.GET)
	public List<User> getUserList() {
		List<User> r = new ArrayList<User>(users.values());
		return r;
	}
	@ApiOperation(value="创建用户", notes="根据User对象创建用户")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@RequestMapping(value="", method=RequestMethod.POST)
	public String postUser(@RequestBody User user) {
		users.put(user.getId(), user);
		userSerivce.create(user.getName(), user.getAge());
		return "success";
	}
	@ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public User getUser(@PathVariable Long id) {
		return users.get(id);
	}
	@ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path"),
			@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	})
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public String putUser(@PathVariable Long id, @RequestBody User user) {
		User u = users.get(id);
		u.setName(user.getName());
		u.setAge(user.getAge());
		users.put(id, u);
		return "success";
	}
	@ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String deleteUser(@PathVariable Long id) {
		users.remove(id);
		return "success";
	}

}
