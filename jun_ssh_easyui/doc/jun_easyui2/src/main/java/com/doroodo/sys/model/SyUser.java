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
@Table(name = "sy_user")
public class SyUser  extends BaseModel implements java.io.Serializable{
	//alias
	public static final String TABLE_ALIAS = "SyUser";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USERID = "userid";
	public static final String ALIAS_PASSWORD = "password";
	public static final String ALIAS_USERNAME = "username";
	public static final String ALIAS_ROUTEID = "routeid";
	public static final String ALIAS_ROUTENAME = "routename";
	public static final String ALIAS_ORGANID = "organid";
	public static final String ALIAS_ROLENAME = "rolename";
	public static final String ALIAS_THEMENAME = "themename";
	public static final String ALIAS_EMAIL = "email";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	@NotBlank @Length(max=255)
	private java.lang.String userid;
	@NotBlank @Length(max=255)
	private java.lang.String password;
	@NotBlank @Length(max=255)
	private java.lang.String username;
	@Length(max=255)
	private java.lang.String routeid;
	@Length(max=255)
	private java.lang.String routename;
	@NotBlank @Length(max=255)
	private java.lang.String organid;
	@Length(max=255)
	private java.lang.String rolename;
	@Length(max=255)
	private java.lang.String themename;
	@Email @Length(max=255)
	private java.lang.String email;
	//columns END


	public SyUser(){
	}

	public SyUser(
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
	
	@Column(name = "userid", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	public java.lang.String getUserid() {
		return this.userid;
	}
	
	public void setUserid(java.lang.String value) {
		this.userid = value;
	}
	
	@Column(name = "password", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	@Column(name = "username", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	public java.lang.String getUsername() {
		return this.username;
	}
	
	public void setUsername(java.lang.String value) {
		this.username = value;
	}
	
	@Column(name = "routeid", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getRouteid() {
		return this.routeid;
	}
	
	public void setRouteid(java.lang.String value) {
		this.routeid = value;
	}
	
	@Column(name = "routename", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getRoutename() {
		return this.routename;
	}
	
	public void setRoutename(java.lang.String value) {
		this.routename = value;
	}
	
	@Column(name = "organid", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
	public java.lang.String getOrganid() {
		return this.organid;
	}
	
	public void setOrganid(java.lang.String value) {
		this.organid = value;
	}
	
	@Column(name = "rolename", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getRolename() {
		return this.rolename;
	}
	
	public void setRolename(java.lang.String value) {
		this.rolename = value;
	}
	
	@Column(name = "themename", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getThemename() {
		return this.themename;
	}
	
	public void setThemename(java.lang.String value) {
		this.themename = value;
	}
	
	@Column(name = "email", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Userid",getUserid())
			.append("Password",getPassword())
			.append("Username",getUsername())
			.append("Routeid",getRouteid())
			.append("Routename",getRoutename())
			.append("Organid",getOrganid())
			.append("Rolename",getRolename())
			.append("Themename",getThemename())
			.append("Email",getEmail())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SyUser == false) return false;
		if(this == obj) return true;
		SyUser other = (SyUser)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

