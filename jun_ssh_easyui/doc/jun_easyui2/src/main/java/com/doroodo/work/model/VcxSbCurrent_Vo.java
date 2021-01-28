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
public class VcxSbCurrent_Vo  extends BaseModel implements java.io.Serializable{
	public  final String FORMAT_SAVE_DATE = DATE_FORMAT;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	private String id;
	private String acrrent;
	private String bcrrent;
	private String ccrrent;
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
	
	public String getAcrrent() {
		return this.acrrent;
	}
	
	public void setAcrrent(String value) {
		this.acrrent = value;
	}
	
	public String getBcrrent() {
		return this.bcrrent;
	}
	
	public void setBcrrent(String value) {
		this.bcrrent = value;
	}
	
	public String getCcrrent() {
		return this.ccrrent;
	}
	
	public void setCcrrent(String value) {
		this.ccrrent = value;
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
			.append("Acrrent",getAcrrent())
			.append("Bcrrent",getBcrrent())
			.append("Ccrrent",getCcrrent())
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
		if(obj instanceof VcxSbCurrent == false) return false;
		if(this == obj) return true;
		VcxSbCurrent other = (VcxSbCurrent)obj;
		return new EqualsBuilder()
			.isEquals();
	}
}

