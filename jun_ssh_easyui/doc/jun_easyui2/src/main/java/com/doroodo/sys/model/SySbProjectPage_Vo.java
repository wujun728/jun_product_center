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
public class SySbProjectPage_Vo  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	private String id;
	private String pid;
	private String parentId;
	private String pageName;
	private String pageContent;
	private String backgroundColor;
	private String nodeId;
	private String updateTime;
	//columns END


	public void setId(String value) {
		this.id = value;
	}
	
		public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

		public String getId() {
		return this.id;
	}
	
	public String getPid() {
		return this.pid;
	}
	
	public void setPid(String value) {
		this.pid = value;
	}
	
	public String getParentId() {
		return this.parentId;
	}
	
	public void setParentId(String value) {
		this.parentId = value;
	}
	
	public String getPageName() {
		return this.pageName;
	}
	
	public void setPageName(String value) {
		this.pageName = value;
	}
	
	public String getPageContent() {
		return this.pageContent;
	}
	
	public void setPageContent(String value) {
		this.pageContent = value;
	}
	
	public String getNodeId() {
		return this.nodeId;
	}
	
	public void setNodeId(String value) {
		this.nodeId = value;
	}
	
	public String getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(String value) {
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

