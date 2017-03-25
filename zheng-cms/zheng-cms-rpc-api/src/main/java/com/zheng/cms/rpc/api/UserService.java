package com.zheng.cms.rpc.api;

import com.zheng.cms.dao.model.CmsUser;
import com.zheng.cms.dao.model.CmsUserExample;
import com.zheng.cms.dao.model.UserVO;
import com.zheng.common.base.BaseService;

/**
 * 用户service接口
 * Created by shuzheng on 2016/11/14.
 */
public interface UserService extends BaseService<CmsUser, CmsUserExample> {
	
	/**
	 * 获取带book数据的用户
	 * @param id
	 * @return
	 */
	UserVO selectUserWithBook(int id);
	
}