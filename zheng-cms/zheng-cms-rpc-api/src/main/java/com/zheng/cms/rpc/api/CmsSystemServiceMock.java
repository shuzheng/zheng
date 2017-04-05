package com.zheng.cms.rpc.api;

import com.zheng.common.base.BaseServiceMock;
import com.zheng.cms.dao.mapper.CmsSystemMapper;
import com.zheng.cms.dao.model.CmsSystem;
import com.zheng.cms.dao.model.CmsSystemExample;

/**
* 降级实现CmsSystemService接口
* Created by shuzheng on 2017/4/5.
*/
public class CmsSystemServiceMock extends BaseServiceMock<CmsSystemMapper, CmsSystem, CmsSystemExample> implements CmsSystemService {

}
