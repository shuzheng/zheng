package com.zheng.cms.service.impl;

import com.zheng.cms.dao.mapper.CmsCommentMapper;
import com.zheng.cms.service.CmsCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 评论service实现
 * Created by shuzheng on 2016/11/14.
 */
@Service
@Transactional
public class CmsCommentServiceImpl implements CmsCommentService {

    private static Logger _log = LoggerFactory.getLogger(CmsCommentServiceImpl.class);

    @Autowired
    private CmsCommentMapper cmsCommentMapper;

    @Override
    public CmsCommentMapper getMapper() {
        return cmsCommentMapper;
    }

}