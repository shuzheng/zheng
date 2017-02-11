package com.zheng.cms.rpc.service.impl;

import com.zheng.cms.dao.mapper.CmsCategoryMapper;
import com.zheng.cms.dao.model.CmsCategory;
import com.zheng.cms.dao.model.CmsCategoryExample;
import com.zheng.cms.rpc.api.CmsCategoryService;
import com.zheng.common.annotation.BaseService;
import com.zheng.common.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类目service实现
 * Created by shuzheng on 2017/01/07.
 */
@Service
@Transactional
@BaseService
public class CmsCategoryServiceImpl extends BaseServiceImpl<CmsCategoryMapper, CmsCategory, CmsCategoryExample> implements CmsCategoryService {

    private static Logger _log = LoggerFactory.getLogger(CmsCategoryServiceImpl.class);

}