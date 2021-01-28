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
@Table(name = "sy_debug")
public class SyDebug  extends BaseModel implements java.io.Serializable{
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	@Length(max=65535)
	private java.lang.String bugContent;
	@Length(max=255)
	private java.lang.String findUser;
	@Length(max=255)
	private java.lang.String editResult;
	@Length(max=255)
	private java.lang.String updateUser;
	@Length(max=255)
	private java.lang.String updateTime;
	@Length(max=255)
	private java.lang.String state;
	//columns END


	public SyDebug(){
	}

	public SyDebug(
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
	
	@Column(name = "bugContent", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getBugContent() {
		return this.bugContent;
	}
	
	public void setBugContent(java.lang.String value) {
		this.bugContent = value;
	}
	
	@Column(name = "findUser", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getFindUser() {
		return this.findUser;
	}
	
	public void setFindUser(java.lang.String value) {
		this.findUser = value;
	}
	
	@Column(name = "editResult", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getEditResult() {
		return this.editResult;
	}
	
	public void setEditResult(java.lang.String value) {
		this.editResult = value;
	}
	
	@Column(name = "updateUser", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getUpdateUser() {
		return this.updateUser;
	}
	
	public void setUpdateUser(java.lang.String value) {
		this.updateUser = value;
	}
	
	@Column(name = "updateTime", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.lang.String value) {
		this.updateTime = value;
	}
	
	@Column(name = "state", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("BugContent",getBugContent())
			.append("FindUser",getFindUser())
			.append("EditResult",getEditResult())
			.append("UpdateUser",getUpdateUser())
			.append("UpdateTime",getUpdateTime())
			.append("State",getState())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SyDebug == false) return false;
		if(this == obj) return true;
		SyDebug other = (SyDebug)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

