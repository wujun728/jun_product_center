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
@Table(name = "sy_notice")
public class SyNotice  extends BaseModel implements java.io.Serializable{
	//alias
	public static final String TABLE_ALIAS = "SyNotice";
	public static final String ALIAS_ID = "checkbox:true^title:'编号'^addform:{type:'eType.Input', hidden:true}^editform:{type:'eType.Input', hidden:true}^hidden:false";
	public static final String ALIAS_THETEXT = "title:'正文'^addform:{hidden:false, type:'eType.HtmlEdit'}^editform:{hidden:false, type:'eType.HtmlEdit'}^hidden:true";
	public static final String ALIAS_CREATETIME = "title:'创建时间'^hidden:false^addform:{hidden:false, type:'eType.DateTimeBox'}^editform:{hidden:false, type:'eType.DateTimeBox'}";
	public static final String ALIAS_CEATEUSERID = "addform:{setvalue:'getCurrentUserId()', readonly:true, hidden:false, type:'eType.Input'}^editform:{readonly:true, hidden:false, type:'eType.Input'}^hidden:false";
	public static final String ALIAS_TITLE = "checkbox:false^title:'标题'^hidden:false^addform:{hidden:false, type:'eType.Input'}^editform:{hidden:false, type:'eType.Input'}";
	public static final String ALIAS_READUSERNAMES = "addform:{hidden:true, type:'eType.Input'}^editform:{hidden:true, type:'eType.Input'}^title:undefined";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Integer id;
	
	private java.lang.String thetext;
	@Length(max=255)
	private java.lang.String createtime;
	@NotBlank @Length(max=225)
	private java.lang.String ceateuserid;
	@Length(max=2000)
	private java.lang.String title;
	@Length(max=2000)
	private java.lang.String readusernames;
	//columns END


	public SyNotice(){
	}

	public SyNotice(
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
	
	@Column(name = "thetext", unique = false, nullable = true, insertable = true, updatable = true, length = 1073741823)
	public java.lang.String getThetext() {
		return this.thetext;
	}
	
	public void setThetext(java.lang.String value) {
		this.thetext = value;
	}
	
	@Column(name = "createtime", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.lang.String value) {
		this.createtime = value;
	}
	
	@Column(name = "ceateuserid", unique = false, nullable = false, insertable = true, updatable = true, length = 225)
	public java.lang.String getCeateuserid() {
		return this.ceateuserid;
	}
	
	public void setCeateuserid(java.lang.String value) {
		this.ceateuserid = value;
	}
	
	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	@Column(name = "readusernames", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public java.lang.String getReadusernames() {
		return this.readusernames;
	}
	
	public void setReadusernames(java.lang.String value) {
		this.readusernames = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Thetext",getThetext())
			.append("Createtime",getCreatetime())
			.append("Ceateuserid",getCeateuserid())
			.append("Title",getTitle())
			.append("Readusernames",getReadusernames())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SyNotice == false) return false;
		if(this == obj) return true;
		SyNotice other = (SyNotice)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

