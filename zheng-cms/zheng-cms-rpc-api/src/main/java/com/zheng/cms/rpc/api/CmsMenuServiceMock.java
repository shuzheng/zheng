package com.zheng.cms.rpc.api;

import com.zheng.cms.dao.mapper.CmsMenuMapper;
import com.zheng.cms.dao.model.CmsMenu;
import com.zheng.cms.dao.model.CmsMenuExample;
import com.zheng.common.base.BaseServiceMock;

/**
 * 降级实现CmsMenuService接口
 * Created by shuzheng on 2017/3/18.
 */
public class CmsMenuServiceMock extends BaseServiceMock<CmsMenuMapper, CmsMenu, CmsMenuExample> implements CmsMenuService {

}
