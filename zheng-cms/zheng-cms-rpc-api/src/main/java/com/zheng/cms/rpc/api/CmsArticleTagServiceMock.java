package com.zheng.cms.rpc.api;

import com.zheng.common.base.BaseServiceMock;
import com.zheng.cms.dao.mapper.CmsArticleTagMapper;
import com.zheng.cms.dao.model.CmsArticleTag;
import com.zheng.cms.dao.model.CmsArticleTagExample;

/**
* 降级实现CmsArticleTagService接口
* Created by shuzheng on 2017/4/5.
*/
public class CmsArticleTagServiceMock extends BaseServiceMock<CmsArticleTagMapper, CmsArticleTag, CmsArticleTagExample> implements CmsArticleTagService {

}
