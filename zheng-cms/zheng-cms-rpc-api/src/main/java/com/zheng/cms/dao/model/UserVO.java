package com.zheng.cms.dao.model;

import java.io.Serializable;
import java.util.List;

/**
 * 用户VO
 * Created by shuzheng on 2016/11/14.
 */
public class UserVO extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Book> books;

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
