package org.myframework;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.texen.util.PropertiesUtil;
import org.myframework.codeutil.CodeGenerator;
import org.myframework.codeutil.CodeTool;
import org.myframework.codeutil.Column;
import org.myframework.util.JdbcUtils;
import org.myframework.util.LogUtils;

public class JDBCCodeGenerator {
	static {
		System.setProperty("codegen.root", "d://");
	}
	private final static Log logger = LogFactory
			.getLog(JDBCCodeGenerator.class);

	/**
	 * 代码生成工具调用
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		CodeGenerator code = new CodeGenerator();

		// 配置项一：JDBC链接的配置文件
		Properties properties = new PropertiesUtil().load("jdbc.properties");
		LogUtils.print(properties);

		// 配置项二：代码模版目录
		code.setTmplDir(CodeTool.RESOURCE_PATH_PREFIX + "templates");

		// 代码内容--
		String tableName = properties.getProperty("tableName");
		code.setTablePrefix(properties.getProperty("tablePrefix"));
		code.setPkgPrefix(properties.getProperty("pkgPrefix"));
		code.setModule(properties.getProperty("module"));
		code.setSubModule(properties.getProperty("subModule"));
		code.setTableName(tableName.toUpperCase());
		// 代码存放目录
		code.setJavaSource(properties.getProperty("javaSource"));
		code.setResources(properties.getProperty("resources"));
		code.setWebapp(properties.getProperty("webapp"));

		// 1 数据库读取配置信息
		Connection conn = JdbcUtils.getConnection(properties);
		List<Column> lsColumns = JdbcUtils.getLsColumns(conn,
				tableName.toUpperCase());
		code.initCodeTool(lsColumns);

		// 配置项三：配置生成文件的列表 templates.cfg配置文件
		// 2 读取待生成文件列表，templates.cfg配置文件
		List<String> lsList = code.getGenFileList(null);
		code.createCodeByConf(lsList);

	}

}
