package com.zheng.cms.rpc.api;

import com.zheng.cms.dao.mapper.CmsTopicMapper;
import com.zheng.cms.dao.model.CmsTopic;
import com.zheng.cms.dao.model.CmsTopicExample;
import com.zheng.common.base.BaseServiceMock;

/**
 * 降级实现CmsTopicService接口
 * Created by shuzheng on 2017/3/18.
 */
public class CmsTopicServiceMock extends BaseServiceMock<CmsTopicMapper, CmsTopic, CmsTopicExample> implements CmsTopicService {

}
