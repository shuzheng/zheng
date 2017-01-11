package com.zheng.upms.dao.mapper;

import com.zheng.upms.dao.model.UpmsUser;
import com.zheng.upms.dao.model.UpmsUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsUserMapper {
    long countByExample(UpmsUserExample example);

    int deleteByExample(UpmsUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(UpmsUser record);

    int insertSelective(UpmsUser record);

    List<UpmsUser> selectByExample(UpmsUserExample example);

    UpmsUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") UpmsUser record, @Param("example") UpmsUserExample example);

    int updateByExample(@Param("record") UpmsUser record, @Param("example") UpmsUserExample example);

    int updateByPrimaryKeySelective(UpmsUser record);

    int updateByPrimaryKey(UpmsUser record);
}