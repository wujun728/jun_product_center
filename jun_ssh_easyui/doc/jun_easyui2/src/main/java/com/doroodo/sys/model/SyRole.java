package com.doroodo.sys.model;

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
@Table(name = "sy_role")
public class SyRole  extends BaseModel implements java.io.Serializable{
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	@NotBlank @Length(max=255)
	private java.lang.String rolename;
	@Length(max=255)
	private java.lang.String roleinfo;
	@NotBlank @Length(max=255)
	private java.lang.String roleid;
	@Length(max=1073741823)
	private java.lang.String operatemap;
	@Length(max=1073741823)
	private java.lang.String operatemapname;
	//columns END


	public SyRole(){
	}

	public SyRole(
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
	
	@Column(name = "rolename", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	public java.lang.String getRolename() {
		return this.rolename;
	}
	
	public void setRolename(java.lang.String value) {
		this.rolename = value;
	}
	
	@Column(name = "roleinfo", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getRoleinfo() {
		return this.roleinfo;
	}
	
	public void setRoleinfo(java.lang.String value) {
		this.roleinfo = value;
	}
	
	@Column(name = "roleid", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	public java.lang.String getRoleid() {
		return this.roleid;
	}
	
	public void setRoleid(java.lang.String value) {
		this.roleid = value;
	}
	
	@Column(name = "operatemap", unique = false, nullable = true, insertable = true, updatable = true, length = 1073741823)
	public java.lang.String getOperatemap() {
		return this.operatemap;
	}
	
	public void setOperatemap(java.lang.String value) {
		this.operatemap = value;
	}
	
	@Column(name = "operatemapname", unique = false, nullable = true, insertable = true, updatable = true, length = 1073741823)
	public java.lang.String getOperatemapname() {
		return this.operatemapname;
	}
	
	public void setOperatemapname(java.lang.String value) {
		this.operatemapname = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Rolename",getRolename())
			.append("Roleinfo",getRoleinfo())
			.append("Roleid",getRoleid())
			.append("Operatemap",getOperatemap())
			.append("Operatemapname",getOperatemapname())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SyRole == false) return false;
		if(this == obj) return true;
		SyRole other = (SyRole)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

