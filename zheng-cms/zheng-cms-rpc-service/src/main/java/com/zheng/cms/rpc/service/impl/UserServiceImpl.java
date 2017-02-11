package com.zheng.cms.rpc.service.impl;

import com.zheng.cms.dao.mapper.CmsUserMapper;
import com.zheng.cms.dao.mapper.UserVOMapper;
import com.zheng.cms.dao.model.CmsUser;
import com.zheng.cms.dao.model.CmsUserExample;
import com.zheng.cms.dao.model.UserVO;
import com.zheng.cms.rpc.api.UserService;
import com.zheng.common.annotation.BaseService;
import com.zheng.common.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户service实现
 * @author shuzheng
 * @date 2016年7月6日 下午6:07:58
 */
@Service
@Transactional
@BaseService
public class UserServiceImpl extends BaseServiceImpl<CmsUserMapper, CmsUser, CmsUserExample> implements UserService {

	private static Logger _log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserVOMapper userVOMapper;

	/**
	 * 获取带book数据的用户
	 * @param id
	 * @return
	 */
	@Override
	@Cacheable(value="ehcache")
	public UserVO selectUserWithBook(int id) {
		return userVOMapper.selectUserWithBook(id);
	}

}