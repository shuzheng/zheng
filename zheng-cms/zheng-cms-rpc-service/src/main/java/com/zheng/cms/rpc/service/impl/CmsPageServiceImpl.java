package com.zheng.cms.rpc.service.impl;

import com.zheng.cms.dao.mapper.CmsPageMapper;
import com.zheng.cms.dao.model.CmsPage;
import com.zheng.cms.dao.model.CmsPageExample;
import com.zheng.cms.rpc.api.CmsPageService;
import com.zheng.common.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 页面service实现
 * Created by shuzheng on 2017/01/08.
 */
@Service
@Transactional
public class CmsPageServiceImpl extends BaseServiceImpl<CmsPageMapper, CmsPage, CmsPageExample> implements CmsPageService {

	private static Logger _log = LoggerFactory.getLogger(CmsPageServiceImpl.class);

}