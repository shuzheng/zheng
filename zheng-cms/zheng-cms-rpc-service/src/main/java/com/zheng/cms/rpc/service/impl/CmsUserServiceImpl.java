package com.zheng.cms.rpc.service.impl;

import com.zheng.common.annotation.BaseService;
import com.zheng.common.base.BaseServiceImpl;
import com.zheng.cms.dao.mapper.CmsUserMapper;
import com.zheng.cms.dao.model.CmsUser;
import com.zheng.cms.dao.model.CmsUserExample;
import com.zheng.cms.rpc.api.CmsUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsUserService实现
* Created by shuzheng on 2017/3/20.
*/
@Service
@Transactional
@BaseService
public class CmsUserServiceImpl extends BaseServiceImpl<CmsUserMapper, CmsUser, CmsUserExample> implements CmsUserService {

    private static Logger _log = LoggerFactory.getLogger(CmsUserServiceImpl.class);

    @Autowired
    CmsUserMapper cmsUserMapper;

}