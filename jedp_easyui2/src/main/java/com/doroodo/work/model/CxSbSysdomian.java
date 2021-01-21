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
@Entity
@Table(name = "cx_sb_sysdomian")
public class CxSbSysdomian  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	@Length(max=32)
	private java.lang.String tableName;
	@Length(max=32)
	private java.lang.String fieldName;
	@Length(max=32)
	private java.lang.String fieldValue;
	@Length(max=32)
	private java.lang.String fieldDesc;
	@Length(max=32)
	private java.lang.String note;
	//columns END


	public CxSbSysdomian(){
	}

	public CxSbSysdomian(
		java.lang.Integer id
	){
		this.id = id;
	}

	

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "increment")
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getId() {
		return this.id;
	}
	
	@Column(name = "table_name", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getTableName() {
		return this.tableName;
	}
	
	public void setTableName(java.lang.String value) {
		this.tableName = value;
	}
	
	@Column(name = "field_name", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getFieldName() {
		return this.fieldName;
	}
	
	public void setFieldName(java.lang.String value) {
		this.fieldName = value;
	}
	
	@Column(name = "field_value", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getFieldValue() {
		return this.fieldValue;
	}
	
	public void setFieldValue(java.lang.String value) {
		this.fieldValue = value;
	}
	
	@Column(name = "field_desc", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getFieldDesc() {
		return this.fieldDesc;
	}
	
	public void setFieldDesc(java.lang.String value) {
		this.fieldDesc = value;
	}
	
	@Column(name = "note", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getNote() {
		return this.note;
	}
	
	public void setNote(java.lang.String value) {
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

