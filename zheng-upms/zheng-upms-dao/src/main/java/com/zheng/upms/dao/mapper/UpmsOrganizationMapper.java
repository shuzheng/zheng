package com.zheng.upms.dao.mapper;

import com.zheng.upms.dao.model.UpmsOrganization;
import com.zheng.upms.dao.model.UpmsOrganizationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsOrganizationMapper {
    long countByExample(UpmsOrganizationExample example);

    int deleteByExample(UpmsOrganizationExample example);

    int deleteByPrimaryKey(Integer organizationId);

    int insert(UpmsOrganization record);

    int insertSelective(UpmsOrganization record);

    List<UpmsOrganization> selectByExample(UpmsOrganizationExample example);

    UpmsOrganization selectByPrimaryKey(Integer organizationId);

    int updateByExampleSelective(@Param("record") UpmsOrganization record, @Param("example") UpmsOrganizationExample example);

    int updateByExample(@Param("record") UpmsOrganization record, @Param("example") UpmsOrganizationExample example);

    int updateByPrimaryKeySelective(UpmsOrganization record);

    int updateByPrimaryKey(UpmsOrganization record);
}