package com.zheng.cms.service.impl;

import com.zheng.cms.dao.mapper.CmsTagMapper;
import com.zheng.cms.service.CmsTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 标签service实现
 * Created by shuzheng on 2016/11/14.
 */
@Service
@Transactional
public class CmsTagServiceImpl implements CmsTagService {

	private static Logger _log = LoggerFactory.getLogger(CmsTagServiceImpl.class);

	@Autowired
	private CmsTagMapper cmsTagMapper;

	@Override
	public CmsTagMapper getMapper() {
		return cmsTagMapper;
	}

}