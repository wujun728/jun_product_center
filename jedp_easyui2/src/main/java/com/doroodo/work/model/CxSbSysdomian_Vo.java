package com.doroodo.work.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.doroodo.base.model.BaseModel;
import com.doroodo.code.util.DateConvertUtils;
public class CxSbSysdomian_Vo  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	private String id;
	private String tableName;
	private String fieldName;
	private String fieldValue;
	private String fieldDesc;
	private String note;
	//columns END


	public void setId(String value) {
		this.id = value;
	}
	
		public String getId() {
		return this.id;
	}
	
	public String getTableName() {
		return this.tableName;
	}
	
	public void setTableName(String value) {
		this.tableName = value;
	}
	
	public String getFieldName() {
		return this.fieldName;
	}
	
	public void setFieldName(String value) {
		this.fieldName = value;
	}
	
	public String getFieldValue() {
		return this.fieldValue;
	}
	
	public void setFieldValue(String value) {
		this.fieldValue = value;
	}
	
	public String getFieldDesc() {
		return this.fieldDesc;
	}
	
	public void setFieldDesc(String value) {
		this.fieldDesc = value;
	}
	
	public String getNote() {
		return this.note;
	}
	
	public void setNote(String value) {
		this.note = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("TableName",getTableName())
			.append("FieldName",getFieldName())
			.append("FieldValue",getFieldValue())
			.append("FieldDesc",getFieldDesc())
			.append("Note",getNote())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CxSbSysdomian == false) return false;
		if(this == obj) return true;
		CxSbSysdomian other = (CxSbSysdomian)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

