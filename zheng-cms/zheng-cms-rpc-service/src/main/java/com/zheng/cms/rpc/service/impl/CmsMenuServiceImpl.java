package com.zheng.cms.rpc.service.impl;

import com.zheng.cms.dao.mapper.CmsMenuMapper;
import com.zheng.cms.dao.model.CmsMenu;
import com.zheng.cms.dao.model.CmsMenuExample;
import com.zheng.cms.rpc.api.CmsMenuService;
import com.zheng.common.annotation.BaseService;
import com.zheng.common.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 菜单service实现
 * Created by shuzheng on 2017/3/18.
 */
@Service
@Transactional
@BaseService
public class CmsMenuServiceImpl extends BaseServiceImpl<CmsMenuMapper, CmsMenu, CmsMenuExample> implements CmsMenuService {

    private static Logger _log = LoggerFactory.getLogger(CmsMenuServiceImpl.class);

}