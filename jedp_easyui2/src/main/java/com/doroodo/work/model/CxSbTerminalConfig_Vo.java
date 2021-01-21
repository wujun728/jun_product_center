package com.doroodo.work.model;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.doroodo.base.model.BaseModel;
import com.doroodo.code.util.DateConvertUtils;
public class CxSbTerminalConfig_Vo  extends BaseModel implements java.io.Serializable{
	public  final String FORMAT_CONFIG_DATE = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	private String id;
	private Date configDate;
	private String leakageCurrent;
	private String overCurrent;
	private String handleTime;
	private String postion;
	private String line;
	private String factory;
	private String address;
	//columns END


	public void setId(String value) {
		this.id = value;
	}
	
		public String getId() {
		return this.id;
	}
	
	@Transient
	public String getConfigDateString() {
		return DateConvertUtils.format(getConfigDate(), FORMAT_CONFIG_DATE);
	}
	public void setConfigDateString(String value) {
		setConfigDate(DateConvertUtils.parse(value, FORMAT_CONFIG_DATE,java.util.Date.class));
	}
	
	public Date getConfigDate() {
		return this.configDate;
	}
	
	public void setConfigDate(Date value) {
		this.configDate = value;
	}
	
	public String getLeakageCurrent() {
		return this.leakageCurrent;
	}
	
	public void setLeakageCurrent(String value) {
		this.leakageCurrent = value;
	}
	
	public String getOverCurrent() {
		return this.overCurrent;
	}
	
	public void setOverCurrent(String value) {
		this.overCurrent = value;
	}
	
	public String getHandleTime() {
		return this.handleTime;
	}
	
	public void setHandleTime(String value) {
		this.handleTime = value;
	}
	
	public String getPostion() {
		return this.postion;
	}
	
	public void setPostion(String value) {
		this.postion = value;
	}
	
	public String getLine() {
		return this.line;
	}
	
	public void setLine(String value) {
		this.line = value;
	}
	
	public String getFactory() {
		return this.factory;
	}
	
	public void setFactory(String value) {
		this.factory = value;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String value) {
		this.address = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ConfigDate",getConfigDate())
			.append("LeakageCurrent",getLeakageCurrent())
			.append("OverCurrent",getOverCurrent())
			.append("HandleTime",getHandleTime())
			.append("Postion",getPostion())
			.append("Line",getLine())
			.append("Factory",getFactory())
			.append("Address",getAddress())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CxSbTerminalConfig == false) return false;
		if(this == obj) return true;
		CxSbTerminalConfig other = (CxSbTerminalConfig)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

