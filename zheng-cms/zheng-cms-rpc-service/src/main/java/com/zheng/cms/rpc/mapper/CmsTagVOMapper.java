package com.zheng.cms.rpc.mapper;

/**
 * 标签VOMapper
 * Created by shuzheng on 2017/01/07.
 */
public interface CmsTagVOMapper {

    int up(Integer articleId);

    int down(Integer articleId);

}