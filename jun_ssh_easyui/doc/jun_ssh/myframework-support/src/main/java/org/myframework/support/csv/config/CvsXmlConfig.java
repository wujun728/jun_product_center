package org.myframework.support.csv.config;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.myframework.commons.util.Assert;
import org.myframework.commons.util.StringUtils;
import org.myframework.support.csv.config.CsvTable.CsvField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * <ol>
 * 读取XML配置文件中CSV映射关系
 * <li>{@link  }</li>
 *
 * </ol>
 *
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年6月15日
 *
 */
public class CvsXmlConfig extends XMLConfiguration implements CsvConfiguration {
	/**
	 *
	 */
	private static final long serialVersionUID = -17244126138842524L;

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(CvsXmlConfig.class);

	private Map<String, CsvTable> configStore;

	public CvsXmlConfig() {
		super();
	}

	public CvsXmlConfig(File file) throws ConfigurationException {
		super(file);
	}

	public CvsXmlConfig(String fileName) throws ConfigurationException {
		super(fileName);
	}

	/**
	 * 解析XML配置文件
	 */
	protected void parse() {
		logger.info("============START 解析XML配置文件" + getFileName());
		final String namespace = this.getString("[@namespace]");
		List<HierarchicalConfiguration> tables = this.configurationsAt("table");
		for (HierarchicalConfiguration table : tables) {
			final String id = StringUtils.isNullOrBlank(namespace) ? table
					.getString("[@id]") : (namespace + "." + table
					.getString("[@id]"));
			final String headerEnabled = table.getString("[@headerEnabled]");
			final String tableType = table.getString("[@tableType]");
			final String sqlkey = table.getString("[@sqlkey]");
			final String clz = table.getString("[@class]");
			final String rowFilter = table.getString("[@rowFilter]");
			final List<HierarchicalConfiguration> fields = table
					.configurationsAt("fields.field");
			configStore.put(id, new CsvTable() {
				@Override
				public String getName() {
					return id;
				}

				@Override
				public String getTableType() {
					return tableType;
				}

				@Override
				public String getClz() {
					return clz;
				}

				@Override
				public String getSqlKey() {
					return sqlkey;
				}
				
				@Override
				public String getRowFilter() {
					return rowFilter ;
				}

				@Override
				public List<CsvField> getCsvFields() {
					return getFieldList(fields);
				}

				@Override
				public String toString() {
					return getName() + ":[" + getName() + "," + getTableType()
							+ "," + getClz() + "," + getSqlKey() + ","
							+ getCsvFields() + "]";
				}

				@Override
				public boolean getHeaderEnabled() {
					return StringUtils.isNullOrBlank(headerEnabled)||"true".equalsIgnoreCase(headerEnabled);
				}
			});
		}
		logger.info("============END 解析XML配置文件" + getFileName());
	}

	protected List<CsvField> getFieldList(List<HierarchicalConfiguration> fields) {
		List<CsvField> fieldElements = new ArrayList<CsvField>();
		for (HierarchicalConfiguration field : fields) {
			final String name = field.getString("[@name]");
			final String type = field.getString("[@type]",CsvTable.STRING);
			final String format = field.getString("[@format]");
			final String desc = field.getString("[@desc]");
			final String maxlength = field.getString("[@maxlength]");
			final String minlength = field.getString("[@minlength]");
			final String required = field.getString("[@required]");
			final String regexp = field.getString("[@regexp]");
			final String validator = field.getString("[@validator]");
			final String defValue = field.getString("[@defValue]");
			final String max = field.getString("[@max]");
			final String min = field.getString("[@min]");
			final String exported = field.getString("[@exported]");
			final String imported = field.getString("[@imported]");
			fieldElements.add(new CsvField() {
				public boolean isExported() {//没有配置默认为true,或者配置为"true"时也为true
					return "true".equalsIgnoreCase(exported)||StringUtils.isNullOrBlank(exported);
				}

				public boolean isImported() {//没有配置默认为true,或者配置为"true"时也为true
					return "true".equalsIgnoreCase(imported)||StringUtils.isNullOrBlank(imported);
				}

				@Override
				public String getName() {
					return name;
				}

				@Override
				public String getType() {
					return StringUtils.isNullOrBlank(format)?type:CsvTable.CUSTOM;
				}

				@Override
				public String getFormat() {
					return format;
				}

				@Override
				public String getMaxLength() {
					return maxlength;
				}

				@Override
				public String getMinLength() {
					return minlength;
				}

				@Override
				public String getMax() {
					return max;
				}

				@Override
				public String getMin() {
					return min;
				}

				@Override
				public boolean isRequired() {
					return "true".equalsIgnoreCase(required);
				}

				@Override
				public String getValidator() {
					return validator;
				}

				@Override
				public String getRegexp() {
					return regexp;
				}

				@Override
				public String toString() {
					return "{" + getName() + "," + getType() + "," + getFormat()+ "}";
				}

				@Override
				public String getDesc() {
					return desc;
				}
				
				@Override
				public String getDefValue() {
					return defValue;
				}

			});
		}
		return fieldElements;
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
		@Override
		public void load(InputStream in) throws ConfigurationException {
			CvsXmlConfig.this.load(in);
			if (getConfigStore() == null) {
				setConfigStore(new HashMap<String, CsvTable>());
			}
			parse();
		}
	}

	@Override
	public List<CsvTable> getCsvTables() {
		super.reload();
		return (new ArrayList<CsvTable>(configStore.values()));
	}

	@Override
	public CsvTable getCsvTable(String key) {
		super.reload();
		return configStore.get(key);
	}

	@Override
	public void putCsvTable(String key, CsvTable element) {
		configStore.put(key, element);
	}

	@Override
	public void remove(String key) {
		configStore.remove(key);
	}

	Map<String, CsvTable> getConfigStore() {
		super.reload();
		return configStore;
	}

	void setConfigStore(Map<String, CsvTable> configStore) {
		this.configStore = configStore;
	}

	@Override
	public boolean containsKey(String key) {
		super.reload();
		return configStore.containsKey(key) || super.containsKey(key);
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

}
