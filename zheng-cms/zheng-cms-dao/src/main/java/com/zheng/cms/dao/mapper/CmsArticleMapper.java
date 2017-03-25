package com.zheng.cms.dao.mapper;

import com.zheng.cms.dao.model.CmsArticle;
import com.zheng.cms.dao.model.CmsArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsArticleMapper {
    long countByExample(CmsArticleExample example);

    int deleteByExample(CmsArticleExample example);

    int deleteByPrimaryKey(Integer articleId);

    int insert(CmsArticle record);

    int insertSelective(CmsArticle record);

    List<CmsArticle> selectByExampleWithBLOBs(CmsArticleExample example);

    List<CmsArticle> selectByExample(CmsArticleExample example);

    CmsArticle selectByPrimaryKey(Integer articleId);

    int updateByExampleSelective(@Param("record") CmsArticle record, @Param("example") CmsArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") CmsArticle record, @Param("example") CmsArticleExample example);

    int updateByExample(@Param("record") CmsArticle record, @Param("example") CmsArticleExample example);

    int updateByPrimaryKeySelective(CmsArticle record);

    int updateByPrimaryKeyWithBLOBs(CmsArticle record);

    int updateByPrimaryKey(CmsArticle record);
}