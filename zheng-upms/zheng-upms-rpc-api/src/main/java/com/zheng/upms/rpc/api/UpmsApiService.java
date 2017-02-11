package com.zheng.upms.rpc.api;

import com.zheng.upms.dao.model.UpmsPermission;

import java.util.List;

/**
 * upms系统接口
 * Created by shuzheng on 2017/2/11.
 */
public interface UpmsApiService {

    /**
     * 根据用户id获取所拥有的权限
     * @param upmsUserId
     * @return
     */
    List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId);

}
