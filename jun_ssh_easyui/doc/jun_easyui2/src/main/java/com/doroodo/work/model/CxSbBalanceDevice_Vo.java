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
public class CxSbBalanceDevice_Vo  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	private String id;
	private String locationLine;
	private String standardLine;
	private String factory;
	private String postion;
	private String note;
	private String address;
	private String sim;
	private String name;
	private String type;
	//columns END


	public void setId(String value) {
		this.id = value;
	}
	
		public String getId() {
		return this.id;
	}
	
	public String getLocationLine() {
		return this.locationLine;
	}
	
	public void setLocationLine(String value) {
		this.locationLine = value;
	}
	
	public String getStandardLine() {
		return this.standardLine;
	}
	
	public void setStandardLine(String value) {
		this.standardLine = value;
	}
	
	public String getFactory() {
		return this.factory;
	}
	
	public void setFactory(String value) {
		this.factory = value;
	}
	
	public String getPostion() {
		return this.postion;
	}
	
	public void setPostion(String value) {
		this.postion = value;
	}
	
	public String getNote() {
		return this.note;
	}
	
	public void setNote(String value) {
		this.note = value;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String value) {
		this.address = value;
	}
	
	public String getSim() {
		return this.sim;
	}
	
	public void setSim(String value) {
		this.sim = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("LocationLine",getLocationLine())
			.append("StandardLine",getStandardLine())
			.append("Factory",getFactory())
			.append("Postion",getPostion())
			.append("Note",getNote())
			.append("Address",getAddress())
			.append("Sim",getSim())
			.append("Name",getName())
			.append("Type",getType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CxSbBalanceDevice == false) return false;
		if(this == obj) return true;
		CxSbBalanceDevice other = (CxSbBalanceDevice)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

