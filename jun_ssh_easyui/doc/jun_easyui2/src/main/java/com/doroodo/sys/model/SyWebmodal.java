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
@Table(name = "sy_webmodal")
public class SyWebmodal  extends BaseModel implements java.io.Serializable{
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	@Length(max=65535)
	private java.lang.String title;
	@Length(max=65535)
	private java.lang.String codeName;
	@Length(max=65535)
	private java.lang.String imgPath;
	@Length(max=65535)
	private java.lang.String info;
	//columns END


	public SyWebmodal(){
	}

	public SyWebmodal(
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
	
	@Column(name = "Title", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	@Column(name = "CodeName", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getCodeName() {
		return this.codeName;
	}
	
	public void setCodeName(java.lang.String value) {
		this.codeName = value;
	}
	
	@Column(name = "ImgPath", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getImgPath() {
		return this.imgPath;
	}
	
	public void setImgPath(java.lang.String value) {
		this.imgPath = value;
	}
	
	@Column(name = "Info", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getInfo() {
		return this.info;
	}
	
	public void setInfo(java.lang.String value) {
		this.info = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Title",getTitle())
			.append("CodeName",getCodeName())
			.append("ImgPath",getImgPath())
			.append("Info",getInfo())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SyWebmodal == false) return false;
		if(this == obj) return true;
		SyWebmodal other = (SyWebmodal)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

