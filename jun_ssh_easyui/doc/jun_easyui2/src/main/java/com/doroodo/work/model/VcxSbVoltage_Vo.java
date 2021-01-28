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
public class VcxSbVoltage_Vo  extends BaseModel implements java.io.Serializable{
	public  final String FORMAT_SAVE_DATE = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	private String id;
	private String avoltage;
	private String bvoltage;
	private String cvoltage;
	private String terminalId;
	private String saveDate;
	private String line;
	private String factory;
	private String protocol;
	private String position;
	private String protocolName;
	private String simCard;
	//columns END

	
	public String getId() {
		return this.id;
	}
	
	public void setId(String value) {
		this.id = value;
	}
	
	public String getAvoltage() {
		return this.avoltage;
	}
	
	public void setAvoltage(String value) {
		this.avoltage = value;
	}
	
	public String getBvoltage() {
		return this.bvoltage;
	}
	
	public void setBvoltage(String value) {
		this.bvoltage = value;
	}
	
	public String getCvoltage() {
		return this.cvoltage;
	}
	
	public void setCvoltage(String value) {
		this.cvoltage = value;
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
	}
	public void setSaveDateString(String value) {
		setSaveDate(value);
	}
	
	public String getSaveDate() {
		return this.saveDate;
	}
	
	public void setSaveDate(String value) {
		this.saveDate = value;
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
	
	public String getProtocol() {
		return this.protocol;
	}
	
	public void setProtocol(String value) {
		this.protocol = value;
	}
	
	public String getPosition() {
		return this.position;
	}
	
	public void setPosition(String value) {
		this.position = value;
	}
	
	public String getProtocolName() {
		return this.protocolName;
	}
	
	public void setProtocolName(String value) {
		this.protocolName = value;
	}
	
	public String getSimCard() {
		return this.simCard;
	}
	
	public void setSimCard(String value) {
		this.simCard = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Avoltage",getAvoltage())
			.append("Bvoltage",getBvoltage())
			.append("Cvoltage",getCvoltage())
			.append("TerminalId",getTerminalId())
			.append("SaveDate",getSaveDate())
			.append("Line",getLine())
			.append("Factory",getFactory())
			.append("Protocol",getProtocol())
			.append("Position",getPosition())
			.append("ProtocolName",getProtocolName())
			.append("SimCard",getSimCard())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof VcxSbVoltage == false) return false;
		if(this == obj) return true;
		VcxSbVoltage other = (VcxSbVoltage)obj;
		return new EqualsBuilder()
			.isEquals();
	}
}

