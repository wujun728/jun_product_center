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
@Table(name = "cx_sb_terminal_config")
public class CxSbTerminalConfig  extends BaseModel implements java.io.Serializable{
	public  final String FORMAT_CONFIG_DATE = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	
	private java.util.Date configDate;
	@Length(max=32)
	private java.lang.String leakageCurrent;
	@Length(max=32)
	private java.lang.String overCurrent;
	@Length(max=32)
	private java.lang.String handleTime;
	@Length(max=32)
	private java.lang.String postion;
	@Length(max=32)
	private java.lang.String line;
	@Length(max=32)
	private java.lang.String factory;
	@Length(max=32)
	private java.lang.String address;
	//columns END


	public CxSbTerminalConfig(){
	}

	public CxSbTerminalConfig(
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
	
	@Transient
	public String getConfigDateString() {
		return DateConvertUtils.format(getConfigDate(), FORMAT_CONFIG_DATE);
	}
	public void setConfigDateString(String value) {
		setConfigDate(DateConvertUtils.parse(value, FORMAT_CONFIG_DATE,java.util.Date.class));
	}
	
	@Column(name = "config_date", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getConfigDate() {
		return this.configDate;
	}
	
	public void setConfigDate(java.util.Date value) {
		this.configDate = value;
	}
	
	@Column(name = "leakage_current", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getLeakageCurrent() {
		return this.leakageCurrent;
	}
	
	public void setLeakageCurrent(java.lang.String value) {
		this.leakageCurrent = value;
	}
	
	@Column(name = "over_current", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getOverCurrent() {
		return this.overCurrent;
	}
	
	public void setOverCurrent(java.lang.String value) {
		this.overCurrent = value;
	}
	
	@Column(name = "handle_time", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getHandleTime() {
		return this.handleTime;
	}
	
	public void setHandleTime(java.lang.String value) {
		this.handleTime = value;
	}
	
	@Column(name = "postion", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getPostion() {
		return this.postion;
	}
	
	public void setPostion(java.lang.String value) {
		this.postion = value;
	}
	
	@Column(name = "line", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getLine() {
		return this.line;
	}
	
	public void setLine(java.lang.String value) {
		this.line = value;
	}
	
	@Column(name = "factory", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getFactory() {
		return this.factory;
	}
	
	public void setFactory(java.lang.String value) {
		this.factory = value;
	}
	
	@Column(name = "address", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
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

