package com.zheng.ucenter.dao.mapper;

import com.zheng.ucenter.dao.model.UcenterOauth;
import com.zheng.ucenter.dao.model.UcenterOauthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UcenterOauthMapper {
    long countByExample(UcenterOauthExample example);

    int deleteByExample(UcenterOauthExample example);

    int deleteByPrimaryKey(Integer oauthId);

    int insert(UcenterOauth record);

    int insertSelective(UcenterOauth record);

    List<UcenterOauth> selectByExample(UcenterOauthExample example);

    UcenterOauth selectByPrimaryKey(Integer oauthId);

    int updateByExampleSelective(@Param("record") UcenterOauth record, @Param("example") UcenterOauthExample example);

    int updateByExample(@Param("record") UcenterOauth record, @Param("example") UcenterOauthExample example);

    int updateByPrimaryKeySelective(UcenterOauth record);

    int updateByPrimaryKey(UcenterOauth record);
}