package com.zheng.cms.rpc.api;

import com.zheng.cms.dao.mapper.CmsUserMapper;
import com.zheng.cms.dao.model.CmsUser;
import com.zheng.cms.dao.model.CmsUserExample;
import com.zheng.cms.dao.model.UserVO;
import com.zheng.common.base.BaseServiceMock;

/**
 * 降级实现UserService接口
 * Created by shuzheng on 2017/2/14.
 */
public class UserServiceMock extends BaseServiceMock<CmsUserMapper, CmsUser, CmsUserExample> implements UserService {

	@Override
	public UserVO selectUserWithBook(int id) {
		return null;
	}

}
