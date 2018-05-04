package com.zheng.api.rpc.service.impl;

import com.zheng.api.rpc.api.ApiService;
import com.zheng.cms.rpc.api.*;
import com.zheng.upms.rpc.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 实现ApiService接口 Created by shuzheng on 2017/2/19.
 */
public class ApiServiceImpl implements ApiService {

	@SuppressWarnings("unused")
	private static Logger _log = LoggerFactory.getLogger(ApiServiceImpl.class);

	@SuppressWarnings("unused")
	@Autowired
	private UpmsSystemService upmsSystemService;

	@SuppressWarnings("unused")
	@Autowired
	private UpmsOrganizationService upmsOrganizationService;
	@SuppressWarnings("unused")
	@Autowired
	private UpmsUserService upmsUserService;
	@SuppressWarnings("unused")
	@Autowired
	private UpmsRoleService upmsRoleService;
	@SuppressWarnings("unused")
	@Autowired
	private UpmsPermissionService upmsPermissionService;
	@SuppressWarnings("unused")
	@Autowired
	private UpmsApiService upmsApiService;
	@SuppressWarnings("unused")
	@Autowired
	private CmsArticleService cmsArticleService;
	@SuppressWarnings("unused")
	@Autowired
	private CmsCategoryService cmsCategoryService;
	@SuppressWarnings("unused")
	@Autowired
	private CmsCommentService cmsCommentService;
	@SuppressWarnings("unused")
	@Autowired
	private CmsTagService cmsTagService;

	@Override
	public String hello(String name) {
		return "hello," + name + "!";
	}

}
