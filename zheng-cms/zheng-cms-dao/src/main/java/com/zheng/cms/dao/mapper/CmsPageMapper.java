package com.zheng.cms.dao.mapper;

import com.zheng.cms.dao.model.CmsPage;
import com.zheng.cms.dao.model.CmsPageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsPageMapper {
    long countByExample(CmsPageExample example);

    int deleteByExample(CmsPageExample example);

    int deleteByPrimaryKey(Integer pageId);

    int insert(CmsPage record);

    int insertSelective(CmsPage record);

    List<CmsPage> selectByExampleWithBLOBs(CmsPageExample example);

    List<CmsPage> selectByExample(CmsPageExample example);

    CmsPage selectByPrimaryKey(Integer pageId);

    int updateByExampleSelective(@Param("record") CmsPage record, @Param("example") CmsPageExample example);

    int updateByExampleWithBLOBs(@Param("record") CmsPage record, @Param("example") CmsPageExample example);

    int updateByExample(@Param("record") CmsPage record, @Param("example") CmsPageExample example);

    int updateByPrimaryKeySelective(CmsPage record);

    int updateByPrimaryKeyWithBLOBs(CmsPage record);

    int updateByPrimaryKey(CmsPage record);
}