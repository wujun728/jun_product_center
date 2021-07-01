package generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class LinGenerater {
	
	AutoGenerator mpg = new AutoGenerator();
	private String databaseName = "lin";
	private String tableName;
	private String modelName;
	private static VelocityEngine ve = new VelocityEngine();
	private File velocityOutdir = new File("d:\\Lin生成代码\\lin\\src\\main\\java\\com\\du\\lin\\");
	private List<String> attrs = new ArrayList<String>();
	private String menuName;
	private String icon;
	/**
	 * @param tableName 表名
	 */
	public LinGenerater(String tableName) {
		super();
		this.tableName = tableName;
		this.modelName = tableName.substring(0, 1).toUpperCase() + tableName.substring(1);
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getTableName() {
		return tableName;
	}

	public String getModelName() {
		return modelName;
	}
	
	
	
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	 /**
	 * 全局配置
	 */
	private void globalConfigInit() {
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir("D://Lin生成代码\\lin\\src\\main\\java");
		gc.setFileOverride(true);
		gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(false);// XML columList
		gc.setAuthor("lin");
		mpg.setGlobalConfig(gc);
	}
	 /**
	 * 数据源配置
	 */
	private void dataSourceConfigInit() {
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("123456");
		dsc.setUrl("jdbc:mysql://127.0.0.1:3306/" + databaseName
				+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		mpg.setDataSource(dsc);
	}
	 /**
	 * 策略配置
	 */
	private void strategyConfigInit() {
		StrategyConfig strategyConfig = new StrategyConfig();
		// strategy.setTablePrefix(new String[]{"_"});// 此处可以修改为您的表前缀
		strategyConfig.setInclude(new String[] { tableName });// 这里限制需要生成的表,不写则是生成所有表
		strategyConfig.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略  下划线转驼峰命名
		mpg.setStrategy(strategyConfig);
	}
	 /**
	 * 包配置
	 */
	private void packageConfigInit() {
		PackageConfig pc = new PackageConfig();
		pc.setParent(null);
		pc.setEntity("com.du.lin.bean");
		pc.setMapper("com.du.lin.dao");
		mpg.setPackageInfo(pc);

	}
	 /**
	 * 初始化Velocity
	 */
	private void VelocityInit() {
		ve.setProperty(VelocityEngine.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.setProperty(Velocity.ENCODING_DEFAULT, "utf8");
		ve.setProperty(Velocity.INPUT_ENCODING, "utf8");
		ve.setProperty(Velocity.OUTPUT_ENCODING, "utf8");
		ve.init();
	}
	 /**
	 * MP模板设置
	 */
	private void templateConfigInit(){
		TemplateConfig tc = new TemplateConfig();
		tc.setController(null);
		tc.setXml(null);
		tc.setService(null);
		tc.setServiceImpl(null);
		mpg.setTemplate(tc);
	}
	 /**
	 * 初始化
	 */	
	private void init() {
		attrs = getColumnList();
		globalConfigInit();
		dataSourceConfigInit();
		strategyConfigInit();
		packageConfigInit();
		templateConfigInit();
		VelocityInit();
	}
    /**
	 * 1.初始化 2.Velocity生成Controller、html、js、Service、ServiceImpl  3.MP生成Mapper、bean
	 */
	public void execute() {
		init();
		// 执行生成
		velocityGenerate();
		mpg.execute();
		System.out.println("可以使用LinGenerater中的insertMenu方法将菜单信息写入数据库");
		System.out.println("之后在角色管理中配置菜单即可");
	}
    /**
	 * 获取数据库表字段
	 */
	private List<String> getColumnList() {
		List<String> columnName = new ArrayList<String>();
		Connection conn = null;
		java.sql.PreparedStatement pst = null;
		ResultSet re = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/" + databaseName +"?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull",
					"root", "123456");
			pst = conn.prepareStatement("select * from " + tableName);
			// 获取数据库列
			re = pst.executeQuery();
			// 数据库列名
			ResultSetMetaData data = re.getMetaData();

			for (int i = 1; i <= data.getColumnCount(); i++) {
				columnName.add(data.getColumnName(i));
			}
			
			columnName.remove("id");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				re.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return columnName;
	}

	

    /**
	 * Velocity生成Controller
	 */
	private void controllerGenterate() throws IOException {
		Template t = ve.getTemplate("/templates/vm/Controller.java.vm");
		VelocityContext vc = new VelocityContext();
		vc.put("model", getTableName());
		// 首写字母大写
		vc.put("model1", getModelName());
		File file = new File(velocityOutdir.getAbsolutePath() + "\\controller\\" + getModelName() + "Controller.java");
		CreateParents(file);
		FileWriter fw = new FileWriter(file);
		t.merge(vc, fw);
		fw.flush();
		fw.close();
	}
    /**
	 * Velocity生成html
	 */
	private void htmlGenterate() throws IOException {
		Template t = ve.getTemplate("/templates/vm/Page.html.vm");
		VelocityContext vc = new VelocityContext();
		vc.put("model", getTableName());
		// 首写字母大写
		vc.put("model1", getModelName());
		File file = new File("d:\\Lin生成代码\\lin\\src\\main\\resources\\templates\\" + getTableName() + "page.html");
		CreateParents(file);
		FileWriter fw = new FileWriter(file);
		t.merge(vc, fw);
		fw.flush();
		fw.close();

	}
    /**
	 * Velocity生成js
	 */
	private void jsGenterate() throws IOException {
		Template t = ve.getTemplate("/templates/vm/Page.js.vm");
		VelocityContext vc = new VelocityContext();
		vc.put("model", getTableName());
		// 首写字母大写
		vc.put("model1", getModelName());
		vc.put("attrs", attrs);
		File file = new File("d:\\Lin生成代码\\lin\\src\\main\\webapp\\static\\js\\lin\\" + getTableName() + ".js");
		CreateParents(file);
		FileWriter fw = new FileWriter(file);
		t.merge(vc, fw);
		fw.flush();
		fw.close();

	}
    /**
	 * Velocity生成Service
	 */
	private void serviceGenterate() throws IOException {
		Template t = ve.getTemplate("/templates/vm/Service.java.vm");
		VelocityContext vc = new VelocityContext();
		vc.put("model", getTableName());
		// 首写字母大写
		vc.put("model1", getModelName());
		File file = new File(
				velocityOutdir.getAbsolutePath() + "\\service\\" + getModelName() + "Service.java");
		CreateParents(file);
		FileWriter fw = new FileWriter(file);
		t.merge(vc, fw);
		fw.flush();
		fw.close();
	}
    /**
	 * Velocity生成ServiceImpl
	 */
	private void serviceImplGenterate() throws IOException {
		Template t = ve.getTemplate("/templates/vm/ServiceImpl.java.vm");
		VelocityContext vc = new VelocityContext();
		vc.put("model", getTableName());
		// 首写字母大写
		vc.put("model1", getModelName());
		
		File file = new File(
				velocityOutdir.getAbsolutePath() + "\\service\\impl\\" + getModelName() + "ServiceImpl.java");


		CreateParents(file);
		FileWriter fw = new FileWriter(file);
		t.merge(vc, fw);
		fw.flush();
		fw.close();
	}

	private void velocityGenerate() {
		try {
			controllerGenterate();
			serviceGenterate();
			serviceImplGenterate();
			jsGenterate();
			htmlGenterate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 创建文件的父文件夹
	 */
	private void CreateParents(File file){
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
	}
	/**
	 * 插入菜单数据到数据库
	 */	
	public void insertMenu(String tabelName , String menuName , String icon){
		if (tabelName == null ||"".equals(tabelName)) {
			System.out.println("tableName error");
		}
		if (menuName == null ||"".equals(menuName)) {
			System.out.println("menuName error");
		}
		if (icon == null ||"".equals(icon)) {
			System.out.println("icon error");
		}
		
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/lin?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull",
						"root", "123456");
			ps =	conn.prepareStatement("insert into menu (name,icon,url,levels) values(?,?,?,?)");
			ps.setString(1, menuName);
			ps.setString(2, "fa-"+icon);
			ps.setString(3, "/" + tabelName);
			ps.setInt(4, 1);
			int result = ps.executeUpdate();
			if (result == 1) {
				System.out.println("菜单添加成功！");
				System.out.println("在角色管理中配置菜单，即可在右侧看到此菜单");
			}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}finally{
				try {
					ps.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	}
	
}
