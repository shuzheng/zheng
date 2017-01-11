package com.zheng.cms.service;

import com.zheng.cms.dao.mapper.CmsUserMapper;
import com.zheng.cms.dao.model.UserVO;

/**
 * 用户service接口
 * @author shuzheng
 * @date 2016年7月6日 下午6:03:45
 */
public interface UserService extends BaseService<CmsUserMapper> {
	
	/**
	 * 获取带book数据的用户
	 * @param id
	 * @return
	 */
	UserVO selectUserWithBook(int id);
	
}