package com.zheng.cms.service.impl;

import com.zheng.cms.dao.mapper.CmsTagMapper;
import com.zheng.cms.service.CmsTagService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 标签service实现
 * Created by shuzheng on 2016/11/14.
 */
@Service
@Transactional
public class CmsTagServiceImpl implements CmsTagService {

	private static Logger _log = LoggerFactory.getLogger(CmsTagServiceImpl.class);

	@Autowired
	private CmsTagMapper cmsTagMapper;

	@Override
	public CmsTagMapper getMapper() {
		return cmsTagMapper;
	}

	// 批量删除
	@Override
	public int deleteByPrimaryKeys(String ids) {
		if (StringUtils.isBlank(ids)) {
			return 0;
		}
		String[] idArray = ids.split("-");
		int count = 0;
		for (String id : idArray) {
			if (StringUtils.isBlank(id)) {
				continue;
			}
			try {
				count += cmsTagMapper.deleteByPrimaryKey(Integer.parseInt(id));
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
		return count;
	}
}