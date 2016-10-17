package com.zheng.cms.dao.model;

import java.awt.print.Book;
import java.util.List;

/**
 * 用户VO
 * @author shuzheng
 * @date 2016年7月6日 下午6:06:36
 */
public class UserVO extends User {

	private List<Book> books;

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
