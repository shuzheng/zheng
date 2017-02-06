package com.zheng.upms.rpc.service.impl;

import com.zheng.common.base.BaseServiceImpl;
import com.zheng.upms.dao.mapper.UpmsRoleMapper;
import com.zheng.upms.dao.model.UpmsRole;
import com.zheng.upms.dao.model.UpmsRoleExample;
import com.zheng.upms.rpc.api.UpmsRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色service实现
 * Created by shuzheng on 2017/2/6.
 */
@Service
@Transactional
public class UpmsRoleServiceImpl extends BaseServiceImpl<UpmsRoleMapper, UpmsRole, UpmsRoleExample> implements UpmsRoleService {

    private static Logger _log = LoggerFactory.getLogger(UpmsRoleServiceImpl.class);

    @Autowired
    UpmsRoleMapper upmsRoleMapper;

}