package com.zheng.cms.service;

import com.zheng.cms.dao.mapper.CmsCommentMapper;

/**
 * 评论service接口
 * Created by shuzheng on 2016/11/14.
 */
public interface CmsCommentService extends BaseService<CmsCommentMapper> {

    // 批量删除
    int deleteByPrimaryKeys(String ids);

}