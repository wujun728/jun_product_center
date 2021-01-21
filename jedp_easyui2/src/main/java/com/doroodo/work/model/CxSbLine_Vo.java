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
public class CxSbLine_Vo  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	private String id;
	private String lineNumber;
	private String lineName;
	private String institutions;
	private String voltageGrade;
	//columns END


	public void setId(String value) {
		this.id = value;
	}
	
		public String getId() {
		return this.id;
	}
	
	public String getLineNumber() {
		return this.lineNumber;
	}
	
	public void setLineNumber(String value) {
		this.lineNumber = value;
	}
	
	public String getLineName() {
		return this.lineName;
	}
	
	public void setLineName(String value) {
		this.lineName = value;
	}
	
	public String getInstitutions() {
		return this.institutions;
	}
	
	public void setInstitutions(String value) {
		this.institutions = value;
	}
	
	public String getVoltageGrade() {
		return this.voltageGrade;
	}
	
	public void setVoltageGrade(String value) {
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

