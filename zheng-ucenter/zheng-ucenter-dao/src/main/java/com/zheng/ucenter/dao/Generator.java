package com.zheng.ucenter.dao;

import com.zheng.common.util.MybatisGeneratorUtil;
import com.zheng.common.util.PropertiesFileUtil;

/**
 * 代码生成类
 * Created by ZhangShuzheng on 2017/1/10.
 */
public class Generator {

	// 根据命名规范，只修改此常量值即可
	private static String MODULE = "zheng-ucenter";
	private static String DATABASE = "zheng";
	private static String TABLE_PREFIX = "ucenter_";
	private static String PACKAGE_NAME = "com.zheng.ucenter";
	private static String JDBC_DRIVER = PropertiesFileUtil.getInstance("jdbc").get("master.jdbc.driver");
	private static String JDBC_URL = PropertiesFileUtil.getInstance("jdbc").get("master.jdbc.url");
	private static String JDBC_USERNAME = PropertiesFileUtil.getInstance("jdbc").get("master.jdbc.username");
	private static String JDBC_PASSWORD = PropertiesFileUtil.getInstance("jdbc").get("master.jdbc.password");

	/**
	 * 自动代码生成
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		MybatisGeneratorUtil.generator(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, MODULE, DATABASE, TABLE_PREFIX, PACKAGE_NAME);
	}

}
