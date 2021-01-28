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
public class SySbJsDictionaries_Vo  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	private String id;
	private String jsEvent;
	private String eventName;
	private String jsEventContent;
	private String updateTime;
	//columns END


	public void setId(String value) {
		this.id = value;
	}
	
		public String getId() {
		return this.id;
	}
	
	public String getJsEvent() {
		return this.jsEvent;
	}
	
	public void setJsEvent(String value) {
		this.jsEvent = value;
	}
	
	public String getEventName() {
		return this.eventName;
	}
	
	public void setEventName(String value) {
		this.eventName = value;
	}
	
	public String getJsEventContent() {
		return this.jsEventContent;
	}
	
	public void setJsEventContent(String value) {
		this.jsEventContent = value;
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
		if(obj instanceof SySbJsDictionaries == false) return false;
		if(this == obj) return true;
		SySbJsDictionaries other = (SySbJsDictionaries)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

