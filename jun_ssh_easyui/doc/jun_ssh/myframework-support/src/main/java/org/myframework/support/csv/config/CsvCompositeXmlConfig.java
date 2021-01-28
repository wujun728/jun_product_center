package org.myframework.support.csv.config;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.myframework.commons.util.Assert;
import org.myframework.commons.util.ExceptionUtils;
import org.myframework.support.csv.config.CsvTable.CsvField;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.ResourceUtils;

/**
 *
 * <ol>
 * 按文件目录(默认目录csvconfig)来读取CSV映射配置信息
 * <li>{@link CvsXmlConfig}</li>
 * <li>{@link CsvConfiguration }</li>
 * <li>{@link CsvTable }</li>
 * <li>{@link CsvField }</li>
 * </ol>
 *
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年6月15日
 *
 */
public class CsvCompositeXmlConfig implements CsvConfiguration,
		InitializingBean {
	/**
	 *
	 */
	protected final Log logger = LogFactory.getLog(getClass());

	private static final String CONFIG_LOCATION = "csvconfig";

	private List<CsvConfiguration> csvConfigList = new LinkedList<CsvConfiguration>();

	private CsvConfiguration inMemoryConfiguration;

	private String configLocation = CONFIG_LOCATION;

	@Override
	public List<CsvTable> getCsvTables() {
		List<CsvTable> list = new ArrayList<CsvTable>();
		Iterator<CsvConfiguration> it = csvConfigList.iterator();
		while (it.hasNext()) {
			CsvConfiguration config = it.next();
			list.addAll(config.getCsvTables());
		}
		return list;
	}

	@Override
	public CsvTable getCsvTable(String key) {
		CsvConfiguration firstMatchingConfiguration = null;
		for (CsvConfiguration config : csvConfigList) {
			if (config.containsKey(key)) {
				firstMatchingConfiguration = config;
				break;
			}
		}

		if (firstMatchingConfiguration != null) {
			return firstMatchingConfiguration.getCsvTable(key);
		} else {
			return null;
		}
	}

	@Override
	public void putCsvTable(String key, CsvTable element) {
		if (inMemoryConfiguration != null)
			inMemoryConfiguration.putCsvTable(key, element);
	}

	@Override
	public void remove(String key) {

		CsvConfiguration firstMatchingConfiguration = null;
		for (CsvConfiguration config : csvConfigList) {
			if (config.containsKey(key)) {
				firstMatchingConfiguration = config;
				break;
			}
		}
		if (firstMatchingConfiguration != null) {
			firstMatchingConfiguration.remove(key);
		}
	}

	@Override
	public boolean containsKey(String key) {
		CsvConfiguration firstMatchingConfiguration = null;
		for (CsvConfiguration config : csvConfigList) {
			if (config.containsKey(key)) {
				firstMatchingConfiguration = config;
				break;
			}
		}

		if (firstMatchingConfiguration != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取可以被导出的列
	 *
	 * @param tableId
	 * @return
	 */
	@Override
	public List<? extends CsvField> getExportedCsvFields(String tableId) {
		List< CsvField> exportedFields = new ArrayList<CsvField>();
		CsvTable table = getCsvTable(tableId);
		Assert.notNull(table, tableId + " 对应的CSV配置信息不存在");
		List<? extends CsvField> fields = table.getCsvFields();
		for (CsvField csvField : fields) {
			if( csvField.isExported()) {
				exportedFields.add(csvField);
			}
		}
		return exportedFields;
	}


	/**
	 * 获取可以被导入的列
	 * @param tableId
	 * @return
	 */
	@Override
	public List<? extends CsvField> getImportedCsvFields(String tableId) {
		List< CsvField> importedFields = new ArrayList<CsvField>();
		CsvTable table = getCsvTable(tableId);
		Assert.notNull(table, tableId + " 对应的CSV配置信息不存在");
		List<? extends CsvField> fields = table.getCsvFields();
		for (CsvField csvField : fields) {
			if(csvField.isImported()) {
				importedFields.add(csvField);
			}
		}
		return importedFields;
	}

	public CsvCompositeXmlConfig(List<CsvConfiguration> csvConfigList) {
		super();
		this.csvConfigList = csvConfigList;
	}

	public CsvCompositeXmlConfig(List<CsvConfiguration> csvConfigList,
			CsvConfiguration inMemoryConfiguration) {
		super();
		this.csvConfigList = csvConfigList;
		csvConfigList.add(inMemoryConfiguration);
	}

	public CsvCompositeXmlConfig() {
		super();
	}

	public void setCsvConfigList(List<CsvConfiguration> csvConfigList) {
		this.csvConfigList = csvConfigList;
	}

	public CsvCompositeXmlConfig(String configLocation) {
		super();
		this.configLocation = configLocation;
		reload();
	}

	public void setInMemoryConfiguration(CsvConfiguration inMemoryConfiguration) {
		this.inMemoryConfiguration = inMemoryConfiguration;
		csvConfigList.add(inMemoryConfiguration);
	}

	@Override
	public void afterPropertiesSet() {
		if (!StringUtils.isEmpty(configLocation))
			reload();
	}

	public void setConfigLocation(String configLocation) {
		this.configLocation = configLocation;
	}

	void reload() {
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
				} else {
					loadConfigFromDirectory(new File(fileURL.toURI()));
				}
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getMessage(e));
		}
	}

	/**
	 * 加载JAR包中的配置文件
	 *
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
					String fileName = jarEntryName
							.substring(directory.length() + 1);
					if (!fileNameList.contains(fileName)) {
						CvsXmlConfig config = new CvsXmlConfig(jarXmlurl);
						csvConfigList.add(config);
						fileNameList.add(jarEntry.getName());
					}
				}
			}
		} catch (Exception e) {
			logger.error("load sqlconfig error :" + fileURL + ">>>"
					+ e.getMessage());
		}
		logger.info("#############end 加载JAR包中的配置文件############# " + fileURL);
	}

	public void loadConfigFromDirectory(File dirFile) {
		String filepath = dirFile.getAbsolutePath();
		if (dirFile.isDirectory()) {
			logger.info("#############start 导入导出xml配置加载 ############# " + filepath);
			File[] files = dirFile.listFiles();
			for (File configFile : files) {
				try {
					CvsXmlConfig config = new CvsXmlConfig(configFile);
					config.setReloadingStrategy(new FileChangedReloadingStrategy());
					csvConfigList.add(config);
				} catch (ConfigurationException e) {
					logger.error("load csvconfig error :"
							+ configFile.getAbsolutePath() + ">>>"
							+ e.getMessage());
				}
			}
			logger.info("#############end 导入导出xml配置加载############# " + filepath);
		}

	}

	private List<String> fileNameList = new ArrayList<String>();



}
