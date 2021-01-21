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
public class CxSbPoint_Vo  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	private String id;
	private String code;
	private String codeVal;
	private String codeDesc;
	private String codeCal;
	private String ctype;
	private String unit;
	private java.lang.String note;
	//columns END

	public java.lang.String getNote() {
		return note;
	}

	public void setNote(java.lang.String note) {
		this.note = note;
	}

	public void setId(String value) {
		this.id = value;
	}
	
		public String getId() {
		return this.id;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public void setCode(String value) {
		this.code = value;
	}
	
	public String getCodeVal() {
		return this.codeVal;
	}
	
	public void setCodeVal(String value) {
		this.codeVal = value;
	}
	
	public String getCodeDesc() {
		return this.codeDesc;
	}
	
	public void setCodeDesc(String value) {
		this.codeDesc = value;
	}
	
	public String getCodeCal() {
		return this.codeCal;
	}
	
	public void setCodeCal(String value) {
		this.codeCal = value;
	}
	
	public String getCtype() {
		return this.ctype;
	}
	
	public void setCtype(String value) {
		this.ctype = value;
	}
	
	public String getUnit() {
		return this.unit;
	}
	
	public void setUnit(String value) {
		this.unit = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Code",getCode())
			.append("CodeVal",getCodeVal())
			.append("CodeDesc",getCodeDesc())
			.append("CodeCal",getCodeCal())
			.append("CodeType",getCtype())
			.append("Unit",getUnit())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CxSbPoint == false) return false;
		if(this == obj) return true;
		CxSbPoint other = (CxSbPoint)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

