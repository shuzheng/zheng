package com.zheng.cms.rpc.mapper;

import com.zheng.cms.dao.model.UserVO;

/**
 * 用户VOMapper
 * Created by shuzheng on 2017/01/07.
 */
public interface UserVOMapper {

	UserVO selectUserWithBook(int id);
	
}