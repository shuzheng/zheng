package com.zheng.upms.rpc.service.impl;

import com.zheng.common.annotation.BaseService;
import com.zheng.common.base.BaseServiceImpl;
import com.zheng.upms.dao.mapper.UpmsUserPermissionMapper;
import com.zheng.upms.dao.model.UpmsUserPermission;
import com.zheng.upms.dao.model.UpmsUserPermissionExample;
import com.zheng.upms.rpc.api.UpmsUserPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsUserPermissionService实现
* Created by shuzheng on 2017/3/20.
*/
@Service
@Transactional
@BaseService
public class UpmsUserPermissionServiceImpl extends BaseServiceImpl<UpmsUserPermissionMapper, UpmsUserPermission, UpmsUserPermissionExample> implements UpmsUserPermissionService {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserPermissionServiceImpl.class);

    @Autowired
    UpmsUserPermissionMapper upmsUserPermissionMapper;

}