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
public class SyPoint_Vo  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	private String id;
	private String code;
	private String codeVal;
	private String codeDesc;
	private String codeCal;
	private String ctype;
	private String unit;
	private String note;
	//columns END


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
	
	public String getNote() {
		return this.note;
	}
	
	public void setNote(String value) {
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

