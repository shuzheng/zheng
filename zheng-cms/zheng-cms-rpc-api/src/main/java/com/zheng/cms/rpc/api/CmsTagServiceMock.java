package com.zheng.cms.rpc.api;

import com.zheng.cms.dao.mapper.CmsTagMapper;
import com.zheng.cms.dao.model.CmsTag;
import com.zheng.cms.dao.model.CmsTagExample;
import com.zheng.common.base.BaseServiceMock;

/**
 * 降级实现CmsTagService接口
 * Created by shuzheng on 2017/2/14.
 */
public class CmsTagServiceMock extends BaseServiceMock<CmsTagMapper, CmsTag, CmsTagExample> implements CmsTagService {

}
