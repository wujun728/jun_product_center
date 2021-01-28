package org.myframework.codeutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.myframework.util.DateUtil;
import org.myframework.util.StringUtil;

public class CodeGenerator {

	private final Log logger = LogFactory.getLog(getClass());

	CodeTool tool = new CodeTool ();

	private String pkgPrefix = "com.linkage";

	private String module = "";

	private String subModule = "region";

	private String tablePrefix = "";

	private String tableName = "GROUP_REGION_AUTO_CLAIM";

	private String javaSource = "D://CODEGEN//java//";

	private String webapp = "D://CODEGEN//webapp//";

	private String resources = "D://CODEGEN//resources//";

	private String classVar;

	private String className;

	private String keyVar;

	private String keyType;

	private String jspDir;

	private String packageName;

	private List<Column> getPkColumns(List<Column> lsColumns) {
		List<Column> lsColumnTemp = new ArrayList<Column>(lsColumns.size());
		List<Column> pkColumns = new ArrayList<Column>(2);
		for (Column column : lsColumns) {
			if ("TRUE".equalsIgnoreCase(column.getColumnKey())) {
				pkColumns.add(column);
			} else {
				lsColumnTemp.add(column);
			}
		}
		tool.put("columnResult", lsColumnTemp);
		tool.put("pkResult", pkColumns);
		return pkColumns ;
	}

	public void initCodeTool(List<Column> lsColumns) throws Exception {
		classVar = StringUtil.toBeanPatternStr(tableName.substring(tablePrefix
				.length()));
		className = StringUtil.firstCharUpperCase(classVar);
		pkgPrefix = pkgPrefix
				+ (StringUtil.isEmpty(module) ? "" : ("." + module))
				+ (StringUtil.isEmpty(subModule) ? "" : ("." + subModule));
		jspDir = (StringUtil.isEmpty(module) ? "" : ("/" + module))
				+ (StringUtil.isEmpty(subModule) ? "" : ("/" + subModule));
		tool.put("tableName", tableName);
		tool.put("className", className);
		tool.put("module", module);
		tool.put("subModule", subModule);
		tool.put("classVar", classVar);
		tool.put("packagePrefix", pkgPrefix);
		tool.put("packageName", packageName);
		tool.put("jspDir", jspDir);
		// 把主键和非主键分别放入tool
		List<Column> pkResult = getPkColumns(lsColumns);
		pkResult = pkResult.size() != 0 ?pkResult :lsColumns;
		int pkCnt = pkResult.size();
		if (pkCnt > 1  ) {
			logger.debug("创建复合主键类;主键字段>>>>>>" + pkResult);
			keyType = "PK";
			keyVar = "pk";
			tool.put("keyType", keyType);
			tool.put("keyVar", keyVar);
		} else  {
			Column column = pkResult.get(0);
			logger.debug("主键字段>>>>>>" + column);
			keyType = column.getDataType();
			keyVar = column.getJavaName();
			tool.put("keyType", keyType);
			tool.put("keyVar", keyVar);
		}

		tool.put("needUpdate", true);
		tool.put("date", DateUtil.getCurrentDay());
		tool.put("author", System.getProperty("user.name"));
	}
	
	public void initCodeTool(Properties properties) throws Exception {
		for (Object key : properties.keySet()) {
			tool.put(key.toString(), properties.get(key));
		}
	}

	public void createCodeByConf(List<String> genFiles) throws Exception {
		// step 1
		tool.initVelocity();
		// step 2
		tool.put("javaSource",javaSource);
		tool.put("webapp",webapp);
		tool.put("resources",resources);
		// step 3
//		List<String> genFiles  = getGenFileList();
		for (String file : genFiles) {
			logger.info("=======❤❤❤❤❤❤❤❤=== CodeGenerator START WORK!!!===========" );
			String[] props = file.split(";");
			if(props.length<3){
				logger.error(file+" >>配置不正确，配置格式应该是<文件描述>;<文件路径>;<模板文件名>");
				continue;
			}
			logger.info("生成源码配置信息 ：" + file);
			String filePath = tool.getText(props[1].trim());
			logger.info("生成源码 ：" + filePath);
			String vmFile = props[2].trim();
			tool.setAbsolutePath(filePath);
			tool.setTmplFile(vmFile);
			tool.createFileByTmpl();
			logger.info("=======❤❤❤❤❤❤❤❤=== CodeGenerator END WORK!!!===========" );
		}
	}

	/**
	 * 获取待生成源码列表
	 * @return
	 * @throws Exception
	 */
	public List<String> getGenFileList(File file) throws Exception {
		List<String> genFiles  = new  ArrayList<String>();
		Reader fr = null;
		if(file==null || !file.exists()) {
			URL fileUrl = this.getClass().getResource("/templates.cfg");
			fr = new InputStreamReader(fileUrl.openStream());
		}else {
			fr = new FileReader(file);
		}
		 
		BufferedReader br = new BufferedReader(fr);
		while (br.ready()) {
			String line = br.readLine();
			if(!line.startsWith("#")&&!"".equals(line.trim()))
				genFiles.add(line);
		}
		br.close();
		fr.close();
		return genFiles;
	}

	public String getPkgPrefix() {
		return pkgPrefix;
	}

	public void setPkgPrefix(String pkgPrefix) {
		this.pkgPrefix = pkgPrefix;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getSubModule() {
		return subModule;
	}

	public void setSubModule(String subModule) {
		this.subModule = subModule;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setJavaSource(String javaSource) {
		this.javaSource = javaSource;
	}

	public void setWebapp(String webapp) {
		this.webapp = webapp;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	@Override
	public String toString() {
		return "CodeGenerator [pkgPrefix=" + pkgPrefix + ", module=" + module
				+ ", subModule=" + subModule + ", tableName=" + tableName
				+ ", javaSource=" + javaSource + ", webapp=" + webapp
				+ ", resources=" + resources + ", classVar=" + classVar
				+ ", className=" + className + ", keyVar=" + keyVar
				+ ", keyType=" + keyType + ", jspDir=" + jspDir
				+ ", packageName=" + packageName + "]";
	}

	/**
	 * 模版文件存储目录
	 * @param tmplDir
	 */
	public void setTmplDir(String tmplDir) {
		tool.setTmplDir(tmplDir);
	}

}
