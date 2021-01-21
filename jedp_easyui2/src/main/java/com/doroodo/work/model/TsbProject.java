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
@Table(name = "t_sb_project")
public class TsbProject  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	@Length(max=50)
	private java.lang.String projectName;
	@Length(max=255)
	private java.lang.String projectDescription;
	@Length(max=100)
	private java.lang.String indexPage;
	@Length(max=50)
	private java.lang.String pageNode;
	//columns END


	public TsbProject(){
	}

	public TsbProject(
		java.lang.Integer id
	){
		this.id = id;
	}

	

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "increment")
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getId() {
		return this.id;
	}
	
	@Column(name = "project_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getProjectName() {
		return this.projectName;
	}
	
	public void setProjectName(java.lang.String value) {
		this.projectName = value;
	}
	
	@Column(name = "project_description", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getProjectDescription() {
		return this.projectDescription;
	}
	
	public void setProjectDescription(java.lang.String value) {
		this.projectDescription = value;
	}
	
	@Column(name = "index_page", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getIndexPage() {
		return this.indexPage;
	}
	
	public void setIndexPage(java.lang.String value) {
		this.indexPage = value;
	}
	
	@Column(name = "page_node", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getPageNode() {
		return this.pageNode;
	}
	
	public void setPageNode(java.lang.String value) {
		this.pageNode = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ProjectName",getProjectName())
			.append("ProjectDescription",getProjectDescription())
			.append("IndexPage",getIndexPage())
			.append("PageNode",getPageNode())
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

