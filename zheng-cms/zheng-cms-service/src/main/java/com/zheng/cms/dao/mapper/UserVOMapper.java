package com.zheng.cms.dao.mapper;

import org.springframework.stereotype.Repository;

import com.zheng.cms.dao.model.UserVO;

/**
 * 用户mapper
 * @author shuzheng
 * @date 2016年7月6日 下午6:05:54
 */
@Repository
public interface UserVOMapper extends UserMapper {

	UserVO selectUserWithBook(int id);
	
}