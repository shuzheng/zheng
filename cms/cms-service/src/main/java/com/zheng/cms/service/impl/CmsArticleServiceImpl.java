package com.zheng.cms.service.impl;

import com.zheng.cms.dao.mapper.CmsArticleMapper;
import com.zheng.cms.service.CmsArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文章service实现
 * Created by shuzheng on 2016/11/14.
 */
@Service
@Transactional
public class CmsArticleServiceImpl implements CmsArticleService {

    private static Logger _log = LoggerFactory.getLogger(CmsArticleServiceImpl.class);

    @Autowired
    private CmsArticleMapper cmsArticleMapper;

    @Override
    public CmsArticleMapper getMapper() {
        return cmsArticleMapper;
    }

}