package com.zheng.common.util;

import org.apache.commons.lang.ObjectUtils;
import org.apache.velocity.VelocityContext;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成类
 * Created by ZhangShuzheng on 2017/1/10.
 */
public class MybatisGeneratorConfigUtil {

	// 模板路径
	private static String VM_PATH = "zheng-common/src/main/resources/generatorConfig.vm";
	// 项目名称
	private static String PROJECT_NAME = "zheng";
	// 数据库名称
	private static String DATABASE_NAME = "zheng";

	/**
	 * 根据模板生成generatorConfig.xml文件
	 * @param module_prefix_name
	 */
	public static void generator(
			String jdbc_driver,
			String jdbc_url,
			String jdbc_username,
			String jdbc_password,
			String module_prefix_name) {
		String module_path = PROJECT_NAME + "-" + module_prefix_name.replaceAll("\\.", "-") + "/" + PROJECT_NAME + "-" + module_prefix_name.replaceAll("\\.", "-") + "-dao/src/main/resources/generatorConfig.xml";
		String sql = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + DATABASE_NAME + "' AND table_name LIKE '" + module_prefix_name.replaceAll("\\.", "_") + "_%';";
		System.out.println("========== 开始生成generatorConfig.xml文件 ==========");
		try {
			VelocityContext context= new VelocityContext();
			List<Map<String, Object>> tables = new ArrayList<>();
			Map<String, Object> table = null;

			// 查询定制前缀项目的所有表
			JdbcUtil jdbcUtil = new JdbcUtil(jdbc_driver, jdbc_url, jdbc_username, jdbc_password);
			List<Map> result = jdbcUtil.selectByParams(sql, null);
			for (Map map : result) {
				System.out.println(map.get("TABLE_NAME"));
				table = new HashMap<>();
				table.put("table_name", map.get("TABLE_NAME"));
				table.put("model_name", StringUtil.lineToHump(ObjectUtils.toString(map.get("TABLE_NAME"))));
				tables.add(table);
			}
			jdbcUtil.release();

			String targetProject = PROJECT_NAME + "-" + module_prefix_name.replaceAll("\\.", "-") + "/" + PROJECT_NAME + "-" + module_prefix_name.replaceAll("\\.", "-") + "-dao";
			context.put("tables", tables);
			context.put("generator_javaModelGenerator_targetPackage", "com." + PROJECT_NAME + "." + module_prefix_name + ".dao.model");
			context.put("generator_sqlMapGenerator_targetPackage", "com." + PROJECT_NAME + "." + module_prefix_name + ".dao.mapper");
			context.put("generator_javaClientGenerator_targetPackage", "com." + PROJECT_NAME + "." + module_prefix_name + ".dao.mapper");
			context.put("targetProject", targetProject);
			VelocityUtil.generate(VM_PATH, module_path, context);
			// 删除旧代码
			deleteDir(new File(targetProject + "/src/main/java/com/" + PROJECT_NAME + "/" + module_prefix_name.replaceAll("\\.", "/") + "/dao/model"));
			deleteDir(new File(targetProject + "/src/main/java/com/" + PROJECT_NAME + "/" + module_prefix_name.replaceAll("\\.", "/") + "/dao/mapper"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("========== 结束生成generatorConfig.xml文件 ==========");
		System.out.println("========== 开始运行MybatisGenerator ==========");
		// 生成代码
		try {
			List<String> warnings = new ArrayList<>();
			File configFile = new File(MybatisGeneratorConfigUtil.class.getResource("/generatorConfig.xml").getFile());
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration config = cp.parseConfiguration(configFile);
			DefaultShellCallback callback = new DefaultShellCallback(true);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);
			for (String warning : warnings) {
				System.out.println(warning);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLParserException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
		System.out.println("========== 结束运行MybatisGenerator ==========");
	}

	// 递归删除非空文件夹
	public static void deleteDir(File dir){
		if(dir.isDirectory()){
			File[] files = dir.listFiles();
			for(int i=0; i<files.length; i++) {
				deleteDir(files[i]);
			}
		}
		dir.delete();
	}

}
