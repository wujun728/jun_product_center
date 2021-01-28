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
public class VcxSbTerminal_Vo  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	private String id;
	private String terminalNumber;
	private String position;
	private String circuit;
	private String factory;
	private String state;
	private String simCard;
	private String cmdRate;
	private String protocol;
	private String type;
	private String lineName;
	private String factoryName;
	private String protocolName;
	//columns END

	
	public String getId() {
		return this.id;
	}
	
	public void setId(String value) {
		this.id = value;
	}
	
	public String getTerminalNumber() {
		return this.terminalNumber;
	}
	
	public void setTerminalNumber(String value) {
		this.terminalNumber = value;
	}
	
	public String getPosition() {
		return this.position;
	}
	
	public void setPosition(String value) {
		this.position = value;
	}
	
	public String getCircuit() {
		return this.circuit;
	}
	
	public void setCircuit(String value) {
		this.circuit = value;
	}
	
	public String getFactory() {
		return this.factory;
	}
	
	public void setFactory(String value) {
		this.factory = value;
	}
	
	public String getState() {
		return this.state;
	}
	
	public void setState(String value) {
		this.state = value;
	}
	
	public String getSimCard() {
		return this.simCard;
	}
	
	public void setSimCard(String value) {
		this.simCard = value;
	}
	
	public String getCmdRate() {
		return this.cmdRate;
	}
	
	public void setCmdRate(String value) {
		this.cmdRate = value;
	}
	
	public String getProtocol() {
		return this.protocol;
	}
	
	public void setProtocol(String value) {
		this.protocol = value;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String value) {
		this.type = value;
	}
	
	public String getLineName() {
		return this.lineName;
	}
	
	public void setLineName(String value) {
		this.lineName = value;
	}
	
	public String getFactoryName() {
		return this.factoryName;
	}
	
	public void setFactoryName(String value) {
		this.factoryName = value;
	}
	
	public String getProtocolName() {
		return this.protocolName;
	}
	
	public void setProtocolName(String value) {
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

