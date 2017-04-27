package com.zheng.ucenter.dao.mapper;

import com.zheng.ucenter.dao.model.UcenterUser;
import com.zheng.ucenter.dao.model.UcenterUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UcenterUserMapper {
    long countByExample(UcenterUserExample example);

    int deleteByExample(UcenterUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(UcenterUser record);

    int insertSelective(UcenterUser record);

    List<UcenterUser> selectByExample(UcenterUserExample example);

    UcenterUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") UcenterUser record, @Param("example") UcenterUserExample example);

    int updateByExample(@Param("record") UcenterUser record, @Param("example") UcenterUserExample example);

    int updateByPrimaryKeySelective(UcenterUser record);

    int updateByPrimaryKey(UcenterUser record);
}