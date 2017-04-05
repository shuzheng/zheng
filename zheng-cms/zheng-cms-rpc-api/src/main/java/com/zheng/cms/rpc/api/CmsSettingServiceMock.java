package com.zheng.cms.rpc.api;

import com.zheng.common.base.BaseServiceMock;
import com.zheng.cms.dao.mapper.CmsSettingMapper;
import com.zheng.cms.dao.model.CmsSetting;
import com.zheng.cms.dao.model.CmsSettingExample;

/**
* 降级实现CmsSettingService接口
* Created by shuzheng on 2017/4/5.
*/
public class CmsSettingServiceMock extends BaseServiceMock<CmsSettingMapper, CmsSetting, CmsSettingExample> implements CmsSettingService {

}
