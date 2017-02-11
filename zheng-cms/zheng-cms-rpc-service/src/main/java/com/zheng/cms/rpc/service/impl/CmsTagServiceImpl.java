package com.zheng.cms.rpc.service.impl;

import com.zheng.cms.dao.mapper.CmsTagMapper;
import com.zheng.cms.dao.model.CmsTag;
import com.zheng.cms.dao.model.CmsTagExample;
import com.zheng.cms.rpc.api.CmsTagService;
import com.zheng.common.annotation.BaseService;
import com.zheng.common.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 标签service实现
 * Created by shuzheng on 2017/01/07.
 */
@Service
@Transactional
@BaseService
public class CmsTagServiceImpl extends BaseServiceImpl<CmsTagMapper, CmsTag, CmsTagExample> implements CmsTagService {

	private static Logger _log = LoggerFactory.getLogger(CmsTagServiceImpl.class);

}