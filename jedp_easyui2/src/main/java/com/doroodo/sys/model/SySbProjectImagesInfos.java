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
@Table(name = "sy_sb_project_images_infos")
public class SySbProjectImagesInfos  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	
	private java.lang.Integer ptimitiveId;
	@Length(max=100)
	private java.lang.String imageUname;
	@Length(max=255)
	private java.lang.String imgName;
	@Length(max=20)
	private java.lang.String updateTime;
	//columns END


	public SySbProjectImagesInfos(){
	}

	public SySbProjectImagesInfos(
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
	
	@Column(name = "ptimitive_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getPtimitiveId() {
		return this.ptimitiveId;
	}
	
	public void setPtimitiveId(java.lang.Integer value) {
		this.ptimitiveId = value;
	}
	
	@Column(name = "img_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getImgName() {
		return this.imgName;
	}
	
	public void setImgName(java.lang.String value) {
		this.imgName = value;
	}
	
	@Column(name = "image_uname", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getImageUname() {
		return this.imageUname;
	}
	
	public void setImageUname(java.lang.String value) {
		this.imageUname = value;
	}
	
	@Column(name = "update_time", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.lang.String value) {
		this.updateTime = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("PtimitiveId",getPtimitiveId())
			.append("imageUname",getImageUname())
			.append("imgName",getImgName())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SySbProjectImagesInfos == false) return false;
		if(this == obj) return true;
		SySbProjectImagesInfos other = (SySbProjectImagesInfos)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

