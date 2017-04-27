package com.zheng.ucenter.dao.mapper;

import com.zheng.ucenter.dao.model.UcenterUserOauth;
import com.zheng.ucenter.dao.model.UcenterUserOauthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UcenterUserOauthMapper {
    long countByExample(UcenterUserOauthExample example);

    int deleteByExample(UcenterUserOauthExample example);

    int deleteByPrimaryKey(Integer userOauthId);

    int insert(UcenterUserOauth record);

    int insertSelective(UcenterUserOauth record);

    List<UcenterUserOauth> selectByExampleWithBLOBs(UcenterUserOauthExample example);

    List<UcenterUserOauth> selectByExample(UcenterUserOauthExample example);

    UcenterUserOauth selectByPrimaryKey(Integer userOauthId);

    int updateByExampleSelective(@Param("record") UcenterUserOauth record, @Param("example") UcenterUserOauthExample example);

    int updateByExampleWithBLOBs(@Param("record") UcenterUserOauth record, @Param("example") UcenterUserOauthExample example);

    int updateByExample(@Param("record") UcenterUserOauth record, @Param("example") UcenterUserOauthExample example);

    int updateByPrimaryKeySelective(UcenterUserOauth record);

    int updateByPrimaryKeyWithBLOBs(UcenterUserOauth record);

    int updateByPrimaryKey(UcenterUserOauth record);
}