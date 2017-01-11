package com.zheng.cms.service;

import com.zheng.cms.dao.mapper.CmsTagMapper;

/**
 * 标签service接口
 * Created by shuzheng on 2016/11/14.
 */
public interface CmsTagService extends BaseService<CmsTagMapper> {

    // 批量删除
    int deleteByPrimaryKeys(String ids);

}