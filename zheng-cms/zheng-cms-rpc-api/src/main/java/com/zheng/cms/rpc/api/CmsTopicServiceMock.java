package com.zheng.cms.rpc.api;

import com.zheng.common.base.BaseServiceMock;
import com.zheng.cms.dao.mapper.CmsTopicMapper;
import com.zheng.cms.dao.model.CmsTopic;
import com.zheng.cms.dao.model.CmsTopicExample;

/**
* 降级实现CmsTopicService接口
* Created by shuzheng on 2017/4/5.
*/
public class CmsTopicServiceMock extends BaseServiceMock<CmsTopicMapper, CmsTopic, CmsTopicExample> implements CmsTopicService {

}
