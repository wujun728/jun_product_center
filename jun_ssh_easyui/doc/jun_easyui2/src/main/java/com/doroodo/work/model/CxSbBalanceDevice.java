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
@Table(name = "cx_sb_balance_device")
public class CxSbBalanceDevice  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	@Length(max=32)
	private java.lang.String locationLine;
	@Length(max=32)
	private java.lang.String standardLine;
	@Length(max=32)
	private java.lang.String factory;
	@Length(max=32)
	private java.lang.String postion;
	@Length(max=64)
	private java.lang.String note;
	@Length(max=32)
	private java.lang.String address;
	@Length(max=19)
	private java.lang.String sim;
	@Length(max=64)
	private java.lang.String name;
	@Length(max=64)
	private java.lang.String type;
	//columns END


	public CxSbBalanceDevice(){
	}

	public CxSbBalanceDevice(
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
	
	@Column(name = "location_line", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getLocationLine() {
		return this.locationLine;
	}
	
	public void setLocationLine(java.lang.String value) {
		this.locationLine = value;
	}
	
	@Column(name = "standard_line", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getStandardLine() {
		return this.standardLine;
	}
	
	public void setStandardLine(java.lang.String value) {
		this.standardLine = value;
	}
	
	@Column(name = "factory", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getFactory() {
		return this.factory;
	}
	
	public void setFactory(java.lang.String value) {
		this.factory = value;
	}
	
	@Column(name = "postion", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getPostion() {
		return this.postion;
	}
	
	public void setPostion(java.lang.String value) {
		this.postion = value;
	}
	
	@Column(name = "note", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getNote() {
		return this.note;
	}
	
	public void setNote(java.lang.String value) {
		this.note = value;
	}
	
	@Column(name = "address", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	@Column(name = "sim", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.String getSim() {
		return this.sim;
	}
	
	public void setSim(java.lang.String value) {
		this.sim = value;
	}
	
	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	@Column(name = "type", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
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
}

