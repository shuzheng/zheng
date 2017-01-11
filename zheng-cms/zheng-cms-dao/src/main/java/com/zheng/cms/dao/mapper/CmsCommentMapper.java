package com.zheng.cms.dao.mapper;

import com.zheng.cms.dao.model.CmsComment;
import com.zheng.cms.dao.model.CmsCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsCommentMapper {
    long countByExample(CmsCommentExample example);

    int deleteByExample(CmsCommentExample example);

    int deleteByPrimaryKey(Integer commentId);

    int insert(CmsComment record);

    int insertSelective(CmsComment record);

    List<CmsComment> selectByExampleWithBLOBs(CmsCommentExample example);

    List<CmsComment> selectByExample(CmsCommentExample example);

    CmsComment selectByPrimaryKey(Integer commentId);

    int updateByExampleSelective(@Param("record") CmsComment record, @Param("example") CmsCommentExample example);

    int updateByExampleWithBLOBs(@Param("record") CmsComment record, @Param("example") CmsCommentExample example);

    int updateByExample(@Param("record") CmsComment record, @Param("example") CmsCommentExample example);

    int updateByPrimaryKeySelective(CmsComment record);

    int updateByPrimaryKeyWithBLOBs(CmsComment record);

    int updateByPrimaryKey(CmsComment record);
}