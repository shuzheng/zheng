package com.zheng.upms.server.realm;

import com.zheng.common.util.MD5Util;
import com.zheng.upms.dao.model.UpmsPermission;
import com.zheng.upms.dao.model.UpmsUser;
import com.zheng.upms.dao.model.UpmsUserExample;
import com.zheng.upms.rpc.api.UpmsApiService;
import com.zheng.upms.rpc.api.UpmsUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by shuzheng on 2017/1/20.
 */
public class UpmsRealm extends AuthorizingRealm {

    private static Logger _log = LoggerFactory.getLogger(UpmsRealm.class);

    @Autowired
    private UpmsUserService upmsUserService;

    @Autowired
    private UpmsApiService upmsApiService;

    /**
     * 授权：验证权限时调用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 当前用户
        UpmsUser upmsUser = (UpmsUser) principalCollection.getPrimaryPrincipal();

        // 当前用户所有权限
        List<UpmsPermission> upmsPermissions = upmsApiService.selectUpmsPermissionByUpmsUserId(upmsUser.getUserId());
        Set<String> permissions = new HashSet<>();
        for (UpmsPermission upmsPermission : upmsPermissions) {
            if (StringUtils.isNotEmpty(upmsPermission.getPermissionValue())) {
                permissions.add(upmsPermission.getPermissionValue());
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证：登录时调用
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());

        // 查询用户信息
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        upmsUserExample.createCriteria()
            .andUsernameEqualTo(username);
        UpmsUser upmsUser = upmsUserService.selectFirstByExample(upmsUserExample);

        if (null == upmsUser) {
            throw new UnknownAccountException("帐号不存在！");
        }
        if (!upmsUser.getPassword().equals(MD5Util.MD5(password + upmsUser.getSalt()))) {
            throw new IncorrectCredentialsException("密码错误！");
        }
        if (upmsUser.getLocked() == 1) {
            throw new LockedAccountException("账号已被锁定！");
        }

        return new SimpleAuthenticationInfo(upmsUser, password, getName());
    }

}
