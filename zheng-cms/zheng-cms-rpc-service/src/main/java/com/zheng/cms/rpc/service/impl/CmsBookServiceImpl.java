package com.zheng.cms.rpc.service.impl;

import com.zheng.common.annotation.BaseService;
import com.zheng.common.base.BaseServiceImpl;
import com.zheng.cms.dao.mapper.CmsBookMapper;
import com.zheng.cms.dao.model.CmsBook;
import com.zheng.cms.dao.model.CmsBookExample;
import com.zheng.cms.rpc.api.CmsBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsBookService实现
* Created by shuzheng on 2017/3/20.
*/
@Service
@Transactional
@BaseService
public class CmsBookServiceImpl extends BaseServiceImpl<CmsBookMapper, CmsBook, CmsBookExample> implements CmsBookService {

    private static Logger _log = LoggerFactory.getLogger(CmsBookServiceImpl.class);

    @Autowired
    CmsBookMapper cmsBookMapper;

}