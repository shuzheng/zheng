package com.zheng.cms.service.impl;

import com.zheng.cms.dao.mapper.CmsCategoryMapper;
import com.zheng.cms.service.CmsCategoryService;
import org.apache.commons.lang.StringUtils;
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

    // 批量删除
    @Override
    public int deleteByPrimaryKeys(String ids) {
        if (StringUtils.isBlank(ids)) {
            return 0;
        }
        String[] idArray = ids.split("-");
        int count = 0;
        for (String id : idArray) {
            if (StringUtils.isBlank(id)) {
                continue;
            }
            try {
                count += cmsCategoryMapper.deleteByPrimaryKey(Integer.parseInt(id));
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        return count;
    }
}