package com.zheng.cms.service;

import java.util.List;
import java.util.Map;

import com.zheng.cms.mapper.UserMapper;
import com.zheng.cms.model.User;
import com.zheng.cms.model.UserVO;
import com.zheng.cms.service.BaseService;

/**
 * 用户service接口
 * @author shuzheng
 * @date 2016年7月6日 下午6:03:45
 */
public interface UserService extends BaseService<UserMapper> {
	
	/**
	 * 获取带book数据的用户
	 * @param id
	 * @return
	 */
	UserVO selectUserWithBook(int id);

	/**
	 * 根据条件获取用户列表
	 * @param map
	 * @return
	 */
	List<User> selectAll(Map<String, Object> map);

	/**
	 * 插入用户并返回主键
	 * @param user
	 */
	void insertAutoKey(User user);
	
}