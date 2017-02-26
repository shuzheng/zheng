package com.zheng.upms.rpc.service.impl;

import com.zheng.common.annotation.BaseService;
import com.zheng.common.base.BaseServiceImpl;
import com.zheng.upms.dao.mapper.UpmsPermissionMapper;
import com.zheng.upms.dao.mapper.UpmsRolePermissionMapper;
import com.zheng.upms.dao.model.UpmsPermission;
import com.zheng.upms.dao.model.UpmsPermissionExample;
import com.zheng.upms.dao.model.UpmsRolePermission;
import com.zheng.upms.dao.model.UpmsRolePermissionExample;
import com.zheng.upms.rpc.api.UpmsPermissionService;
import com.zheng.upms.rpc.api.UpmsRolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色权限service实现
 * Created by shuzheng on 2017/2/26.
 */
@Service
@Transactional
@BaseService
public class UpmsRolePermissionServiceImpl extends BaseServiceImpl<UpmsRolePermissionMapper, UpmsRolePermission, UpmsRolePermissionExample> implements UpmsRolePermissionService {

    private static Logger _log = LoggerFactory.getLogger(UpmsRolePermissionServiceImpl.class);

    @Autowired
    UpmsRolePermissionMapper upmsRolePermissionMapper;

}