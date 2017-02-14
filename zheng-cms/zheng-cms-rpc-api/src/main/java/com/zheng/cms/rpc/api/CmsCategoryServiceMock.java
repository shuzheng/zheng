package com.zheng.cms.rpc.api;

import com.zheng.cms.dao.mapper.CmsCategoryMapper;
import com.zheng.cms.dao.model.CmsCategory;
import com.zheng.cms.dao.model.CmsCategoryExample;
import com.zheng.common.base.BaseServiceMock;

/**
 * 降级实现CmsCategoryService接口
 * Created by shuzheng on 2017/2/14.
 */
public class CmsCategoryServiceMock extends BaseServiceMock<CmsCategoryMapper, CmsCategory, CmsCategoryExample> implements CmsCategoryService {

}
