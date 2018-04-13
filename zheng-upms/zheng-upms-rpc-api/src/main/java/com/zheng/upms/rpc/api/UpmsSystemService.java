package com.zheng.upms.rpc.api;

import com.zheng.common.base.BaseService;
import com.zheng.upms.dao.model.UpmsSystem;
import com.zheng.upms.dao.model.UpmsSystemExample;

/**
* UpmsSystemService接口
* Created by shuzheng on 2017/3/20.
*/
public interface UpmsSystemService extends BaseService<UpmsSystem, UpmsSystemExample> {

    /**
     * 根据name获取UpmsSystem
     * @param name
     * @return
     */
    UpmsSystem selectUpmsSystemByName(String name);

}