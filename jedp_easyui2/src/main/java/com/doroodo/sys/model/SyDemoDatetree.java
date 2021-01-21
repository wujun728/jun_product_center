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
@Table(name = "sy_demo_datetree")
public class SyDemoDatetree  extends BaseModel implements java.io.Serializable{
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	@Length(max=2000)
	private java.lang.String doroodoDate;
	@Length(max=2000)
	private java.lang.String explainv;
	//columns END


	public SyDemoDatetree(){
	}

	public SyDemoDatetree(
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
	
	@Column(name = "doroodo_Date", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public java.lang.String getDoroodoDate() {
		return this.doroodoDate;
	}
	
	public void setDoroodoDate(java.lang.String value) {
		this.doroodoDate = value;
	}
	
	@Column(name = "explainv", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public java.lang.String getExplainv() {
		return this.explainv;
	}
	
	public void setExplainv(java.lang.String value) {
		this.explainv = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("DoroodoDate",getDoroodoDate())
			.append("Explainv",getExplainv())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SyDemoDatetree == false) return false;
		if(this == obj) return true;
		SyDemoDatetree other = (SyDemoDatetree)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

