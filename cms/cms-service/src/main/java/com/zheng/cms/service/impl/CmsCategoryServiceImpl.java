package com.zheng.cms.service.impl;

import com.zheng.cms.dao.mapper.CmsCategoryMapper;
import com.zheng.cms.service.CmsCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类目service实现
 * Created by shuzheng on 2016/11/14.
 */
@Service
@Transactional
public class CmsCategoryServiceImpl implements CmsCategoryService {

    private static Logger _log = LoggerFactory.getLogger(CmsCategoryServiceImpl.class);

    @Autowired
    private CmsCategoryMapper cmsCategoryMapper;

    @Override
    public CmsCategoryMapper getMapper() {
        return cmsCategoryMapper;
    }

}