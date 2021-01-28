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
@Table(name = "t_sb_js_dictionaries")
public class TsbJsDictionaries  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	@Length(max=50)
	private java.lang.String jsEvent;
	@Length(max=50)
	private java.lang.String eventName;
	@Length(max=65535)
	private java.lang.String jsEventContent;
	@Length(max=50)
	private java.lang.String updateTime;
	//columns END


	public TsbJsDictionaries(){
	}

	public TsbJsDictionaries(
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
	
	@Column(name = "js_event", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getJsEvent() {
		return this.jsEvent;
	}
	
	public void setJsEvent(java.lang.String value) {
		this.jsEvent = value;
	}
	
	@Column(name = "event_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getEventName() {
		return this.eventName;
	}
	
	public void setEventName(java.lang.String value) {
		this.eventName = value;
	}
	
	@Column(name = "js_event_content", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getJsEventContent() {
		return this.jsEventContent;
	}
	
	public void setJsEventContent(java.lang.String value) {
		this.jsEventContent = value;
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
			.append("JsEvent",getJsEvent())
			.append("EventName",getEventName())
			.append("JsEventContent",getJsEventContent())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsbJsDictionaries == false) return false;
		if(this == obj) return true;
		TsbJsDictionaries other = (TsbJsDictionaries)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

