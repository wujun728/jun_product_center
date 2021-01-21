package org.myframework.maven.plugin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.myframework.codeutil.CodeGenerator;
import org.myframework.codeutil.CodeTool;
import org.myframework.codeutil.Column;
import org.myframework.util.JdbcUtils;
import org.myframework.util.LogUtils;

/**
 * 根据模板生成代码
 */
@Mojo(name = "showmecode"/* , defaultPhase = LifecyclePhase.PROCESS_SOURCES */)
public class CodeMojo extends AbstractMojo {
	/**
	* Logger for this class
	*/
	private static final Logger logger = LoggerFactory.getLogger(CodeMojo.class);
	 
	/**
	 * JDBC链接配置
	 */
	@Parameter(defaultValue = "${basedir}/codescript/jdbc.properties",   required = true)
	private File jdbcConfigFile;
	
	/**
	 * 待生成文件列表的配置文件
	 */
	@Parameter(defaultValue = "${basedir}/codescript/templates.cfg",   required = true)
	private File templatesCfgFile;
	
	
	/**
	 * VELOCITY模版文件所在目录
	 */
	@Parameter(defaultValue = "${basedir}/codescript/templates",  required = false)
	private File tmplDir  ;
	
	/**
	 * 配置代码生成目录
	 */
	@Parameter(defaultValue = "${project.build.sourceDirectory}",  required = false)
	private String javaSource  ;

	@Parameter(defaultValue = "${basedir}/src/main/webapp",  required = false)
	private String webapp  ;

	@Parameter(defaultValue = "${project.build.sourceDirectory}",  required = false)
	private String resources  ;
	
	public void execute() throws MojoExecutionException {
		CodeGenerator code = new CodeGenerator();

		// 配置项一：JDBC链接的配置文件
		Properties properties = new Properties();
		try {
			logger.info("JDBC配置文件>>>>>"+jdbcConfigFile.getAbsolutePath());
			properties.load(new FileInputStream(jdbcConfigFile));
		} catch (FileNotFoundException e) {
			System.err.println(jdbcConfigFile.getAbsolutePath() +"不存在!");
			return ;
		} catch (IOException e) {
			e.printStackTrace();
		} 
		LogUtils.print(properties);
		LogUtils.print(javaSource);
		LogUtils.print(resources);
		LogUtils.print(webapp);

		// 配置项二：代码模版目录
		if(!tmplDir.exists()) {
			logger.info(tmplDir.getAbsolutePath() +"目录不存在,使用插件默认模板目录");
			code.setTmplDir(CodeTool.RESOURCE_PATH_PREFIX + "templates");
		}else {
			logger.info("代码模板目录>>>>>"+tmplDir.getAbsolutePath());
			code.setTmplDir(tmplDir.getAbsolutePath());
		}

		// 代码内容--
		String tableName = properties.getProperty("tableName");
		code.setTablePrefix(properties.getProperty("tablePrefix"));
		code.setPkgPrefix(properties.getProperty("pkgPrefix"));
		code.setModule(properties.getProperty("module"));
		code.setSubModule(properties.getProperty("subModule"));
		code.setTableName(tableName.toUpperCase());
		
		// 代码存放目录
		code.setJavaSource(javaSource);
		code.setResources(resources);
		code.setWebapp(webapp);

		
		// 1 数据库读取配置信息
		Connection conn;
		try {
			conn = JdbcUtils.getConnection(properties);
			List<Column> lsColumns = JdbcUtils.getLsColumns(conn,
					tableName.toUpperCase());
			code.initCodeTool(lsColumns);

			// 配置项三：配置生成文件的列表 templates.cfg配置文件
			// 2 读取待生成文件列表
			logger.info("templates.cfg配置文件>>>>>"+templatesCfgFile.getAbsolutePath());
			List<String> lsList ;
			if(!templatesCfgFile.exists())
				lsList=code.getGenFileList(null);
			else
				lsList=code.getGenFileList(templatesCfgFile);
			//3.生成代码
			code.createCodeByConf(lsList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
