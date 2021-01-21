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
public class CxSbReadStatus_Vo  extends BaseModel implements java.io.Serializable{
	public  final String FORMAT_SAVE_DATE = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	private String id;
	private String leakageCurrent;
	private String acurrent;
	private String bcurrent;
	private String ccurrent;
	private String terminalId;
	private String saveDate;
	//columns END


	public void setId(String value) {
		this.id = value;
	}
	
		public String getId() {
		return this.id;
	}
	
	public String getLeakageCurrent() {
		return this.leakageCurrent;
	}
	
	public void setLeakageCurrent(String value) {
		this.leakageCurrent = value;
	}
	
	public String getAcurrent() {
		return this.acurrent;
	}
	
	public void setAcurrent(String value) {
		this.acurrent = value;
	}
	
	public String getBcurrent() {
		return this.bcurrent;
	}
	
	public void setBcurrent(String value) {
		this.bcurrent = value;
	}
	
	public String getCcurrent() {
		return this.ccurrent;
	}
	
	public void setCcurrent(String value) {
		this.ccurrent = value;
	}
	
	public String getTerminalId() {
		return this.terminalId;
	}
	
	public void setTerminalId(String value) {
		this.terminalId = value;
	}
	
	@Transient
	public String getSaveDateString() {
		return getSaveDate();
		//return DateConvertUtils.format(getSaveDate(), FORMAT_SAVE_DATE);
	}
	public void setSaveDateString(String value) {
		setSaveDate(value);
		//setSaveDate(DateConvertUtils.parse(value, FORMAT_SAVE_DATE,String.class));
	}
	
	public String getSaveDate() {
		return this.saveDate;
	}
	
	public void setSaveDate(String value) {
		this.saveDate = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("LeakageCurrent",getLeakageCurrent())
			.append("Acurrent",getAcurrent())
			.append("Bcurrent",getBcurrent())
			.append("Ccurrent",getCcurrent())
			.append("TerminalId",getTerminalId())
			.append("SaveDate",getSaveDate())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CxSbReadStatus == false) return false;
		if(this == obj) return true;
		CxSbReadStatus other = (CxSbReadStatus)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

