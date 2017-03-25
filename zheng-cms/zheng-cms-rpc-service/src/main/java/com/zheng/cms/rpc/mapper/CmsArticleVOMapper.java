package com.zheng.cms.rpc.mapper;

/**
 * 文章VOMapper
 * Created by shuzheng on 2017/01/07.
 */
public interface CmsArticleVOMapper {

    int up(Integer articleId);

    int down(Integer articleId);

}