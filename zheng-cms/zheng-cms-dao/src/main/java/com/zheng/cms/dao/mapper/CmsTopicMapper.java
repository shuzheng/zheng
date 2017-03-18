package com.zheng.cms.dao.mapper;

import com.zheng.cms.dao.model.CmsTopic;
import com.zheng.cms.dao.model.CmsTopicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsTopicMapper {
    long countByExample(CmsTopicExample example);

    int deleteByExample(CmsTopicExample example);

    int deleteByPrimaryKey(Integer topicId);

    int insert(CmsTopic record);

    int insertSelective(CmsTopic record);

    List<CmsTopic> selectByExample(CmsTopicExample example);

    CmsTopic selectByPrimaryKey(Integer topicId);

    int updateByExampleSelective(@Param("record") CmsTopic record, @Param("example") CmsTopicExample example);

    int updateByExample(@Param("record") CmsTopic record, @Param("example") CmsTopicExample example);

    int updateByPrimaryKeySelective(CmsTopic record);

    int updateByPrimaryKey(CmsTopic record);
}