package com.zheng.upms.rpc.api;

import com.zheng.upms.dao.mapper.UpmsSystemMapper;

/**
 * 系统service接口
 * Created by shuzheng on 2016/12/18.
 */
public interface UpmsSystemService extends BaseService<UpmsSystemMapper> {

    // 批量删除
    int deleteByPrimaryKeys(String ids);

}