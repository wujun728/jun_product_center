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
@Table(name = "sy_sb_project_page")
public class SySbProjectPage  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	
	private java.lang.Integer pid;
	
	private java.lang.Integer parentId;
	@Length(max=50)
	private java.lang.String pageName;
	@Length(max=65535)
	private java.lang.String pageContent;
	@Length(max=10)
	private java.lang.String backgroundColor;
	@Length(max=100)
	private java.lang.String nodeId;
	@Length(max=255)
	private java.lang.String updateTime;
	//columns END


	public SySbProjectPage(){
	}

	public SySbProjectPage(
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
	
	@Column(name = "p_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getPid() {
		return this.pid;
	}
	
	public void setPid(java.lang.Integer value) {
		this.pid = value;
	}
	
	@Column(name = "parent_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getParentId() {
		return this.parentId;
	}
	
	public void setParentId(java.lang.Integer value) {
		this.parentId = value;
	}
	
	@Column(name = "page_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getPageName() {
		return this.pageName;
	}
	
	public void setPageName(java.lang.String value) {
		this.pageName = value;
	}
	
	@Column(name = "page_content", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getPageContent() {
		return this.pageContent;
	}
	
	public void setPageContent(java.lang.String value) {
		this.pageContent = value;
	}
	
	@Column(name = "background_color", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getBackgroundColor() {
		return this.backgroundColor;
	}
	
	public void setBackgroundColor(java.lang.String value) {
		this.backgroundColor = value;
	}
	
	@Column(name = "node_id", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getNodeId() {
		return this.nodeId;
	}
	
	public void setNodeId(java.lang.String value) {
		this.nodeId = value;
	}
	
	@Column(name = "update_time", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.lang.String value) {
		this.updateTime = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Pid",getPid())
			.append("ParentId",getParentId())
			.append("PageName",getPageName())
			.append("PageContent",getPageContent())
			.append("BackgroundColor",getBackgroundColor())
			.append("NodeId",getNodeId())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SySbProjectPage == false) return false;
		if(this == obj) return true;
		SySbProjectPage other = (SySbProjectPage)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

