package com.zheng.ucenter.dao.mapper;

import com.zheng.ucenter.dao.model.UcenterUserLog;
import com.zheng.ucenter.dao.model.UcenterUserLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UcenterUserLogMapper {
    long countByExample(UcenterUserLogExample example);

    int deleteByExample(UcenterUserLogExample example);

    int deleteByPrimaryKey(Integer userLogId);

    int insert(UcenterUserLog record);

    int insertSelective(UcenterUserLog record);

    List<UcenterUserLog> selectByExampleWithBLOBs(UcenterUserLogExample example);

    List<UcenterUserLog> selectByExample(UcenterUserLogExample example);

    UcenterUserLog selectByPrimaryKey(Integer userLogId);

    int updateByExampleSelective(@Param("record") UcenterUserLog record, @Param("example") UcenterUserLogExample example);

    int updateByExampleWithBLOBs(@Param("record") UcenterUserLog record, @Param("example") UcenterUserLogExample example);

    int updateByExample(@Param("record") UcenterUserLog record, @Param("example") UcenterUserLogExample example);

    int updateByPrimaryKeySelective(UcenterUserLog record);

    int updateByPrimaryKeyWithBLOBs(UcenterUserLog record);

    int updateByPrimaryKey(UcenterUserLog record);
}