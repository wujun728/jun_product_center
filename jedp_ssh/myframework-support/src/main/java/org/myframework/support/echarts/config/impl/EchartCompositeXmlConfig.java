package org.myframework.support.echarts.config.impl;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.myframework.support.echarts.config.EchartConfig;
import org.myframework.support.echarts.config.EchartOption;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.ResourceUtils;

/**
 *
 * <ol>
 * 按文件目录(默认目录echartconfig)加载ECHARTS配置信息
 * 优先加载classpath下的配置，再加载jar中不重名的配置文件
 * <li>{@link EchartOption  }</li>
 * <li>{@link XMLConfiguration  }</li>
 * </ol>
 *
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年6月29日
 *
 */
public class EchartCompositeXmlConfig implements EchartConfig, InitializingBean {
	/**
	 *
	 */
	protected final Log logger = LogFactory.getLog(getClass());

	private static final String CONFIG_LOCATION = "echartconfig";

	private List<EchartConfig> echartConfigList = new ArrayList<EchartConfig>();

	private List<String> fileNameList = new ArrayList<String>();

	private EchartConfig inMemoryConfiguration;

	private String configLocation = CONFIG_LOCATION;


	@Override
	public void afterPropertiesSet() {
		if (!StringUtils.isEmpty(configLocation))
			reload();
	}

	public void setConfigLocation(String configLocation) {
		this.configLocation = configLocation;
	}

	public void reload() {
		echartConfigList.clear();
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
			logger.info("#############start ECHARTS配置加载############# " + filepath);
			File[] files = dirFile.listFiles();
			for (File configFile : files) {
				try {
					EchartXmlConfig config = new EchartXmlConfig(
							configFile);
					config.setReloadingStrategy(new FileChangedReloadingStrategy());
					echartConfigList.add(config);
					fileNameList.add(configFile.getName());
				} catch (ConfigurationException e) {
					logger.error(getMessage(e));
					throw new RuntimeException(getMessage(e));
				}
			}
			logger.info("#############end ECHARTS配置加载############# " + filepath);
		}
	}
	
	  private String getMessage(ConfigurationException e) {
	        if (e.getCause() != null ) {
	            return e.getCause().toString() + " >>>"+ e.getMessage();
	        } else if (e.getMessage() != null) {
	            return e.getMessage() ;
	        } else {
	            return null;
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
						EchartXmlConfig config = new EchartXmlConfig(
								jarXmlurl);
						echartConfigList.add(config);
						fileNameList.add(jarEntry.getName());
					}
				}
			}
		} catch (Exception e) {
			logger.error("load EchartConfig error :" + fileURL + ">>>"
					+ e.getMessage());
		}
		logger.info("#############end 加载JAR包中的配置文件############# " + fileURL);
	}



	public void reload(String dist) {
		echartConfigList.clear();
		loadConfig(configLocation);
	}

	@Override
	public EchartOption get(String key) {
		for (EchartConfig config : echartConfigList) {
			EchartOption echartOption = config.get(key) ;
			if (echartOption != null) {
				return echartOption;
			}
		}
		return null;
	}

	@Override
	public void put(String key, EchartOption EchartOption) {
		if (inMemoryConfiguration != null)
			inMemoryConfiguration.put(key, EchartOption);
	}

	@Override
	public void remove(String key) {
		inMemoryConfiguration.remove(key);
	}


	public EchartCompositeXmlConfig(List<EchartConfig> echartConfigList) {
		super();
		this.echartConfigList = echartConfigList;
	}

	public EchartCompositeXmlConfig(List<EchartConfig> echartConfigList,
			EchartConfig inMemoryConfiguration) {
		super();
		this.echartConfigList = echartConfigList;
		echartConfigList.add(inMemoryConfiguration);
	}

	public EchartCompositeXmlConfig() {
		super();
	}

	public void setEchartConfigList(List<EchartConfig> echartConfigList) {
		this.echartConfigList = echartConfigList;
	}

	public EchartCompositeXmlConfig(String configLocation) {
		super();
		this.configLocation = configLocation;
		reload();
	}

	public void setInMemoryConfiguration(EchartConfig inMemoryConfiguration) {
		this.inMemoryConfiguration = inMemoryConfiguration;
		echartConfigList.add(inMemoryConfiguration);
	}

	@Override
	public boolean containsKey(String key) {
		for (EchartConfig config : echartConfigList) {
			boolean exists = config.containsKey(key);
			if (exists) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void removeAll() {
		for (EchartConfig config : echartConfigList) {
			config.removeAll();
		}
	}

}
