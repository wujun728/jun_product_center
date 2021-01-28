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
@Table(name = "sy_sb_right_event_info")
public class SySbRightEventInfo  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	@Length(max=100)
	private java.lang.String nodeId;
	@Length(max=255)
	private java.lang.String menuName;
	@Length(max=255)
	private java.lang.String eventContent;
	@Length(max=50)
	private java.lang.String updateTime;
	//columns END


	public SySbRightEventInfo(){
	}

	public SySbRightEventInfo(
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
	
	@Column(name = "node_id", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getNodeId() {
		return this.nodeId;
	}
	
	public void setNodeId(java.lang.String value) {
		this.nodeId = value;
	}
	
	@Column(name = "menu_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getMenuName() {
		return this.menuName;
	}
	
	public void setMenuName(java.lang.String value) {
		this.menuName = value;
	}
	
	@Column(name = "event_content", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getEventContent() {
		return this.eventContent;
	}
	
	public void setEventContent(java.lang.String value) {
		this.eventContent = value;
	}
	
	@Column(name = "update_time", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.lang.String value) {
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

