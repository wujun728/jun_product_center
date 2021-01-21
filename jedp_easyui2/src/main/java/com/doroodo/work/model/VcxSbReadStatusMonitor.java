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
@Table(name = "v_cx_sb_read_status_monitor")
public class VcxSbReadStatusMonitor  extends BaseModel implements java.io.Serializable{
	public  final String FORMAT_SAVE_DATE = DATE_TIME_FORMAT;
	

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
	
	private java.util.Date saveDate;
	
	private java.lang.Integer terminalId;
	@Length(max=255)
	private java.lang.String terminalNumber;
	@Length(max=50)
	private java.lang.String position;
	@Length(max=20)
	private java.lang.String circuit;
	@Length(max=32)
	private java.lang.String name;
	@Length(max=255)
	private java.lang.String state;
	//columns END


	public VcxSbReadStatusMonitor(){
	}


	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "increment")
	@Column(name = "id", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
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
	
	@Column(name = "terminal_id", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getTerminalId() {
		return this.terminalId;
	}
	
	public void setTerminalId(java.lang.Integer value) {
		this.terminalId = value;
	}
	
	@Column(name = "terminal_number", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getTerminalNumber() {
		return this.terminalNumber;
	}
	
	public void setTerminalNumber(java.lang.String value) {
		this.terminalNumber = value;
	}
	
	@Column(name = "position", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getPosition() {
		return this.position;
	}
	
	public void setPosition(java.lang.String value) {
		this.position = value;
	}
	
	@Column(name = "circuit", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getCircuit() {
		return this.circuit;
	}
	
	public void setCircuit(java.lang.String value) {
		this.circuit = value;
	}
	
	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	@Column(name = "state", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("LeakageCurrent",getLeakageCurrent())
			.append("Acurrent",getAcurrent())
			.append("Bcurrent",getBcurrent())
			.append("Ccurrent",getCcurrent())
			.append("SaveDate",getSaveDate())
			.append("TerminalId",getTerminalId())
			.append("TerminalNumber",getTerminalNumber())
			.append("Position",getPosition())
			.append("Circuit",getCircuit())
			.append("Name",getName())
			.append("State",getState())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof VcxSbReadStatusMonitor == false) return false;
		if(this == obj) return true;
		VcxSbReadStatusMonitor other = (VcxSbReadStatusMonitor)obj;
		return new EqualsBuilder()
			.isEquals();
	}
}

