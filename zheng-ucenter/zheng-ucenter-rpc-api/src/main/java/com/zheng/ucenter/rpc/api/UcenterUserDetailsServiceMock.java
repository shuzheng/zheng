package com.zheng.ucenter.rpc.api;

import com.zheng.common.base.BaseServiceMock;
import com.zheng.ucenter.dao.mapper.UcenterUserDetailsMapper;
import com.zheng.ucenter.dao.model.UcenterUserDetails;
import com.zheng.ucenter.dao.model.UcenterUserDetailsExample;

/**
* 降级实现UcenterUserDetailsService接口
* Created by shuzheng on 2017/4/27.
*/
public class UcenterUserDetailsServiceMock extends BaseServiceMock<UcenterUserDetailsMapper, UcenterUserDetails, UcenterUserDetailsExample> implements UcenterUserDetailsService {

}
