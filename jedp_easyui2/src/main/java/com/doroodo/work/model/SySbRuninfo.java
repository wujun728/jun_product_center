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
@Table(name = "sy_sb_runinfo")
public class SySbRuninfo  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	@NotBlank @Length(max=255)
	private java.lang.String freeMemory;
	@NotBlank @Length(max=255)
	private java.lang.String totalMemory;
	@NotBlank @Length(max=255)
	private java.lang.String maxMemory;
	@Length(max=255)
	private java.lang.String tomcatDir;
	@Length(max=255)
	private java.lang.String dpDir;
	@NotBlank @Length(max=255)
	private java.lang.String insertTime;
	//columns END


	public SySbRuninfo(){
	}

	public SySbRuninfo(
		java.lang.Integer id
	){
		this.id = id;
	}

	

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "identity")
	@Column(name = "Id", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getId() {
		return this.id;
	}
	
	@Column(name = "freeMemory", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	public java.lang.String getFreeMemory() {
		return this.freeMemory;
	}
	
	public void setFreeMemory(java.lang.String value) {
		this.freeMemory = value;
	}
	
	@Column(name = "totalMemory", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	public java.lang.String getTotalMemory() {
		return this.totalMemory;
	}
	
	public void setTotalMemory(java.lang.String value) {
		this.totalMemory = value;
	}
	
	@Column(name = "maxMemory", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	public java.lang.String getMaxMemory() {
		return this.maxMemory;
	}
	
	public void setMaxMemory(java.lang.String value) {
		this.maxMemory = value;
	}
	
	@Column(name = "tomcatDir", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getTomcatDir() {
		return this.tomcatDir;
	}
	
	public void setTomcatDir(java.lang.String value) {
		this.tomcatDir = value;
	}
	
	@Column(name = "dpDir", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getDpDir() {
		return this.dpDir;
	}
	
	public void setDpDir(java.lang.String value) {
		this.dpDir = value;
	}
	
	@Column(name = "insertTime", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	public java.lang.String getInsertTime() {
		return this.insertTime;
	}
	
	public void setInsertTime(java.lang.String value) {
		this.insertTime = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("FreeMemory",getFreeMemory())
			.append("TotalMemory",getTotalMemory())
			.append("MaxMemory",getMaxMemory())
			.append("TomcatDir",getTomcatDir())
			.append("DpDir",getDpDir())
			.append("InsertTime",getInsertTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SySbRuninfo == false) return false;
		if(this == obj) return true;
		SySbRuninfo other = (SySbRuninfo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

