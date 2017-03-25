package com.zheng.cms.rpc.api;

import com.zheng.common.base.BaseServiceMock;
import com.zheng.cms.dao.mapper.CmsBookMapper;
import com.zheng.cms.dao.model.CmsBook;
import com.zheng.cms.dao.model.CmsBookExample;

/**
* 降级实现CmsBookService接口
* Created by shuzheng on 2017/3/20.
*/
public class CmsBookServiceMock extends BaseServiceMock<CmsBookMapper, CmsBook, CmsBookExample> implements CmsBookService {

}
