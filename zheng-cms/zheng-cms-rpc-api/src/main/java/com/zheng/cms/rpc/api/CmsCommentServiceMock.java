package com.zheng.cms.rpc.api;

import com.zheng.cms.dao.mapper.CmsCommentMapper;
import com.zheng.cms.dao.model.CmsComment;
import com.zheng.cms.dao.model.CmsCommentExample;
import com.zheng.common.base.BaseServiceMock;

/**
 * 降级实现CmsCommentService接口
 * Created by shuzheng on 2017/2/14.
 */
public class CmsCommentServiceMock extends BaseServiceMock<CmsCommentMapper, CmsComment, CmsCommentExample> implements CmsCommentService {

}
