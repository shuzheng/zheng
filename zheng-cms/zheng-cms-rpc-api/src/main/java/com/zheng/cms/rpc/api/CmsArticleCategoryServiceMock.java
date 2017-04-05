package com.zheng.cms.rpc.api;

import com.zheng.common.base.BaseServiceMock;
import com.zheng.cms.dao.mapper.CmsArticleCategoryMapper;
import com.zheng.cms.dao.model.CmsArticleCategory;
import com.zheng.cms.dao.model.CmsArticleCategoryExample;

/**
* 降级实现CmsArticleCategoryService接口
* Created by shuzheng on 2017/4/5.
*/
public class CmsArticleCategoryServiceMock extends BaseServiceMock<CmsArticleCategoryMapper, CmsArticleCategory, CmsArticleCategoryExample> implements CmsArticleCategoryService {

}
