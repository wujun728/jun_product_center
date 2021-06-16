package com.wangzhixuan.generator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.toolkit.StringUtils;

/**
 * <p>
 * 代码生成器演示
 * </p>
 * 
 * @author hubin
 * @date 2016-12-01
 */
public class MysqlGenerator {

	/**
	 * <p>
	 * MySQL 生成演示
	 * </p>
	 */
	public static void main(String[] args) {
		/* 获取 JDBC 配置文件 */
		Properties props = getProperties();
		AutoGenerator mpg = new AutoGenerator();

		String outputDir = "/Users/lcm/Desktop/generator/code";
		final String viewOutputDir = outputDir + "/view/";
		
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir(outputDir);
		gc.setFileOverride(true);
		gc.setActiveRecord(true);// 开启 activeRecord 模式
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(false);// XML columList
		gc.setAuthor("zhixuan.wang");

		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		gc.setMapperName("%sMapper");
		gc.setXmlName("%sMapper");
		gc.setServiceName("I%sService");
		gc.setServiceImplName("%sServiceImpl");
		gc.setControllerName("%sController");
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setTypeConvert(new MySqlTypeConvert());
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername(props.getProperty("db.master.user"));
		dsc.setPassword(props.getProperty("db.master.password"));
		dsc.setUrl(props.getProperty("db.master.url"));
		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		// strategy.setCapitalMode(true);// 全局大写命名
		// strategy.setDbColumnUnderline(true);//全局下划线命名
//		strategy.setTablePrefix(new String[] { "bmd_", "mp_" });// 此处可以修改为您的表前缀
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		// strategy.setInclude(new String[] { "user" }); // 需要生成的表
		// strategy.setExclude(new String[]{"test"}); // 排除生成的表
		// 自定义实体父类
		// strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
		// 自定义实体，公共字段
		// strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
		// 自定义 mapper 父类
		// strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
		// 自定义 service 父类
		// strategy.setSuperServiceClass("com.baomidou.demo.TestService");
		// 自定义 service 实现类父类
		// strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
		// 自定义 controller 父类
		strategy.setSuperControllerClass("com.wangzhixuan.commons.base.BaseController");
		// 【实体】是否生成字段常量（默认 false）
		// public static final String ID = "test_id";
		// strategy.setEntityColumnConstant(true);
		// 【实体】是否为构建者模型（默认 false）
		// public User setName(String name) {this.name = name; return this;}
		// strategy.setEntityBuliderModel(true);
		mpg.setStrategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setModuleName(null);  //所属模块
		pc.setParent("com.wangzhixuan"); // 自定义包路径
		pc.setController("controller"); // 这里是控制器包名，默认 web
		pc.setEntity("model");
		pc.setXml("sqlMapperXml");
		mpg.setPackageInfo(pc);

		// 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {}
		};
		// 生成的模版路径，不存在时需要先新建
		File viewDir = new File(viewOutputDir);
		if (!viewDir.exists()) {
			viewDir.mkdirs();
		}
		List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
		focList.add(new FileOutConfig("/templates/add.jsp.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return getGeneratorViewPath(viewOutputDir, tableInfo, "Add.jsp");
			}
		});
		focList.add(new FileOutConfig("/templates/edit.jsp.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return getGeneratorViewPath(viewOutputDir, tableInfo, "Edit.jsp");
			}
		});
		focList.add(new FileOutConfig("/templates/list.jsp.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return getGeneratorViewPath(viewOutputDir, tableInfo, "List.jsp");
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);

		// 执行生成
		mpg.execute();
	}

	/**
	 * 获取配置文件
	 *
	 * @return 配置Props
	 */
	private static Properties getProperties() {
		// 读取配置文件
		Resource resource = new ClassPathResource("/config/application.properties");
		Properties props = new Properties();
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
	
	/**
	 * 页面生成的文件名
	 */
	private static String getGeneratorViewPath(String viewOutputDir, TableInfo tableInfo, String suffixPath) {
		String name = StringUtils.firstToLowerCase(tableInfo.getEntityName());
		String path = viewOutputDir + "/" + name + "/" + name + suffixPath;
		File viewDir = new File(path).getParentFile();
		if (!viewDir.exists()) {
			viewDir.mkdirs();
		}
		return path;
	}
}