package com.zheng.upms.dao.mapper;

import com.zheng.upms.dao.model.UpmsUserRole;
import com.zheng.upms.dao.model.UpmsUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsUserRoleMapper {
    long countByExample(UpmsUserRoleExample example);

    int deleteByExample(UpmsUserRoleExample example);

    int deleteByPrimaryKey(Integer userRoleId);

    int insert(UpmsUserRole record);

    int insertSelective(UpmsUserRole record);

    List<UpmsUserRole> selectByExample(UpmsUserRoleExample example);

    UpmsUserRole selectByPrimaryKey(Integer userRoleId);

    int updateByExampleSelective(@Param("record") UpmsUserRole record, @Param("example") UpmsUserRoleExample example);

    int updateByExample(@Param("record") UpmsUserRole record, @Param("example") UpmsUserRoleExample example);

    int updateByPrimaryKeySelective(UpmsUserRole record);

    int updateByPrimaryKey(UpmsUserRole record);
}