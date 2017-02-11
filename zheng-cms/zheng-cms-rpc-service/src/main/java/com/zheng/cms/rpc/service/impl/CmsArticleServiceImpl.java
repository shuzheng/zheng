package com.zheng.cms.rpc.service.impl;

import com.zheng.cms.dao.mapper.CmsArticleMapper;
import com.zheng.cms.dao.model.CmsArticle;
import com.zheng.cms.dao.model.CmsArticleExample;
import com.zheng.cms.rpc.api.CmsArticleService;
import com.zheng.common.annotation.BaseService;
import com.zheng.common.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文章service实现
 * Created by shuzheng on 2017/01/07.
 */
@Service
@Transactional
@BaseService
public class CmsArticleServiceImpl extends BaseServiceImpl<CmsArticleMapper, CmsArticle, CmsArticleExample> implements CmsArticleService {

    private static Logger _log = LoggerFactory.getLogger(CmsArticleServiceImpl.class);

}