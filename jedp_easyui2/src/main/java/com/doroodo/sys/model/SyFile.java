package com.doroodo.sys.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;



import com.doroodo.code.util.DateConvertUtils;
@Entity
@Table(name = "sy_file")
public class SyFile implements java.io.Serializable{
	//alias
	public static final String TABLE_ALIAS = "SyFile";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_FILEID = "fileid";
	public static final String ALIAS_FILENAME = "filename";
	public static final String ALIAS_SYSNAME = "sysname";
	public static final String ALIAS_USERID = "userid";
	public static final String ALIAS_CREATETIME = "createtime";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	@Length(max=255)
	private java.lang.String fileid;
	@Length(max=2000)
	private java.lang.String filename;
	@Length(max=255)
	private java.lang.String sysname;
	@Length(max=255)
	private java.lang.String userid;
	@Length(max=255)
	private java.lang.String createtime;
	//columns END


	public SyFile(){
	}

	public SyFile(
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
	
	@Column(name = "fileid", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getFileid() {
		return this.fileid;
	}
	
	public void setFileid(java.lang.String value) {
		this.fileid = value;
	}
	
	@Column(name = "filename", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public java.lang.String getFilename() {
		return this.filename;
	}
	
	public void setFilename(java.lang.String value) {
		this.filename = value;
	}
	
	@Column(name = "sysname", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getSysname() {
		return this.sysname;
	}
	
	public void setSysname(java.lang.String value) {
		this.sysname = value;
	}
	
	@Column(name = "userid", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getUserid() {
		return this.userid;
	}
	
	public void setUserid(java.lang.String value) {
		this.userid = value;
	}
	
	@Column(name = "createtime", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.lang.String value) {
		this.createtime = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Fileid",getFileid())
			.append("Filename",getFilename())
			.append("Sysname",getSysname())
			.append("Userid",getUserid())
			.append("Createtime",getCreatetime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SyFile == false) return false;
		if(this == obj) return true;
		SyFile other = (SyFile)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

