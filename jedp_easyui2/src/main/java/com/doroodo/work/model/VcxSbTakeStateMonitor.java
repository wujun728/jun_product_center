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
@Table(name = "v_cx_sb_take_state_monitor")
public class VcxSbTakeStateMonitor  extends BaseModel implements java.io.Serializable{
	public  final String FORMAT_SAVE_DATE = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	private java.lang.Integer id;
	@Length(max=255)
	private java.lang.String proState;
	@Length(max=32)
	private java.lang.String proStateDesc;
	@Length(max=32)
	private java.lang.String note;
	@Length(max=255)
	private java.lang.String faultCurrent;
	@Length(max=255)
	private java.lang.String leakageValue;
	@Length(max=255)
	private java.lang.String overValue;
	@Length(max=255)
	private java.lang.String sign;
	
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
	private java.lang.Integer lineId;
	@Length(max=255)
	private java.lang.String lineName;
	@Length(max=255)
	private java.lang.String org;
	//columns END


	public VcxSbTakeStateMonitor(){
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
	
	@Column(name = "pro_state", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getProState() {
		return this.proState;
	}
	
	public void setProState(java.lang.String value) {
		this.proState = value;
	}
	
	@Column(name = "pro_state_desc", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getProStateDesc() {
		return this.proStateDesc;
	}
	
	public void setProStateDesc(java.lang.String value) {
		this.proStateDesc = value;
	}
	
	@Column(name = "note", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getNote() {
		return this.note;
	}
	
	public void setNote(java.lang.String value) {
		this.note = value;
	}
	
	@Column(name = "fault_current", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getFaultCurrent() {
		return this.faultCurrent;
	}
	
	public void setFaultCurrent(java.lang.String value) {
		this.faultCurrent = value;
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
	
	@Column(name = "sign", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getSign() {
		return this.sign;
	}
	
	public void setSign(java.lang.String value) {
		this.sign = value;
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
	
	@Column(name = "line_id", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getLineId() {
		return this.lineId;
	}
	
	public void setLineId(java.lang.Integer value) {
		this.lineId = value;
	}
	
	@Column(name = "line_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getLineName() {
		return this.lineName;
	}
	
	public void setLineName(java.lang.String value) {
		this.lineName = value;
	}
	
	@Column(name = "org", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getOrg() {
		return this.org;
	}
	
	public void setOrg(java.lang.String value) {
		this.org = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ProState",getProState())
			.append("ProStateDesc",getProStateDesc())
			.append("Note",getNote())
			.append("FaultCurrent",getFaultCurrent())
			.append("LeakageValue",getLeakageValue())
			.append("OverValue",getOverValue())
			.append("Sign",getSign())
			.append("SaveDate",getSaveDate())
			.append("TerminalId",getTerminalId())
			.append("TerminalNumber",getTerminalNumber())
			.append("Position",getPosition())
			.append("Circuit",getCircuit())
			.append("Name",getName())
			.append("State",getState())
			.append("LineId",getLineId())
			.append("LineName",getLineName())
			.append("Org",getOrg())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof VcxSbTakeStateMonitor == false) return false;
		if(this == obj) return true;
		VcxSbTakeStateMonitor other = (VcxSbTakeStateMonitor)obj;
		return new EqualsBuilder()
			.isEquals();
	}
}

