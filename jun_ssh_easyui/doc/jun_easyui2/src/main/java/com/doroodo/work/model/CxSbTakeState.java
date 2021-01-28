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
@Table(name = "cx_sb_take_state")
public class CxSbTakeState  extends BaseModel implements java.io.Serializable{
	public  final String FORMAT_SAVE_DATE = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	@Length(max=255)
	private java.lang.String proState;
	@Length(max=255)
	private java.lang.String leakageValue;
	@Length(max=255)
	private java.lang.String overValue;
	@Length(max=255)
	private java.lang.String faultCurrent;
	@Length(max=255)
	private java.lang.String sign;
	@Length(max=32)
	private java.lang.String terminalId;
	
	private java.util.Date saveDate;
	//columns END


	public CxSbTakeState(){
	}

	public CxSbTakeState(
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
	
	@Column(name = "pro_state", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getProState() {
		return this.proState;
	}
	
	public void setProState(java.lang.String value) {
		this.proState = value;
	}
	
	@Column(name = "leakage_value", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getLeakageValue() {
		return this.leakageValue;
	}
	
	public void setLeakageValue(java.lang.String value) {
		this.leakageValue = value;
	}
	
	@Column(name = "over_value", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getOverValue() {
		return this.overValue;
	}
	
	public void setOverValue(java.lang.String value) {
		this.overValue = value;
	}
	
	@Column(name = "fault_current", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getFaultCurrent() {
		return this.faultCurrent;
	}
	
	public void setFaultCurrent(java.lang.String value) {
		this.faultCurrent = value;
	}
	
	@Column(name = "sign", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getSign() {
		return this.sign;
	}
	
	public void setSign(java.lang.String value) {
		this.sign = value;
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
			.append("ProState",getProState())
			.append("LeakageValue",getLeakageValue())
			.append("OverValue",getOverValue())
			.append("FaultCurrent",getFaultCurrent())
			.append("Sign",getSign())
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
		if(obj instanceof CxSbTakeState == false) return false;
		if(this == obj) return true;
		CxSbTakeState other = (CxSbTakeState)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

