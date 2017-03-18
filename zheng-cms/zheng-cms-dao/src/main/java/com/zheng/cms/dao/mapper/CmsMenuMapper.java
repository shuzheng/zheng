package com.zheng.cms.dao.mapper;

import com.zheng.cms.dao.model.CmsMenu;
import com.zheng.cms.dao.model.CmsMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsMenuMapper {
    long countByExample(CmsMenuExample example);

    int deleteByExample(CmsMenuExample example);

    int deleteByPrimaryKey(Integer pageId);

    int insert(CmsMenu record);

    int insertSelective(CmsMenu record);

    List<CmsMenu> selectByExample(CmsMenuExample example);

    CmsMenu selectByPrimaryKey(Integer pageId);

    int updateByExampleSelective(@Param("record") CmsMenu record, @Param("example") CmsMenuExample example);

    int updateByExample(@Param("record") CmsMenu record, @Param("example") CmsMenuExample example);

    int updateByPrimaryKeySelective(CmsMenu record);

    int updateByPrimaryKey(CmsMenu record);
}