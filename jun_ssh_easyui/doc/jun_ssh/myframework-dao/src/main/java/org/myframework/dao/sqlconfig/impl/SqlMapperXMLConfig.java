package org.myframework.dao.sqlconfig.impl;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.myframework.commons.util.StringUtils;
import org.myframework.dao.sqlconfig.SqlConfig;
import org.myframework.dao.sqlconfig.SqlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * <ol>按文件名加载SQL配置信息
 * <li>{@link SqlMapper  }</li>
 * <li>{@link XMLConfiguration  }</li>
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年6月29日
 *
 */
public class SqlMapperXMLConfig extends XMLConfiguration implements SqlConfig {
	private static final Logger logger = LoggerFactory
			.getLogger(SqlMapperXMLConfig.class);

	protected Map<String, SqlMapper> sqlConfigCache;

	Map<String, SqlMapper> getSqlConfigCache() {
		return sqlConfigCache;
	}

	void setSqlConfigCache(Map<String, SqlMapper> sqlConfigCache) {
		this.sqlConfigCache = sqlConfigCache;
	}

	public SqlMapperXMLConfig() {
		super();
	}

	public SqlMapperXMLConfig(File file) throws ConfigurationException {
		super(file);
	}

	public SqlMapperXMLConfig(String fileName) throws ConfigurationException {
		super(fileName);
	}

	public SqlMapperXMLConfig(URL url) throws ConfigurationException {
		super(url);
	}

	@Override
	public SqlMapper getSqlMapper(String sqlKey) {
		super.reload();
		return sqlConfigCache.get(sqlKey);
	}

	@Override
	public void addSqlMapper(String sqlKey, SqlMapper sqlMapper) {
		// sqlConfigCache.remove(sqlKey);
		sqlConfigCache.put(sqlKey, sqlMapper);
	}

	@Override
	public void removeSqlMapper(String sqlKey) {
		sqlConfigCache.remove(sqlKey);
	}

	@Override
	public Set<String> getKeyList() {
		super.reload();
		return sqlConfigCache.keySet();
	}

	@Override
	public void reload(String dist) {
		reload();
	}

	/**
	 * 解析XML配置文件
	 */
	protected void parse() {
		logger.info("============START 解析XML配置文件" + getFileName());
		final String namespace = this.getString("[@namespace]");
		final String globalUseCache = this.getString("[@useCache]");
		List<HierarchicalConfiguration> sqls = this.configurationsAt("sql");
		for (HierarchicalConfiguration sql : sqls) {
			final String nodeUseCache = sql.getString("[@useCache]");
			final String flushCache = sql.getString("[@flushCache]");
			final String sqlId = sql.getString("[@id]");
			final String sqlTxt = sql.getString(null);
			SqlMapper sqlMapper = new SqlMapper() {
				public boolean isUseCache() {
					if (!StringUtils.isEmpty(nodeUseCache)) {
						return "true".equalsIgnoreCase(nodeUseCache);
					} else {
						return "true".equalsIgnoreCase(globalUseCache);
					}
				}

				public boolean isFlushCache() {
					return "true".equalsIgnoreCase(flushCache);
				}

				public String getSqlKey() {
					return namespace + "." + sqlId;
				}

				public String getSqlCode() {
					return sqlTxt;
				}

				@Override
				public String toString() {
					return " SqlMapper  [isUseCache()=" + isUseCache()
							+ ", getSqlKey()=" + getSqlKey()
							+ ", getSqlCode()=" + getSqlCode()
							+ ", isFlushCache()=" + isFlushCache()
							+ " ,filePath = "
							+ SqlMapperXMLConfig.this.getURL() + "]";
				}

			};
			SqlMapperXMLConfig.this.addSqlMapper(sqlMapper.getSqlKey(),
					sqlMapper);
		}
		logger.info("============END 解析XML配置文件" + getFileName());
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
			SqlMapperXMLConfig.this.setDelimiterParsingDisabled(true);
		}

		@Override
		public void load(InputStream in) throws ConfigurationException {
			SqlMapperXMLConfig.this.load(in);
			if(getSqlConfigCache()==null) {
				setSqlConfigCache(new HashMap<String, SqlMapper>());
			}
			parse();
		}
	}

}
