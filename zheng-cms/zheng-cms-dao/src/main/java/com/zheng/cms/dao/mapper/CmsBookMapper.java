package com.zheng.cms.dao.mapper;

import com.zheng.cms.dao.model.CmsBook;
import com.zheng.cms.dao.model.CmsBookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsBookMapper {
    long countByExample(CmsBookExample example);

    int deleteByExample(CmsBookExample example);

    int deleteByPrimaryKey(Integer bookId);

    int insert(CmsBook record);

    int insertSelective(CmsBook record);

    List<CmsBook> selectByExample(CmsBookExample example);

    CmsBook selectByPrimaryKey(Integer bookId);

    int updateByExampleSelective(@Param("record") CmsBook record, @Param("example") CmsBookExample example);

    int updateByExample(@Param("record") CmsBook record, @Param("example") CmsBookExample example);

    int updateByPrimaryKeySelective(CmsBook record);

    int updateByPrimaryKey(CmsBook record);
}