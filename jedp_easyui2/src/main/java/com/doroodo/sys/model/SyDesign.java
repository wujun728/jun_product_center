/*

 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2013
 */

package com.doroodo.sys.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import org.hibernate.validator.constraints.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;



@Entity
@Table(name = "sy_design")
public class SyDesign implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "SyDesign";
	public static final String ALIAS_ID = "title:'编号'^hidden:true";
	public static final String ALIAS_TABLENAME = "title:'表名'";
	public static final String ALIAS_DESIGNCONTENT = "title:'编辑内容'";
	public static final String ALIAS_EDITERNAME = "title:'贡献者'^form:{hidden:true}";
	public static final String ALIAS_EDITTIME = "title:'编辑时间'^form:{hidden:true}";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	@NotBlank @Length(max=255)
	private java.lang.String tablename;
	@Length(max=255)
	private java.lang.String designcontent;
	@NotBlank @Length(max=255)
	private java.lang.String editername;
	@NotBlank @Length(max=255)
	private java.lang.String edittime;
	//columns END


	public SyDesign(){
	}

	public SyDesign(
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
	
	@Column(name = "tablename", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	public java.lang.String getTablename() {
		return this.tablename;
	}
	
	public void setTablename(java.lang.String value) {
		this.tablename = value;
	}
	
	@Column(name = "designcontent", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getDesigncontent() {
		return this.designcontent;
	}
	
	public void setDesigncontent(java.lang.String value) {
		this.designcontent = value;
	}
	
	@Column(name = "editername", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	public java.lang.String getEditername() {
		return this.editername;
	}
	
	public void setEditername(java.lang.String value) {
		this.editername = value;
	}
	
	@Column(name = "edittime", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	public java.lang.String getEdittime() {
		return this.edittime;
	}
	
	public void setEdittime(java.lang.String value) {
		this.edittime = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Tablename",getTablename())
			.append("Designcontent",getDesigncontent())
			.append("Editername",getEditername())
			.append("Edittime",getEdittime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SyDesign == false) return false;
		if(this == obj) return true;
		SyDesign other = (SyDesign)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

