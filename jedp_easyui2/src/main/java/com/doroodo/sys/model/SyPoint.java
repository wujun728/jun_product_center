package com.doroodo.sys.model;

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
@Table(name = "sy_point")
public class SyPoint  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	@Length(max=32)
	private java.lang.String code;
	@Length(max=32)
	private java.lang.String codeVal;
	@Length(max=32)
	private java.lang.String codeDesc;
	@Length(max=65535)
	private java.lang.String codeCal;
	@Length(max=8)
	private java.lang.String ctype;
	@Length(max=32)
	private java.lang.String unit;
	@Length(max=65535)
	private java.lang.String note;
	//columns END


	public SyPoint(){
	}

	public SyPoint(
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
	
	@Column(name = "code", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getCode() {
		return this.code;
	}
	
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	
	@Column(name = "code_val", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getCodeVal() {
		return this.codeVal;
	}
	
	public void setCodeVal(java.lang.String value) {
		this.codeVal = value;
	}
	
	@Column(name = "code_desc", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getCodeDesc() {
		return this.codeDesc;
	}
	
	public void setCodeDesc(java.lang.String value) {
		this.codeDesc = value;
	}
	
	@Column(name = "code_cal", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getCodeCal() {
		return this.codeCal;
	}
	
	public void setCodeCal(java.lang.String value) {
		this.codeCal = value;
	}
	
	@Column(name = "ctype", unique = false, nullable = true, insertable = true, updatable = true, length = 8)
	public java.lang.String getCtype() {
		return this.ctype;
	}
	
	public void setCtype(java.lang.String value) {
		this.ctype = value;
	}
	
	@Column(name = "unit", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getUnit() {
		return this.unit;
	}
	
	public void setUnit(java.lang.String value) {
		this.unit = value;
	}
	
	@Column(name = "note", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getNote() {
		return this.note;
	}
	
	public void setNote(java.lang.String value) {
		this.note = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Code",getCode())
			.append("CodeVal",getCodeVal())
			.append("CodeDesc",getCodeDesc())
			.append("CodeCal",getCodeCal())
			.append("Ctype",getCtype())
			.append("Unit",getUnit())
			.append("Note",getNote())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SyPoint == false) return false;
		if(this == obj) return true;
		SyPoint other = (SyPoint)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

