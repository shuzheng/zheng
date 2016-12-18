package com.zheng.upms.service;

/**
 * baseService接口
 * @author shuzheng
 * @date 2016年7月7日 上午9:58:23
 */
public interface BaseService<Mapper> {

	/**
	 * 获取基本操作mapper
	 * @return
	 */
	Mapper getMapper();

}