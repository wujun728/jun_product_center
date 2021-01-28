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
public class SySbRuninfo_Vo  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	private String id;
	private String freeMemory;
	private String totalMemory;
	private String maxMemory;
	private String tomcatDir;
	private String dpDir;
	private String insertTime;
	//columns END


	public void setId(String value) {
		this.id = value;
	}
	
		public String getId() {
		return this.id;
	}
	
	public String getFreeMemory() {
		return this.freeMemory;
	}
	
	public void setFreeMemory(String value) {
		this.freeMemory = value;
	}
	
	public String getTotalMemory() {
		return this.totalMemory;
	}
	
	public void setTotalMemory(String value) {
		this.totalMemory = value;
	}
	
	public String getMaxMemory() {
		return this.maxMemory;
	}
	
	public void setMaxMemory(String value) {
		this.maxMemory = value;
	}
	
	public String getTomcatDir() {
		return this.tomcatDir;
	}
	
	public void setTomcatDir(String value) {
		this.tomcatDir = value;
	}
	
	public String getDpDir() {
		return this.dpDir;
	}
	
	public void setDpDir(String value) {
		this.dpDir = value;
	}
	
	public String getInsertTime() {
		return this.insertTime;
	}
	
	public void setInsertTime(String value) {
		this.insertTime = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("FreeMemory",getFreeMemory())
			.append("TotalMemory",getTotalMemory())
			.append("MaxMemory",getMaxMemory())
			.append("TomcatDir",getTomcatDir())
			.append("DpDir",getDpDir())
			.append("InsertTime",getInsertTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SySbRuninfo == false) return false;
		if(this == obj) return true;
		SySbRuninfo other = (SySbRuninfo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

