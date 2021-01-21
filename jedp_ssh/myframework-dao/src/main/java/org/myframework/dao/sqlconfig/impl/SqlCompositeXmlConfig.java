package org.myframework.dao.sqlconfig.impl;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.myframework.commons.util.ExceptionUtils;
import org.myframework.dao.sqlconfig.SqlConfig;
import org.myframework.dao.sqlconfig.SqlMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.ResourceUtils;

/**
 *
 * <ol>
 * 按文件目录(默认目录sqlvelocity)加载SQL配置信息
 * 优先加载classpath下的配置，再加载jar中不重名的配置文件
 * <li>{@link SqlMapper  }</li>
 * <li>{@link XMLConfiguration  }</li>
 * </ol>
 *
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年6月29日
 *
 */
public class SqlCompositeXmlConfig implements SqlConfig, InitializingBean {
	/**
	 *
	 */
	protected final Log logger = LogFactory.getLog(getClass());

	private static final String CONFIG_LOCATION = "sqlvelocity";

	private List<SqlConfig> sqlConfigList = new ArrayList<SqlConfig>();

	private List<String> fileNameList = new ArrayList<String>();

	private SqlConfig inMemoryConfiguration;

	private String configLocation = CONFIG_LOCATION;

	public SqlCompositeXmlConfig(List<SqlConfig> csvConfigList) {
		super();
		this.sqlConfigList = csvConfigList;
	}

	public SqlCompositeXmlConfig(List<SqlConfig> csvConfigList,
			SqlConfig inMemoryConfiguration) {
		super();
		this.sqlConfigList = csvConfigList;
		csvConfigList.add(inMemoryConfiguration);
	}

	public SqlCompositeXmlConfig() {
		super();
	}

	public void setCsvConfigList(List<SqlConfig> csvConfigList) {
		this.sqlConfigList = csvConfigList;
	}

	public SqlCompositeXmlConfig(String configLocation) {
		super();
		this.configLocation = configLocation;
		reload();
	}

	public void setInMemoryConfiguration(SqlConfig inMemoryConfiguration) {
		this.inMemoryConfiguration = inMemoryConfiguration;
		sqlConfigList.add(inMemoryConfiguration);
	}

	@Override
	public void afterPropertiesSet() {
		if (!StringUtils.isEmpty(configLocation))
			reload();
	}

	public void setConfigLocation(String configLocation) {
		this.configLocation = configLocation;
	}

	public void reload() {
		sqlConfigList.clear();
		loadConfig(configLocation);
	}

	public void loadConfig(String directory) {
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			Enumeration<URL> urls = loader.getResources(directory);
			while (urls.hasMoreElements()) {
				URL fileURL = ResourceUtils.extractJarFileURL(urls
						.nextElement());
				if (ResourceUtils.isJarFileURL(fileURL)) {
					loadConfigFromJar(directory, fileURL);
				}else {
					loadConfigFromDirectory(new File(fileURL.toURI() ) );
				}
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getMessage(e));
			throw new RuntimeException(e.getMessage());
		}
	}

	public void loadConfigFromDirectory(File dirFile) {
		String filepath = dirFile.getAbsolutePath();
		if (dirFile.isDirectory()) {
			logger.info("#############start SQL查询配置文件加载############# " + filepath);
			File[] files = dirFile.listFiles();
			for (File configFile : files) {
				try {
					SqlMapperXMLConfig config = new SqlMapperXMLConfig(
							configFile);
					config.setReloadingStrategy(new FileChangedReloadingStrategy());
					sqlConfigList.add(config);
					fileNameList.add(configFile.getName());
				} catch (ConfigurationException e) {
					logger.error("load sqlconfig error :"
							+ configFile.getAbsolutePath() + ">>>"
							+ e.getMessage());
					throw new RuntimeException("load sqlconfig error :"
							+ configFile.getAbsolutePath() + ">>>"
							+ e.getMessage());
				}
			}
			logger.info("#############end SQL查询配置文件加载############# " + filepath);
		}
	}

	/**
	 *  加载JAR包中的配置文件
	 * @param directory
	 * @param fileURL
	 */
	protected void loadConfigFromJar(String directory, URL fileURL) {
		logger.info("#############start 加载JAR包中的配置文件############# " + fileURL);
		try {
			JarFile jarFile = new JarFile(fileURL.getFile());
			Enumeration<JarEntry> entrys = jarFile.entries();
			while (entrys.hasMoreElements()) {
				JarEntry jarEntry = (JarEntry) entrys.nextElement();
				String jarEntryName = jarEntry.getName();
				if (jarEntryName.startsWith(directory)
						&& jarEntryName.endsWith(".xml")
						&& !jarEntry.isDirectory()) {
					String jarXmlurl = ResourceUtils.JAR_URL_PREFIX
							+ ResourceUtils.FILE_URL_PREFIX + fileURL.getPath()
							+ ResourceUtils.JAR_URL_SEPARATOR + jarEntryName;
					//
					String fileName = jarEntryName.substring(directory.length()+1);
					if (!fileNameList.contains(fileName)) {
						SqlMapperXMLConfig config = new SqlMapperXMLConfig(
								jarXmlurl);
						sqlConfigList.add(config);
						fileNameList.add(jarEntry.getName());
					}
				}
			}
		} catch (Exception e) {
			logger.error("load sqlconfig error :" + fileURL + ">>>"
					+ e.getMessage());
			throw new RuntimeException("load sqlconfig error :" + fileURL + ">>>"
					+ e.getMessage());
		}
		logger.info("#############end 加载JAR包中的配置文件############# " + fileURL);
	}



	@Override
	public void reload(String dist) {
		sqlConfigList.clear();
		loadConfig(configLocation);
	}

	@Override
	public SqlMapper getSqlMapper(String sqlKey) {
		for (SqlConfig config : sqlConfigList) {
			SqlMapper sqlMapper = config.getSqlMapper(sqlKey);
			if (sqlMapper != null) {
				return sqlMapper;
			}
		}
		return null;
	}

	@Override
	public void addSqlMapper(String sqlKey, SqlMapper sqlMapper) {
		if (inMemoryConfiguration != null)
			inMemoryConfiguration.addSqlMapper(sqlKey, sqlMapper);
	}

	@Override
	public void removeSqlMapper(String sqlKey) {
		inMemoryConfiguration.removeSqlMapper(sqlKey);
	}

	@Override
	public Set<String> getKeyList() {
		Set<String> keys = new HashSet<String>();
		for (SqlConfig config : sqlConfigList) {
			keys.addAll(config.getKeyList());
		}
		return keys;
	}

}
