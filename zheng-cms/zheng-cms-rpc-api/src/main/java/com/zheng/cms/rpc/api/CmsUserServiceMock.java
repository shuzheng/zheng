package com.zheng.cms.rpc.api;

import com.zheng.common.base.BaseServiceMock;
import com.zheng.cms.dao.mapper.CmsUserMapper;
import com.zheng.cms.dao.model.CmsUser;
import com.zheng.cms.dao.model.CmsUserExample;

/**
* 降级实现CmsUserService接口
* Created by shuzheng on 2017/3/20.
*/
public class CmsUserServiceMock extends BaseServiceMock<CmsUserMapper, CmsUser, CmsUserExample> implements CmsUserService {

}
