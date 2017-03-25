package com.zheng.cms.dao.mapper;

import com.zheng.cms.dao.model.CmsSetting;
import com.zheng.cms.dao.model.CmsSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsSettingMapper {
    long countByExample(CmsSettingExample example);

    int deleteByExample(CmsSettingExample example);

    int deleteByPrimaryKey(Integer settingId);

    int insert(CmsSetting record);

    int insertSelective(CmsSetting record);

    List<CmsSetting> selectByExample(CmsSettingExample example);

    CmsSetting selectByPrimaryKey(Integer settingId);

    int updateByExampleSelective(@Param("record") CmsSetting record, @Param("example") CmsSettingExample example);

    int updateByExample(@Param("record") CmsSetting record, @Param("example") CmsSettingExample example);

    int updateByPrimaryKeySelective(CmsSetting record);

    int updateByPrimaryKey(CmsSetting record);
}