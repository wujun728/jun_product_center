package org.myframework.support.echarts.config.impl;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.myframework.support.echarts.config.EchartConfig;
import org.myframework.support.echarts.config.EchartOption;
import org.myframework.support.echarts.exception.EchartOptionDuplicateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * <ol>按文件名加载echart配置信息
 * <li>{@link EchartOption  }</li>
 * <li>{@link XMLConfiguration  }</li>
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年11月18日
 *
 */
public class EchartXmlConfig extends XMLConfiguration implements EchartConfig {
	private static final Logger logger = LoggerFactory
			.getLogger(EchartXmlConfig.class);

	//有代理类进行初始化操作
	protected Map<String, EchartOption> echartConfigCache ;

	/**
	 * 解析XML配置文件
	 */
	protected void parse() {
		logger.info("============START 解析XML配置文件" + getFileName());
		final String namespace = this.getString("[@namespace]");
		List<HierarchicalConfiguration> echarts = this.configurationsAt("echart");
		for (HierarchicalConfiguration echart : echarts) {
			final String name = echart.getString("[@name]");
			final String type = echart.getString("[@type]");
			final String title = echart.getString("[@title]");
			final String ref = echart.getString("[@ref]");
			final String legendData = echart.getString("[@legendData]");
			final String categoryData = echart.getString("[@categoryData]");
			final String seriesData = echart.getString("[@seriesData]");
			final String dataType = echart.getString("[@dataType]");
			final String optionContent = echart.getString(null);
			EchartOption echartOption = new EchartOption() {

				@Override
				public String getId() {
					return namespace+"."+name;
				}
				
				@Override
				public String getTitle() {
					return title ;
				}

				@Override
				public String getType() {
					return type;
				}

				@Override
				public String getRef() {
					return ref;
				}

				@Override
				public String getLegendData() {
					return legendData;
				}

				@Override
				public String getCategoryData() {
					return categoryData;
				}

				@Override
				public String getSeriesData() {
					return seriesData;
				}

				@Override
				public String getDataType() {
					return dataType;
				}

				@Override
				public String getOptionContent() {
					return optionContent;
				}

			};
			EchartXmlConfig.this.check(echartOption.getId());
			EchartXmlConfig.this.put(echartOption.getId(),echartOption);
		}
		logger.info("============END 解析XML配置文件" + getFileName());
	}



	protected void check(String key) {
		if (echartConfigCache.containsKey(key)) {
			throw new EchartOptionDuplicateException("Duplicate key : "
					+ key);
		}
	}


	@Override
	public String toString() {
		return echartConfigCache.toString();
	}

	@Override
	public boolean containsKey(String key) {
		return echartConfigCache.containsKey(key);
	}

	@Override
	public EchartOption get(String key) {
		//如果文件发生变更，reload方法执行后就会重新加载新的内容
		super.reload();
		return echartConfigCache.get(key);
	}

	@Override
	public void reload() {
		echartConfigCache.clear();
		super.reload();
	}

	@Override
	public void put(String key, EchartOption value) {
		echartConfigCache.put(key, value);
	}

	@Override
	public void remove(String key) {
		echartConfigCache.remove(key);
	}

	@Override
	public void removeAll() {
		echartConfigCache.clear();
	}

	@Override
	protected FileConfigurationDelegate createDelegate() {
		return new XMLFileConfigurationDelegate();
	}

 
	/**
	 * A special implementation of the {@code FileConfiguration} interface that
	 * is used internally to implement the {@code FileConfiguration} methods for
	 * {@code XMLConfiguration}, too.
	 */
	private class XMLFileConfigurationDelegate extends
			FileConfigurationDelegate {
		public XMLFileConfigurationDelegate() {
			EchartXmlConfig.this.setDelimiterParsingDisabled(true);
		}

		@Override
		public void load(InputStream in) throws ConfigurationException {
			EchartXmlConfig.this.load(in);
			if(getEchartConfigCache()==null) {
				setEchartConfigCache(new HashMap<String, EchartOption>());
			}
			parse();
		}
	}
	

	public Map<String, EchartOption> getEchartConfigCache() {
		return echartConfigCache;
	}

	public void setEchartConfigCache(Map<String, EchartOption> echartConfigCache) {
		this.echartConfigCache = echartConfigCache;
	}


	public EchartXmlConfig() {
		super();
	}

	public EchartXmlConfig(File file) throws ConfigurationException {
		super(file);
	}

	public EchartXmlConfig(String fileName) throws ConfigurationException {
		super(fileName);
	}

	public EchartXmlConfig(URL url) throws ConfigurationException {
		super(url);
	}

}
