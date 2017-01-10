package com.zheng.cms.dao;

import com.zheng.common.util.JdbcUtil;
import com.zheng.common.util.PropertiesFileUtil;
import com.zheng.common.util.VelocityUtil;
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

	private static String VM_PATH = "zheng-common/src/main/resources/generatorConfig.vm";
	private static String MODULE_PATH = "zheng-cms/zheng-" + MODULE_PREFIX_NAME + "-dao/src/main/resources/generatorConfig.xml";
	private static String SQL = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = 'zheng' AND table_name LIKE '" + MODULE_PREFIX_NAME + "_%';";
	private static String JDBC_DRIVER = PropertiesFileUtil.getInstance("jdbc").get("jdbc.driver");
	private static String JDBC_URL = PropertiesFileUtil.getInstance("jdbc").get("jdbc.url");
	private static String JDBC_USERNAME= PropertiesFileUtil.getInstance("jdbc").get("jdbc.username");
	private static String JDBC_PASSWORD = PropertiesFileUtil.getInstance("jdbc").get("jdbc.password");

	/**
	 * 根据模板生成generatorConfig.xml文件
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("========== 开始生成代码 ==========");
		try {
			VelocityContext context= new VelocityContext();
			List<Map<String, Object>> tables = new ArrayList<>();
			Map<String, Object> table = null;

			// 查询定制前缀项目的所有表
			JdbcUtil jdbcUtil = new JdbcUtil(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
			List<Map> result = jdbcUtil.selectByParams(SQL, null);
			for (Map map : result) {
				System.out.println(map.get("TABLE_NAME"));
				table = new HashMap<>();
				table.put("table_name", map.get("TABLE_NAME"));
				// TODO 转驼峰
				table.put("model_name", map.get("TABLE_NAME"));
				tables.add(table);
			}
			jdbcUtil.release();

			context.put("tables", tables);
			VelocityUtil.generate(VM_PATH, MODULE_PATH, context);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("========== 结束生成代码 ==========");
	}

}
