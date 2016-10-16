package com.zheng.cms.service.impl;

import java.util.List;
import java.util.Map;

import com.zheng.cms.mapper.UserMapper;
import com.zheng.cms.model.User;
import com.zheng.cms.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zheng.cms.mapper.UserVOMapper;
import com.zheng.cms.model.UserVO;
import com.zheng.cms.service.UserService;

/**
 * 用户service实现
 * @author shuzheng
 * @date 2016年7月6日 下午6:07:58
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserVOMapper userVOMapper;

	/**
	 * 获取基本操作mapper
	 * @return
	 */
	@Override
	public UserMapper getMapper() {
		return userMapper;
	}
	
	/**
	 * 获取带book数据的用户
	 * @param id
	 * @return
	 */
	@Override
	public UserVO selectUserWithBook(int id) {
		return userVOMapper.selectUserWithBook(id);
	}

	/**
	 * 根据条件获取用户列表
	 * @param map
	 * @return
	 */
	@Override
	public List<User> selectAll(Map<String, Object> map) {
		return userVOMapper.selectAll(map);
	}

	/**
	 * 插入用户并返回主键
	 * @param user
	 */
	@Override
	public void insertAutoKey(User user) {
		userVOMapper.insertAutoKey(user);
	}

	
}