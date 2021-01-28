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
@Table(name = "sy_chart")
public class SyChart  extends BaseModel implements java.io.Serializable{
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	@Length(max=2000)
	private java.lang.String title;
	@Length(max=2000)
	private java.lang.String sysname;
	@Length(max=2000)
	private java.lang.String createuser;
	@Length(max=256)
	private java.lang.String createtime;
	@Length(max=500)
	private java.lang.String url;
	//columns END


	public SyChart(){
	}

	public SyChart(
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
	
	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	@Column(name = "sysname", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public java.lang.String getSysname() {
		return this.sysname;
	}
	
	public void setSysname(java.lang.String value) {
		this.sysname = value;
	}
	
	@Column(name = "createuser", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public java.lang.String getCreateuser() {
		return this.createuser;
	}
	
	public void setCreateuser(java.lang.String value) {
		this.createuser = value;
	}
	
	@Column(name = "createtime", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public java.lang.String getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.lang.String value) {
		this.createtime = value;
	}
	
	@Column(name = "url", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public java.lang.String getUrl() {
		return this.url;
	}
	
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Title",getTitle())
			.append("Sysname",getSysname())
			.append("Createuser",getCreateuser())
			.append("Createtime",getCreatetime())
			.append("Url",getUrl())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SyChart == false) return false;
		if(this == obj) return true;
		SyChart other = (SyChart)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

