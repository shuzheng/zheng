package com.zheng.cms.rpc.api;

import com.zheng.cms.dao.mapper.CmsArticleMapper;
import com.zheng.cms.dao.model.CmsArticle;
import com.zheng.cms.dao.model.CmsArticleExample;
import com.zheng.common.base.BaseServiceMock;

/**
 * 降级实现CmsArticleService接口
 * Created by shuzheng on 2017/2/14.
 */
public class CmsArticleServiceMock extends BaseServiceMock<CmsArticleMapper, CmsArticle, CmsArticleExample> implements CmsArticleService {

}
