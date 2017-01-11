package com.zheng.upms.dao.mapper;

import com.zheng.upms.dao.model.UpmsSystem;
import com.zheng.upms.dao.model.UpmsSystemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsSystemMapper {
    long countByExample(UpmsSystemExample example);

    int deleteByExample(UpmsSystemExample example);

    int deleteByPrimaryKey(Integer systemId);

    int insert(UpmsSystem record);

    int insertSelective(UpmsSystem record);

    List<UpmsSystem> selectByExample(UpmsSystemExample example);

    UpmsSystem selectByPrimaryKey(Integer systemId);

    int updateByExampleSelective(@Param("record") UpmsSystem record, @Param("example") UpmsSystemExample example);

    int updateByExample(@Param("record") UpmsSystem record, @Param("example") UpmsSystemExample example);

    int updateByPrimaryKeySelective(UpmsSystem record);

    int updateByPrimaryKey(UpmsSystem record);
}