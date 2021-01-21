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
@Table(name = "t_sb_primitive")
public class TsbPrimitive  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	
	private java.lang.Integer classId;
	@Length(max=255)
	private java.lang.String primitiveName;
	@Length(max=255)
	private java.lang.String fileName;
	@Length(max=50)
	private java.lang.String primitiveNodeType;
	@Length(max=10)
	private java.lang.String imgWidth;
	@Length(max=10)
	private java.lang.String imgHeight;
	@Length(max=255)
	private java.lang.String updateTime;
	//columns END


	public TsbPrimitive(){
	}

	public TsbPrimitive(
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
	
	@Column(name = "class_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getClassId() {
		return this.classId;
	}
	
	public void setClassId(java.lang.Integer value) {
		this.classId = value;
	}
	
	@Column(name = "primitive_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getPrimitiveName() {
		return this.primitiveName;
	}
	
	public void setPrimitiveName(java.lang.String value) {
		this.primitiveName = value;
	}
	
	@Column(name = "file_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getFileName() {
		return this.fileName;
	}
	
	public void setFileName(java.lang.String value) {
		this.fileName = value;
	}
	
	@Column(name = "primitive_node_type", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getPrimitiveNodeType() {
		return this.primitiveNodeType;
	}
	
	public void setPrimitiveNodeType(java.lang.String value) {
		this.primitiveNodeType = value;
	}
	
	@Column(name = "img_width", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getImgWidth() {
		return this.imgWidth;
	}
	
	public void setImgWidth(java.lang.String value) {
		this.imgWidth = value;
	}
	
	@Column(name = "img_height", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getImgHeight() {
		return this.imgHeight;
	}
	
	public void setImgHeight(java.lang.String value) {
		this.imgHeight = value;
	}
	
	@Column(name = "update_time", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.lang.String value) {
		this.updateTime = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ClassId",getClassId())
			.append("PrimitiveName",getPrimitiveName())
			.append("FileName",getFileName())
			.append("PrimitiveNodeType",getPrimitiveNodeType())
			.append("ImgWidth",getImgWidth())
			.append("ImgHeight",getImgHeight())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsbPrimitive == false) return false;
		if(this == obj) return true;
		TsbPrimitive other = (TsbPrimitive)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

