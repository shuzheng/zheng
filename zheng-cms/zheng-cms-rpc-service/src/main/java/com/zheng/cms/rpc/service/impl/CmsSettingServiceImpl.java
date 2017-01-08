package com.zheng.cms.rpc.service.impl;

import com.zheng.cms.dao.mapper.CmsSettingMapper;
import com.zheng.cms.dao.model.CmsSetting;
import com.zheng.cms.dao.model.CmsSettingExample;
import com.zheng.cms.rpc.api.CmsSettingService;
import com.zheng.common.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 网站设置service实现
 * Created by shuzheng on 2017/01/08.
 */
@Service
@Transactional
public class CmsSettingServiceImpl extends BaseServiceImpl<CmsSettingMapper, CmsSetting, CmsSettingExample> implements CmsSettingService {

	private static Logger _log = LoggerFactory.getLogger(CmsSettingServiceImpl.class);

}