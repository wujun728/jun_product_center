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
@Table(name = "cx_sb_read_status")
public class CxSbReadStatus  extends BaseModel implements java.io.Serializable{
	public  final String FORMAT_SAVE_DATE = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	@Length(max=255)
	private java.lang.String leakageCurrent;
	@Length(max=255)
	private java.lang.String acurrent;
	@Length(max=255)
	private java.lang.String bcurrent;
	@Length(max=255)
	private java.lang.String ccurrent;
	@Length(max=32)
	private java.lang.String terminalId;
	
	private java.util.Date saveDate;
	//columns END


	public CxSbReadStatus(){
	}

	public CxSbReadStatus(
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
	
	@Column(name = "leakage_current", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getLeakageCurrent() {
		return this.leakageCurrent;
	}
	
	public void setLeakageCurrent(java.lang.String value) {
		this.leakageCurrent = value;
	}
	
	@Column(name = "A_current", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getAcurrent() {
		return this.acurrent;
	}
	
	public void setAcurrent(java.lang.String value) {
		this.acurrent = value;
	}
	
	@Column(name = "B_current", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getBcurrent() {
		return this.bcurrent;
	}
	
	public void setBcurrent(java.lang.String value) {
		this.bcurrent = value;
	}
	
	@Column(name = "C_current", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getCcurrent() {
		return this.ccurrent;
	}
	
	public void setCcurrent(java.lang.String value) {
		this.ccurrent = value;
	}
	
	@Column(name = "terminal_id", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getTerminalId() {
		return this.terminalId;
	}
	
	public void setTerminalId(java.lang.String value) {
		this.terminalId = value;
	}
	
	@Transient
	public String getSaveDateString() {
		return DateConvertUtils.format(getSaveDate(), FORMAT_SAVE_DATE);
	}
	public void setSaveDateString(String value) {
		setSaveDate(DateConvertUtils.parse(value, FORMAT_SAVE_DATE,java.util.Date.class));
	}
	
	@Column(name = "save_date", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getSaveDate() {
		return this.saveDate;
	}
	
	public void setSaveDate(java.util.Date value) {
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

