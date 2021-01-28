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
public class SySbRightEventInfo_Vo  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	private String id;
	private String nodeId;
	private String menuName;
	private String eventContent;
	private String updateTime;
	//columns END


	public void setId(String value) {
		this.id = value;
	}
	
		public String getId() {
		return this.id;
	}
	
	public String getNodeId() {
		return this.nodeId;
	}
	
	public void setNodeId(String value) {
		this.nodeId = value;
	}
	
	public String getMenuName() {
		return this.menuName;
	}
	
	public void setMenuName(String value) {
		this.menuName = value;
	}
	
	public String getEventContent() {
		return this.eventContent;
	}
	
	public void setEventContent(String value) {
		this.eventContent = value;
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
			.append("NodeId",getNodeId())
			.append("MenuName",getMenuName())
			.append("EventContent",getEventContent())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SySbRightEventInfo == false) return false;
		if(this == obj) return true;
		SySbRightEventInfo other = (SySbRightEventInfo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

