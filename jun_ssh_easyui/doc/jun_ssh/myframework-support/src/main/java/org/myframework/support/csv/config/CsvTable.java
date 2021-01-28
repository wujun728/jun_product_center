package org.myframework.support.csv.config;

import java.util.List;

/**
 *
 * <ol>
 * 映射CSV与表格之间的对应关系
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
public interface CsvTable {

	public final static String EMAIL = "email";
	public final static String URL = "url";

	public final static String DATE = "date";
	public final static String DATE_TIME = "datetime";
	public final static String NUMBER = "number";
	public final static String STRING = "string";

	
	public final static String CUSTOM = "custom";

	public final static String DATE_FORMAT = "yyyy-MM-dd";
	public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public final static String UUID_VALUE = "uuid";
	
	public final static String TODAY_VALUE = "today";
	
	public final static String NOW_VALUE = "now";
	
	public final static String CREATOR_ID = "creator";

	String getName();

	@Deprecated
	String getTableType();

	String getClz();

	/**
	 * 导入必须的配置
	 *
	 * @return
	 */
	String getSqlKey();

	/**
	 * 是否带表头信息
	 *
	 * @return
	 */
	boolean getHeaderEnabled();
	
	/**
	 * 行级数据检查
	 * @return
	 */
	String getRowFilter();

	/**
	 * 返回表格字段信息
	 *
	 * @return
	 */
	List<? extends CsvField> getCsvFields();

	/**
	 * 表格字段信息，包含名称、类型、格式化、导入字段验证信息（最大值、最小值、最大长度、最小长度、正则验证、是否必须）
	 * <ol>
	 * <li>{@link  }</li>
	 *
	 * </ol>
	 *
	 * @see
	 * @author Wujun
	 * @since 1.0
	 * @2015年6月24日
	 *
	 */
	public static interface CsvField {

		boolean isExported();

		boolean isImported();

		boolean isRequired();

		String getName();

		String getType();

		String getFormat();

		String getDesc();
		
		String getDefValue();

		String getMaxLength();

		String getMinLength();

		String getRegexp();

		String getValidator();

		String getMax();

		String getMin();
	}

}
