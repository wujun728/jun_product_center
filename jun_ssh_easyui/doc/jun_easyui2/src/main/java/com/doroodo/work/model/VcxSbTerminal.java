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
@Table(name = "v_cx_sb_terminal")
public class VcxSbTerminal  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	private java.lang.Integer id;
	@Length(max=255)
	private java.lang.String terminalNumber;
	@Length(max=50)
	private java.lang.String position;
	@Length(max=20)
	private java.lang.String circuit;
	@Length(max=20)
	private java.lang.String factory;
	@Length(max=255)
	private java.lang.String state;
	@Length(max=255)
	private java.lang.String simCard;
	
	private java.lang.Integer cmdRate;
	@Length(max=32)
	private java.lang.String protocol;
	@Length(max=32)
	private java.lang.String type;
	@Length(max=255)
	private java.lang.String lineName;
	@Length(max=32)
	private java.lang.String factoryName;
	@Length(max=32)
	private java.lang.String protocolName;
	//columns END


	public VcxSbTerminal(){
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
	
	@Column(name = "factory", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getFactory() {
		return this.factory;
	}
	
	public void setFactory(java.lang.String value) {
		this.factory = value;
	}
	
	@Column(name = "state", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	
	@Column(name = "sim_card", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getSimCard() {
		return this.simCard;
	}
	
	public void setSimCard(java.lang.String value) {
		this.simCard = value;
	}
	
	@Column(name = "cmd_rate", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCmdRate() {
		return this.cmdRate;
	}
	
	public void setCmdRate(java.lang.Integer value) {
		this.cmdRate = value;
	}
	
	@Column(name = "protocol", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getProtocol() {
		return this.protocol;
	}
	
	public void setProtocol(java.lang.String value) {
		this.protocol = value;
	}
	
	@Column(name = "type", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
	}
	
	@Column(name = "line_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getLineName() {
		return this.lineName;
	}
	
	public void setLineName(java.lang.String value) {
		this.lineName = value;
	}
	
	@Column(name = "factory_name", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getFactoryName() {
		return this.factoryName;
	}
	
	public void setFactoryName(java.lang.String value) {
		this.factoryName = value;
	}
	
	@Column(name = "protocol_name", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getProtocolName() {
		return this.protocolName;
	}
	
	public void setProtocolName(java.lang.String value) {
		this.protocolName = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("TerminalNumber",getTerminalNumber())
			.append("Position",getPosition())
			.append("Circuit",getCircuit())
			.append("Factory",getFactory())
			.append("State",getState())
			.append("SimCard",getSimCard())
			.append("CmdRate",getCmdRate())
			.append("Protocol",getProtocol())
			.append("Type",getType())
			.append("LineName",getLineName())
			.append("FactoryName",getFactoryName())
			.append("ProtocolName",getProtocolName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof VcxSbTerminal == false) return false;
		if(this == obj) return true;
		VcxSbTerminal other = (VcxSbTerminal)obj;
		return new EqualsBuilder()
			.isEquals();
	}
}

