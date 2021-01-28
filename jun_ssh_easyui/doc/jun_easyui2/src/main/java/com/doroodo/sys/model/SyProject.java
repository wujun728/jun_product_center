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
@Table(name = "sy_project")
public class SyProject  extends BaseModel implements java.io.Serializable{
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	@Length(max=255)
	private java.lang.String projectName;
	@Length(max=65535)
	private java.lang.String projectInfo;
	@Length(max=65535)
	private java.lang.String projectModule;
	
	private java.lang.Integer projectUsed;
	//columns END


	public SyProject(){
	}

	public SyProject(
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
	
	@Column(name = "projectName", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getProjectName() {
		return this.projectName;
	}
	
	public void setProjectName(java.lang.String value) {
		this.projectName = value;
	}
	
	@Column(name = "projectInfo", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getProjectInfo() {
		return this.projectInfo;
	}
	
	public void setProjectInfo(java.lang.String value) {
		this.projectInfo = value;
	}
	
	@Column(name = "projectModule", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getProjectModule() {
		return this.projectModule;
	}
	
	public void setProjectModule(java.lang.String value) {
		this.projectModule = value;
	}
	
	@Column(name = "projectUsed", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getProjectUsed() {
		return this.projectUsed;
	}
	
	public void setProjectUsed(java.lang.Integer value) {
		this.projectUsed = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ProjectName",getProjectName())
			.append("ProjectInfo",getProjectInfo())
			.append("ProjectModule",getProjectModule())
			.append("ProjectUsed",getProjectUsed())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SyProject == false) return false;
		if(this == obj) return true;
		SyProject other = (SyProject)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

