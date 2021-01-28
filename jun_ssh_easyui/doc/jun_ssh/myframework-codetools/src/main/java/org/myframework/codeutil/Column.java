package org.myframework.codeutil;

import org.myframework.util.StringUtil;

public class Column {

	private String javaName;

	private String name;

	private String dataType ;

	private String nullable ="false";

	private String length="10";

	private String precision="10";

	private String scale = "0";

	private String comments="";

	private String columnKey="";

	private String unique;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

	public String getName() {
		return name;
	}

	public String getSetterMethod() {
		return "set"+StringUtil.firstCharUpperCase(javaName) ;
	}

	public String getGetterMethod() {
		return "get"+StringUtil.firstCharUpperCase(javaName) ;
	}

	public void setName(String name) {
		this.name = name;
		this.javaName = StringUtil.toBeanPatternStr(name);
	}

	public String getUnique() {
		return unique;
	}

	public void setUnique(String unique) {
		this.unique = unique;
	}

	public String getNullable() {
		return nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getJavaName() {
		return javaName;
	}

	public void setJavaName(String javaName) {
		this.javaName = javaName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Override
	public String toString() {
		return "Column [javaName=" + javaName + ", name=" + name
				+ ", dataType=" + dataType + ", unique=" + unique
				+ ", nullable=" + nullable + ", length=" + length
				+ ", precision=" + precision + ", scale=" + scale
				+ ", comments=" + comments + ", columnKey=" + columnKey + "]";
	}

}
