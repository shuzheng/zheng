package com.zheng.cms.rpc.service.impl;

import com.zheng.common.annotation.BaseService;
import com.zheng.common.base.BaseServiceImpl;
import com.zheng.cms.dao.mapper.CmsCategoryTagMapper;
import com.zheng.cms.dao.model.CmsCategoryTag;
import com.zheng.cms.dao.model.CmsCategoryTagExample;
import com.zheng.cms.rpc.api.CmsCategoryTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsCategoryTagService实现
* Created by shuzheng on 2017/4/5.
*/
@Service
@Transactional
@BaseService
public class CmsCategoryTagServiceImpl extends BaseServiceImpl<CmsCategoryTagMapper, CmsCategoryTag, CmsCategoryTagExample> implements CmsCategoryTagService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsCategoryTagServiceImpl.class);

    @Autowired
    CmsCategoryTagMapper cmsCategoryTagMapper;

}