package com.zheng.cms.rpc.api;

import com.zheng.cms.dao.mapper.CmsPageMapper;
import com.zheng.cms.dao.model.CmsPage;
import com.zheng.cms.dao.model.CmsPageExample;
import com.zheng.common.base.BaseServiceMock;

/**
 * 降级实现CmsPageService接口
 * Created by shuzheng on 2017/2/14.
 */
public class CmsPageServiceMock extends BaseServiceMock<CmsPageMapper, CmsPage, CmsPageExample> implements CmsPageService {

}
