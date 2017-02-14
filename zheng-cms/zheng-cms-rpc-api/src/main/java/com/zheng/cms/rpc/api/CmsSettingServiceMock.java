package com.zheng.cms.rpc.api;

import com.zheng.cms.dao.mapper.CmsSettingMapper;
import com.zheng.cms.dao.model.CmsSetting;
import com.zheng.cms.dao.model.CmsSettingExample;
import com.zheng.common.base.BaseServiceMock;

/**
 * 降级实现CmsSettingService接口
 * Created by shuzheng on 2017/2/14.
 */
public class CmsSettingServiceMock extends BaseServiceMock<CmsSettingMapper, CmsSetting, CmsSettingExample> implements CmsSettingService {

}
