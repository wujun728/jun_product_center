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
public class TsbPrimitive_Vo  extends BaseModel implements java.io.Serializable{
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	private String id;
	private String classId;
	private String primitiveName;
	private String fileName;
	private String primitiveNodeType;
	private String imgWidth;
	private String imgHeight;
	private String updateTime;
	//columns END


	public void setId(String value) {
		this.id = value;
	}
	
		public String getId() {
		return this.id;
	}
	
	public String getClassId() {
		return this.classId;
	}
	
	public void setClassId(String value) {
		this.classId = value;
	}
	
	public String getPrimitiveName() {
		return this.primitiveName;
	}
	
	public void setPrimitiveName(String value) {
		this.primitiveName = value;
	}
	
	public String getFileName() {
		return this.fileName;
	}
	
	public void setFileName(String value) {
		this.fileName = value;
	}
	
	public String getPrimitiveNodeType() {
		return this.primitiveNodeType;
	}
	
	public void setPrimitiveNodeType(String value) {
		this.primitiveNodeType = value;
	}
	
	public String getImgWidth() {
		return this.imgWidth;
	}
	
	public void setImgWidth(String value) {
		this.imgWidth = value;
	}
	
	public String getImgHeight() {
		return this.imgHeight;
	}
	
	public void setImgHeight(String value) {
		this.imgHeight = value;
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

