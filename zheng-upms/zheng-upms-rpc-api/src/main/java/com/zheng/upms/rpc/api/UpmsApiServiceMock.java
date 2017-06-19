package com.zheng.upms.rpc.api;

import com.zheng.upms.dao.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 降级实现UpmsApiService接口
 * Created by shuzheng on 2017/2/14.
 */
public class UpmsApiServiceMock implements UpmsApiService {

    private static Logger _log = LoggerFactory.getLogger(UpmsApiServiceMock.class);

    @Override
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId) {
        _log.info("UpmsApiServiceMock => selectUpmsPermissionByUpmsUserId");
        return null;
    }

    @Override
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserIdByCache(Integer upmsUserId) {
        _log.info("UpmsApiServiceMock => selectUpmsPermissionByUpmsUserIdByCache");
        return null;
    }

    @Override
    public List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId) {
        _log.info("UpmsApiServiceMock => selectUpmsRoleByUpmsUserId");
        return null;
    }

    @Override
    public List<UpmsRole> selectUpmsRoleByUpmsUserIdByCache(Integer upmsUserId) {
        _log.info("UpmsApiServiceMock => selectUpmsRoleByUpmsUserIdByCache");
        return null;
    }

    @Override
    public List<UpmsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(Integer upmsRoleId) {
        _log.info("UpmsApiServiceMock => selectUpmsRolePermisstionByUpmsRoleId");
        return null;
    }

    @Override
    public List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(Integer upmsUserId) {
        _log.info("UpmsApiServiceMock => selectUpmsUserPermissionByUpmsUserId");
        return null;
    }

    @Override
    public List<UpmsSystem> selectUpmsSystemByExample(UpmsSystemExample upmsSystemExample) {
        _log.info("UpmsApiServiceMock => selectUpmsSystemByExample");
        return null;
    }

    @Override
    public List<UpmsOrganization> selectUpmsOrganizationByExample(UpmsOrganizationExample upmsOrganizationExample) {
        _log.info("UpmsApiServiceMock => selectUpmsOrganizationByExample");
        return null;
    }

    @Override
    public UpmsUser selectUpmsUserByUsername(String username) {
        _log.info("UpmsApiServiceMock => selectUpmsUserByUsername");
        return null;
    }

    @Override
    public int insertUpmsLogSelective(UpmsLog record) {
        _log.info("UpmsApiServiceMock => insertSelective");
        return 0;
    }

}
