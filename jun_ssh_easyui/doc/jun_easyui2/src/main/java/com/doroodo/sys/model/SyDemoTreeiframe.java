package com.doroodo.sys.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.doroodo.base.model.BaseModel;
@Entity
@Table(name = "sy_demo_treeiframe")
public class SyDemoTreeiframe  extends BaseModel implements java.io.Serializable{
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	@Length(max=2000)
	private java.lang.String routeId;
	@Length(max=2000)
	private java.lang.String routeName;
	@Length(max=2000)
	private java.lang.String name;
	
	private java.lang.Integer pid;
	//columns END


	public SyDemoTreeiframe(){
	}

	public SyDemoTreeiframe(
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
	
	@Column(name = "RouteId", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public java.lang.String getRouteId() {
		return this.routeId;
	}
	
	public void setRouteId(java.lang.String value) {
		this.routeId = value;
	}
	
	@Column(name = "RouteName", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public java.lang.String getRouteName() {
		return this.routeName;
	}
	
	public void setRouteName(java.lang.String value) {
		this.routeName = value;
	}
	
	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	@Column(name = "PId", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getPid() {
		return this.pid;
	}
	
	public void setPid(java.lang.Integer value) {
		this.pid = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("RouteId",getRouteId())
			.append("RouteName",getRouteName())
			.append("Name",getName())
			.append("Pid",getPid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SyDemoTreeiframe == false) return false;
		if(this == obj) return true;
		SyDemoTreeiframe other = (SyDemoTreeiframe)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

