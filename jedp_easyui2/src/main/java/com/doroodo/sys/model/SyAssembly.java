package com.doroodo.sys.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.doroodo.base.model.BaseModel;



@Entity
@Table(name = "sy_assembly")
public class SyAssembly  extends BaseModel implements java.io.Serializable{
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	@Length(max=2000)
	private java.lang.String name;
	@Length(max=2000)
	private java.lang.String sysname;
	@Length(max=65535)
	private java.lang.String getscript;
	@Length(max=65535)
	private java.lang.String setscript;
	@Length(max=2000)
	private java.lang.String classname;
	@Length(max=65535)
	private java.lang.String html;
	@Length(max=65535)
	private java.lang.String propety;
	@Length(max=255)
	private java.lang.String createuserid;
	@Length(max=2000)
	private java.lang.String filename;
	@Length(max=256)
	private java.lang.String createtime;
	//columns END


	public SyAssembly(){
	}

	public SyAssembly(
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
	
	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	@Column(name = "sysname", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public java.lang.String getSysname() {
		return this.sysname;
	}
	
	public void setSysname(java.lang.String value) {
		this.sysname = value;
	}
	
	@Column(name = "getscript", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getGetscript() {
		return this.getscript;
	}
	
	public void setGetscript(java.lang.String value) {
		this.getscript = value;
	}
	
	@Column(name = "setscript", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getSetscript() {
		return this.setscript;
	}
	
	public void setSetscript(java.lang.String value) {
		this.setscript = value;
	}
	
	@Column(name = "classname", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public java.lang.String getClassname() {
		return this.classname;
	}
	
	public void setClassname(java.lang.String value) {
		this.classname = value;
	}
	
	@Column(name = "html", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getHtml() {
		return this.html;
	}
	
	public void setHtml(java.lang.String value) {
		this.html = value;
	}
	
	@Column(name = "propety", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getPropety() {
		return this.propety;
	}
	
	public void setPropety(java.lang.String value) {
		this.propety = value;
	}
	
	@Column(name = "createuserid", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getCreateuserid() {
		return this.createuserid;
	}
	
	public void setCreateuserid(java.lang.String value) {
		this.createuserid = value;
	}
	
	@Column(name = "filename", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public java.lang.String getFilename() {
		return this.filename;
	}
	
	public void setFilename(java.lang.String value) {
		this.filename = value;
	}
	
	@Column(name = "createtime", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public java.lang.String getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.lang.String value) {
		this.createtime = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Sysname",getSysname())
			.append("Getscript",getGetscript())
			.append("Setscript",getSetscript())
			.append("Classname",getClassname())
			.append("Html",getHtml())
			.append("Propety",getPropety())
			.append("Createuserid",getCreateuserid())
			.append("Filename",getFilename())
			.append("Createtime",getCreatetime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SyAssembly == false) return false;
		if(this == obj) return true;
		SyAssembly other = (SyAssembly)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

