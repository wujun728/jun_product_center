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
@Table(name = "sy_log")
public class SyLog  extends BaseModel implements java.io.Serializable{
	//alias
	public static final String TABLE_ALIAS = "SyLog";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_LOG_DATE = "logDate";
	public static final String ALIAS_LOG_LEVEL = "logLevel";
	public static final String ALIAS_LOCATION = "location";
	public static final String ALIAS_MESSAGE = "message";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	@Length(max=50)
	private java.lang.String logDate;
	@Length(max=5)
	private java.lang.String logLevel;
	@Length(max=100)
	private java.lang.String location;
	@Length(max=1000)
	private java.lang.String message;
	//columns END


	public SyLog(){
	}

	public SyLog(
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
	
	@Column(name = "log_date", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getLogDate() {
		return this.logDate;
	}
	
	public void setLogDate(java.lang.String value) {
		this.logDate = value;
	}
	
	@Column(name = "log_level", unique = false, nullable = true, insertable = true, updatable = true, length = 5)
	public java.lang.String getLogLevel() {
		return this.logLevel;
	}
	
	public void setLogLevel(java.lang.String value) {
		this.logLevel = value;
	}
	
	@Column(name = "location", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getLocation() {
		return this.location;
	}
	
	public void setLocation(java.lang.String value) {
		this.location = value;
	}
	
	@Column(name = "message", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public java.lang.String getMessage() {
		return this.message;
	}
	
	public void setMessage(java.lang.String value) {
		this.message = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("LogDate",getLogDate())
			.append("LogLevel",getLogLevel())
			.append("Location",getLocation())
			.append("Message",getMessage())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SyLog == false) return false;
		if(this == obj) return true;
		SyLog other = (SyLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

