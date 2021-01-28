package org.myframework.dao.metadata;

/**
 * @ClassName: ColumnInfo
 * @Description: TODO(获取JDBC返回结果集字段描述信息)
 * @author
 * @date 2014年12月30日 下午5:40:12
 * 
 */
public interface ColumnInfo {
	// 字段描述
	public String getColumnLabel();

	// 字段名
	public String getColumnName();
	
	// 字段长度
	public int getColumnSize();

	// 字段数据类型
	public String getColTypeName();

	// 精度
	public int getColPrecision();

	// 是否为空
	public int isNullable();
	
	// 是否主键字段
	public boolean isPk();
	
	// 大小范围
	public int getColScale();

	// 长度
	public int getLength();


	
	public final static String TABLE_CAT = "TABLE_CAT";
	public final static String TABLE_SCHEM = "TABLE_SCHEM";

	public final static String TABLE_NAME = "TABLE_NAME";
	public final static String COLUMN_NAME = "COLUMN_NAME";
	public final static String DATA_TYPE = "DATA_TYPE";
	public final static String TYPE_NAME = "TYPE_NAME";

	public final static String COLUMN_SIZE = "COLUMN_SIZE";
	public final static String BUFFER_LENGTH = "BUFFER_LENGTH";
	public final static String DECIMAL_DIGITS = "DECIMAL_DIGITS";
	public final static String NUM_PREC_RADIX = "NUM_PREC_RADIX";
	public final static String CHAR_OCTET_LENGTH = "CHAR_OCTET_LENGTH";
	
	public final static String NULLABLE = "NULLABLE";
	public final static String REMARKS = "REMARKS";

}
