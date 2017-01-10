package com.zheng.cms.dao;

import com.zheng.common.util.*;
import org.apache.commons.lang.ObjectUtils;
import org.apache.velocity.VelocityContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成类
 * Created by ZhangShuzheng on 2017/1/10.
 */
public class Generator {

	// 根据命名规范，只修改此常量值即可
	private static String MODULE_PREFIX_NAME = "cms";
	private static String JDBC_DRIVER = PropertiesFileUtil.getInstance("jdbc").get("jdbc.driver");
	private static String JDBC_URL = PropertiesFileUtil.getInstance("jdbc").get("jdbc.url");
	private static String JDBC_USERNAME= PropertiesFileUtil.getInstance("jdbc").get("jdbc.username");
	private static String JDBC_PASSWORD = PropertiesFileUtil.getInstance("jdbc").get("jdbc.password");

	/**
	 * 根据模板生成generatorConfig.xml文件
	 * @param args
	 */
	public static void main(String[] args) {
		MybatisGeneratorConfigUtil.generator(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, MODULE_PREFIX_NAME);
	}

}
