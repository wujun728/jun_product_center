package org.myframework.support.csv.config;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.myframework.commons.util.Assert;
import org.myframework.commons.util.StringUtils;
import org.myframework.dao.jdbc.impl.SimpleJdbcCrud;
import org.myframework.dao.metadata.ColumnInfo;
import org.myframework.support.csv.annotation.CsvColumn;
import org.myframework.support.csv.annotation.CsvEntity;
import org.myframework.support.csv.config.CsvTable.CsvField;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.annotation.AnnotationUtils;

/**
 *
 * <ol>
 * 读取entity实体的定义，生成导入导出的默认配置信息，采用读取数据表信息的方式读取列信息
 * <li>{@link }</li>
 *
 * </ol>
 *
 * @see LoadPackageClasses
 * @author Wujun
 * @since 1.0
 * @2015年7月15日
 *
 */
public class CsvAnnotationConfig implements InitializingBean, CsvConfiguration {
	final Log logger = LogFactory.getLog(getClass());
	LoadPackageClasses loader;

	private String[] packagesToScan;

	public void setPackagesToScan(String... packagesToScan) {
		this.packagesToScan = packagesToScan;
	}

	private Map<String, CsvTable> configStore = new HashMap<String, CsvTable>();

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info(
				"##########################Entity上的导入导出配置加载 start#############");
		loader = new LoadPackageClasses(packagesToScan, Entity.class);
		Set<Class<?>> clzClasses = loader.getClassSet();
		for (Class<?> entityClz : clzClasses) {
			parse(entityClz);
		}
		logger.info(
				"##########################Entity上的导入导出配置加载 end #############");
	}

	private void parse(final Class entityClz) {
		final String id = "entities." + entityClz.getSimpleName();
		logger.info("加载配置,id=" + id);
		// CsvEntity相关信息
		final CsvEntity csvEntity = AnnotationUtils.getAnnotation(entityClz,
				CsvEntity.class);
		configStore.put(id, new CsvTable() {
			@Override
			public String getName() {
				return csvEntity != null
						&& !StringUtils.isNullOrBlank(csvEntity.name())
								? csvEntity.name() : id;
			}

			@Override
			public String getTableType() {
				return null;
			}

			@Override
			public String getClz() {
				return entityClz.getName();
			}

			@Override
			public String getSqlKey() {
				return null;
			}

			@Override
			public boolean getHeaderEnabled() {
				return false;
			}

			@Override
			public String getRowFilter() {
				return null;
			}

			@Override
			public List<? extends CsvField> getCsvFields() {
				return null;
			}

		});
	}

	private CsvTable loadConfigFromTableInfo(final String id, String clzName)
			throws ClassNotFoundException {
		final Class entityClz = Class.forName(clzName);
		final Method[] methods = getMethods(entityClz);
		final Field[] fields = getFields(entityClz);
		// 数据库读取注释信息
		Table table = AnnotationUtils.getAnnotation(entityClz, Table.class);
		final List<ColumnInfo> columnInfos = jdbc
				.getTableColumnInfos(table.name());
		logger.info(table.name() + "列信息如下：" + columnInfos);
		// CsvEntity相关信息
		final CsvEntity csvEntity = AnnotationUtils.getAnnotation(entityClz,
				CsvEntity.class);
		configStore.put(id, new CsvTable() {
			@Override
			public String getName() {
				return csvEntity != null
						&& !StringUtils.isNullOrBlank(csvEntity.name())
								? csvEntity.name() : id;
			}

			@Override
			public String getTableType() {
				return "";
			}

			@Override
			public String getClz() {
				return entityClz.getName();
			}

			@Override
			public String getSqlKey() {
				return csvEntity != null ? csvEntity.sqlKey() : "";
			}

			@Override
			public String getRowFilter() {
				return csvEntity != null ? csvEntity.rowFilter() : "";
			}

			@Override
			public boolean getHeaderEnabled() {
				return csvEntity != null ? csvEntity.headerEnabled() : true;
			}

			@Override
			public List<CsvField> getCsvFields() {
				List<CsvField> csvfields = getFieldList(fields, columnInfos);
				List<CsvField> methodCsvfields = getFieldList(methods,
						columnInfos);
				csvfields.addAll(methodCsvfields);
				return csvfields;
			}

			@Override
			public String toString() {
				return getName() + ":[" + getName() + "," + getTableType() + ","
						+ getClz() + "," + getSqlKey() + "," + getCsvFields()
						+ "]";
			}
		});

		return configStore.get(id);
	}

	/**
	 * 获取类中所有定义的属性
	 * @param entityClz
	 * @return
	 */
	private Field[] getFields(final Class entityClz) {
		List<Field> fields = new ArrayList<Field>();

		Field[] field0 = entityClz.getDeclaredFields();
		for (Field field : field0) {
			fields.add(field);
		}
//		field0 = entityClz.getSuperclass().getDeclaredFields();
//		for (Field field : field0) {
//			fields.add(field);
//		}

		return fields.toArray(new Field[] {});
	}

	/**
	 * 获取类中所有定义的方法
	 * @param entityClz
	 * @return
	 */
	private Method[] getMethods(final Class entityClz) {
		List<Method> methods = new ArrayList<Method>();
		Method[] methods0 = entityClz.getDeclaredMethods();
		for (Method method : methods0) {
			logger.debug(method);
			methods.add(method);
		}
//		methods0 = entityClz.getSuperclass().getDeclaredMethods();
//		for (Method method : methods0) {
//			methods.add(method);
//		}
		
		return methods.toArray( new Method[] {} ) ;
	}

	/**
	 * 获取entity与导出导入文档的字段与列的映射关系
	 *
	 * @param methods
	 * @return
	 */
	protected List<CsvField> getFieldList(Method[] methods,
			final List<ColumnInfo> columnInfos) {
		List<CsvField> fieldElements = new ArrayList<CsvField>();
		for (int i = 0; i < methods.length; i++) {
			final Method method = methods[i];
			final Column column = AnnotationUtils.getAnnotation(method,
					Column.class);
			final CsvColumn csvColumn = AnnotationUtils
					.getAnnotation(method, CsvColumn.class);
			if ((column != null ||csvColumn!=null)&& Modifier.isPublic(method.getModifiers())
					&& method.getName().startsWith("get")) {

				final String name = StringUtils
						.uncapitalize(method.getName().substring(3));

				final String type = csvColumn != null
						&& !StringUtils.isNullOrBlank(csvColumn.type())
								? csvColumn.type()
								: getCsvType(method.getReturnType());

				final String desc = csvColumn != null
						&& !StringUtils.isNullOrBlank(csvColumn.desc())
								? csvColumn.desc()
								: getColumnLabel(column.name(), columnInfos);
				 
				fieldElements.add(createCsvField(name, type, desc, column,
						csvColumn));
			}
		}
		return fieldElements;
	}

	/**
	 * 获取entity与导出导入文档的字段与列的映射关系
	 *
	 * @param fields
	 * @return
	 */
	protected List<CsvField> getFieldList(Field[] fields,
			final List<ColumnInfo> columnInfos) {
		List<CsvField> fieldElements = new ArrayList<CsvField>();
		for (int i = 0; i < fields.length; i++) {
			final Field field = fields[i];
			final Column column = AnnotationUtils.getAnnotation(field,
					Column.class);
			final CsvColumn csvFieldAnnotation = AnnotationUtils
					.getAnnotation(field, CsvColumn.class);

			if (column != null) {
				final String name = field.getName();
				final String type = csvFieldAnnotation != null
						&& !StringUtils.isNullOrBlank(csvFieldAnnotation.type())
								? csvFieldAnnotation.type()
								: getCsvType(field.getType());

				final String desc = csvFieldAnnotation != null
						&& !StringUtils.isNullOrBlank(csvFieldAnnotation.desc())
								? csvFieldAnnotation.desc()
								: getColumnLabel(column.name(), columnInfos);
				fieldElements.add(createCsvField(name, type, desc, column,
						csvFieldAnnotation));
			}
		}
		return fieldElements;
	}

	private CsvField createCsvField(final String name, final String type,
			final String desc, final Column column,
			final CsvColumn csvFieldAnnotation) {
		return new CsvField() {

			public boolean isExported() {
				return csvFieldAnnotation != null
						? csvFieldAnnotation.exported() : true;
			}

			public boolean isImported() {
				return csvFieldAnnotation != null
						? csvFieldAnnotation.imported() : true;
			}

			@Override
			public String getName() {
				return name;
			}

			@Override
			public String getType() {
				return (csvFieldAnnotation != null && !StringUtils
						.isNullOrBlank(csvFieldAnnotation.format()))
								? CsvTable.CUSTOM : type;
			}

			@Override
			public String getFormat() {
				return csvFieldAnnotation != null ? csvFieldAnnotation.format()
						: "";
			}

			@Override
			public String getMaxLength() {
				int maxLen = csvFieldAnnotation != null
						? csvFieldAnnotation.maxLength() : column.length();
				maxLen = (maxLen != Integer.MAX_VALUE) ? maxLen
						: (column==null?Integer.MAX_VALUE:column.length());
				return maxLen + "";
			}

			@Override
			public String getMinLength() {
				return csvFieldAnnotation != null
						? csvFieldAnnotation.minLength() + "" : "";
			}

			@Override
			public String getMax() {
				return csvFieldAnnotation != null
						? csvFieldAnnotation.max() + "" : "";
			}

			@Override
			public String getMin() {
				return csvFieldAnnotation != null
						? csvFieldAnnotation.min() + "" : "";
			}

			@Override
			public boolean isRequired() {
				return csvFieldAnnotation != null
						? csvFieldAnnotation.required() : !column.nullable();
			}

			@Override
			public String getRegexp() {
				return csvFieldAnnotation != null
						? csvFieldAnnotation.regexp() + "" : "";
			}

			@Override
			public String getDesc() {
				return desc;
			}

			@Override
			public String getValidator() {
				return csvFieldAnnotation != null
						? csvFieldAnnotation.validator() : "";
			}

			@Override
			public String toString() {
				return getName() + "," + getType() + "," + getFormat();
			}

			@Override
			public String getDefValue() {
				return csvFieldAnnotation != null
						? csvFieldAnnotation.defValue() : "";
			}
		};
	}

	protected String getCsvType(Class clz) {
		String type = "string";
		if (java.sql.Date.class.equals(clz)) {
			type = CsvTable.DATE;
		}
		if (java.sql.Timestamp.class.equals(clz)) {
			type = CsvTable.DATE_TIME;
		}
		if (java.lang.Number.class.isAssignableFrom(clz)) {
			type = CsvTable.NUMBER;
		}
		return type;
	}

	SimpleJdbcCrud jdbc;

	/**
	 * 从数据库中获取描述信息
	 *
	 * @param columnName
	 * @param columnInfos
	 * @return
	 */
	protected String getColumnLabel(String columnName,
			List<ColumnInfo> columnInfos) {
		String columnLabel = null;
		for (ColumnInfo columnInfo : columnInfos) {
			if (columnName.equalsIgnoreCase(columnInfo.getColumnName())) {
				columnLabel = columnInfo.getColumnLabel();
				return StringUtils.isNullOrBlank(columnLabel) ? columnName
						: columnLabel;
			}
		}
		return StringUtils.isNullOrBlank(columnLabel) ? columnName
				: columnLabel;
	}

	public void setJdbc(SimpleJdbcCrud jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public List<CsvTable> getCsvTables() {
		return (new ArrayList<CsvTable>(configStore.values()));
	}

	@Override
	public CsvTable getCsvTable(String key) {
		CsvTable csvtable = configStore.get(key);
		String clzname = csvtable.getClz();
		List<? extends CsvField> ls = csvtable.getCsvFields();
		if (ls == null) {
			logger.debug("尝试从数据库中加载列配置信息");
			try {
				csvtable = loadConfigFromTableInfo(key, clzname);
			} catch (ClassNotFoundException e) {
			}
		}
		return csvtable;
	}

	@Override
	public void putCsvTable(String key, CsvTable element) {
		configStore.put(key, element);
	}

	@Override
	public void remove(String key) {
		configStore.remove(key);
	}

	@Override
	public boolean containsKey(String key) {
		return configStore.containsKey(key);
	}

	/**
	 * 获取可以被导出的列
	 *
	 * @param tableId
	 * @return
	 */
	@Override
	public List<? extends CsvField> getExportedCsvFields(String tableId) {
		List<CsvField> exportedFields = new ArrayList<CsvField>();
		CsvTable table = getCsvTable(tableId);
		Assert.notNull(table, tableId + " 对应的CSV配置信息不存在");
		List<? extends CsvField> fields = table.getCsvFields();
		for (CsvField csvField : fields) {
			if (csvField.isExported()) {
				exportedFields.add(csvField);
			}
		}
		return exportedFields;
	}

	/**
	 * 获取可以被导入的列
	 *
	 * @param tableId
	 * @return
	 */
	@Override
	public List<? extends CsvField> getImportedCsvFields(String tableId) {
		List<CsvField> importedFields = new ArrayList<CsvField>();
		CsvTable table = getCsvTable(tableId);
		Assert.notNull(table, tableId + " 对应的CSV配置信息不存在");
		List<? extends CsvField> fields = table.getCsvFields();
		for (CsvField csvField : fields) {
			if (csvField.isImported()) {
				importedFields.add(csvField);
			}
		}
		return importedFields;
	}

}
