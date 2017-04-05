package com.zheng.cms.rpc.api;

import com.zheng.common.base.BaseServiceMock;
import com.zheng.cms.dao.mapper.CmsCommentMapper;
import com.zheng.cms.dao.model.CmsComment;
import com.zheng.cms.dao.model.CmsCommentExample;

/**
* 降级实现CmsCommentService接口
* Created by shuzheng on 2017/4/5.
*/
public class CmsCommentServiceMock extends BaseServiceMock<CmsCommentMapper, CmsComment, CmsCommentExample> implements CmsCommentService {

}
