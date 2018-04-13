package com.zheng.cms.rpc.api;

import com.zheng.cms.dao.model.CmsArticle;
import com.zheng.cms.dao.model.CmsArticleExample;
import com.zheng.common.base.BaseService;

import java.util.List;

/**
* CmsArticleService接口
* Created by shuzheng on 2017/4/5.
*/
public interface CmsArticleService extends BaseService<CmsArticle, CmsArticleExample> {

    /**
     * 根据类目获取文章列表
     * @param categoryId
     * @param offset
     * @param limit
     * @return
     */
    List<CmsArticle> selectCmsArticlesByCategoryId(Integer categoryId, Integer offset, Integer limit);

    /**
     * 根据类目获取文章数量
     * @param categoryId
     * @return
     */
    long countByCategoryId(Integer categoryId);

    /**
     * 根据标签获取文章列表
     * @param tagId
     * @param offset
     * @param limit
     * @return
     */
    List<CmsArticle> selectCmsArticlesByTagId(Integer tagId, Integer offset, Integer limit);

    /**
     * 根据标签获取文章数量
     * @param tagId
     * @return
     */
    long countByTagId(Integer tagId);

}