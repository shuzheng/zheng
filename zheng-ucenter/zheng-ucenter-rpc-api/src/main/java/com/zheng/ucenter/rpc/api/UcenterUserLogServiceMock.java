package com.zheng.ucenter.rpc.api;

import com.zheng.common.base.BaseServiceMock;
import com.zheng.ucenter.dao.mapper.UcenterUserLogMapper;
import com.zheng.ucenter.dao.model.UcenterUserLog;
import com.zheng.ucenter.dao.model.UcenterUserLogExample;

/**
* 降级实现UcenterUserLogService接口
* Created by shuzheng on 2017/4/27.
*/
public class UcenterUserLogServiceMock extends BaseServiceMock<UcenterUserLogMapper, UcenterUserLog, UcenterUserLogExample> implements UcenterUserLogService {

}
