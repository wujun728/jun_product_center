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
@Table(name = "v_cx_sb_balance_data")
public class VcxSbBalanceData  extends BaseModel implements java.io.Serializable{
	public  final String FORMAT_SAVE_DATE = DATE_FORMAT;
	

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
	@Length(max=255)
	private java.lang.String locationLineName;
	@Length(max=255)
	private java.lang.String org;
	@Length(max=32)
	private java.lang.String factoryName;
	@Length(max=255)
	private java.lang.String standardLineName;
	
	private java.lang.Double standard;
	
	private java.lang.Double a;
	
	private java.lang.Double b;
	
	private java.lang.Double c;
	
	private java.util.Date saveDate;
	//columns END


	public VcxSbBalanceData(){
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
	
	@Column(name = "location_line_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getLocationLineName() {
		return this.locationLineName;
	}
	
	public void setLocationLineName(java.lang.String value) {
		this.locationLineName = value;
	}
	
	@Column(name = "org", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getOrg() {
		return this.org;
	}
	
	public void setOrg(java.lang.String value) {
		this.org = value;
	}
	
	@Column(name = "factory_name", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getFactoryName() {
		return this.factoryName;
	}
	
	public void setFactoryName(java.lang.String value) {
		this.factoryName = value;
	}
	
	@Column(name = "standard_line_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getStandardLineName() {
		return this.standardLineName;
	}
	
	public void setStandardLineName(java.lang.String value) {
		this.standardLineName = value;
	}
	
	@Column(name = "standard", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	public java.lang.Double getStandard() {
		return this.standard;
	}
	
	public void setStandard(java.lang.Double value) {
		this.standard = value;
	}
	
	@Column(name = "a", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	public java.lang.Double getA() {
		return this.a;
	}
	
	public void setA(java.lang.Double value) {
		this.a = value;
	}
	
	@Column(name = "b", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	public java.lang.Double getB() {
		return this.b;
	}
	
	public void setB(java.lang.Double value) {
		this.b = value;
	}
	
	@Column(name = "c", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	public java.lang.Double getC() {
		return this.c;
	}
	
	public void setC(java.lang.Double value) {
		this.c = value;
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
			.append("LocationLine",getLocationLine())
			.append("StandardLine",getStandardLine())
			.append("Factory",getFactory())
			.append("Postion",getPostion())
			.append("Note",getNote())
			.append("Address",getAddress())
			.append("Sim",getSim())
			.append("Name",getName())
			.append("Type",getType())
			.append("LocationLineName",getLocationLineName())
			.append("Org",getOrg())
			.append("FactoryName",getFactoryName())
			.append("StandardLineName",getStandardLineName())
			.append("Standard",getStandard())
			.append("A",getA())
			.append("B",getB())
			.append("C",getC())
			.append("SaveDate",getSaveDate())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof VcxSbBalanceData == false) return false;
		if(this == obj) return true;
		VcxSbBalanceData other = (VcxSbBalanceData)obj;
		return new EqualsBuilder()
			.isEquals();
	}
}

