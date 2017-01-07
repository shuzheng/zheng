package com.zheng.cms.rpc.service.impl;

import com.zheng.cms.dao.mapper.CmsCommentMapper;
import com.zheng.cms.dao.model.CmsComment;
import com.zheng.cms.dao.model.CmsCommentExample;
import com.zheng.cms.rpc.api.CmsCommentService;
import com.zheng.common.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 评论service实现
 * Created by shuzheng on 2017/01/07.
 */
@Service
@Transactional
public class CmsCommentServiceImpl extends BaseServiceImpl<CmsCommentMapper, CmsComment, CmsCommentExample> implements CmsCommentService {

    private static Logger _log = LoggerFactory.getLogger(CmsCommentServiceImpl.class);

}