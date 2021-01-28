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
public class TsbProject_Vo  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	private String id;
	private String projectName;
	private String projectDescription;
	private String indexPage;
	//columns END


	public void setId(String value) {
		this.id = value;
	}
	
		public String getId() {
		return this.id;
	}
	
	public String getProjectName() {
		return this.projectName;
	}
	
	public void setProjectName(String value) {
		this.projectName = value;
	}
	
	public String getProjectDescription() {
		return this.projectDescription;
	}
	
	public void setProjectDescription(String value) {
		this.projectDescription = value;
	}
	
	public String getIndexPage() {
		return this.indexPage;
	}
	
	public void setIndexPage(String value) {
		this.indexPage = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ProjectName",getProjectName())
			.append("ProjectDescription",getProjectDescription())
			.append("IndexPage",getIndexPage())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsbProject == false) return false;
		if(this == obj) return true;
		TsbProject other = (TsbProject)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

