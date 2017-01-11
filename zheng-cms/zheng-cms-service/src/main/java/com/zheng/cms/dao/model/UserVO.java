package com.zheng.cms.dao.model;

import java.util.List;

/**
 * 用户VO
 * @author shuzheng
 * @date 2016年7月6日 下午6:06:36
 */
public class UserVO extends CmsUser {

	private List<CmsBook> books;

	public List<CmsBook> getBooks() {
		return books;
	}

	public void setBooks(List<CmsBook> books) {
		this.books = books;
	}
}
