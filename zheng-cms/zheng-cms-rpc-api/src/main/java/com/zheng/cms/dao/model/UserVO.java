package com.zheng.cms.dao.model;

import java.io.Serializable;
import java.util.List;

/**
 * 用户VO
 * Created by shuzheng on 2016/11/14.
 */
public class UserVO extends CmsUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<CmsBook> books;

	public List<CmsBook> getBooks() {
		return books;
	}

	public void setBooks(List<CmsBook> books) {
		this.books = books;
	}
}
