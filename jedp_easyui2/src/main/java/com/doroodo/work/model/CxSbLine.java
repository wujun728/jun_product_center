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
@Table(name = "cx_sb_line")
public class CxSbLine  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	@Length(max=255)
	private java.lang.String lineNumber;
	@Length(max=255)
	private java.lang.String lineName;
	@Length(max=255)
	private java.lang.String institutions;
	@Length(max=50)
	private java.lang.String voltageGrade;
	//columns END


	public CxSbLine(){
	}

	public CxSbLine(
		java.lang.Integer id
	){
		this.id = id;
	}

	

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "increment")
	@Column(name = "Id", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getId() {
		return this.id;
	}
	
	@Column(name = "line_number", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getLineNumber() {
		return this.lineNumber;
	}
	
	public void setLineNumber(java.lang.String value) {
		this.lineNumber = value;
	}
	
	@Column(name = "line_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getLineName() {
		return this.lineName;
	}
	
	public void setLineName(java.lang.String value) {
		this.lineName = value;
	}
	
	@Column(name = "institutions", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getInstitutions() {
		return this.institutions;
	}
	
	public void setInstitutions(java.lang.String value) {
		this.institutions = value;
	}
	
	@Column(name = "voltage_grade", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getVoltageGrade() {
		return this.voltageGrade;
	}
	
	public void setVoltageGrade(java.lang.String value) {
		this.voltageGrade = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("LineNumber",getLineNumber())
			.append("LineName",getLineName())
			.append("Institutions",getInstitutions())
			.append("VoltageGrade",getVoltageGrade())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CxSbLine == false) return false;
		if(this == obj) return true;
		CxSbLine other = (CxSbLine)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

