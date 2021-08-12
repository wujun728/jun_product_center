package com.shuogesha.platform.entity;

import java.io.Serializable;

public class Payments implements Serializable {
	
		private Long id;
	private String name;
	private String sort;
	private String status;//是否显示
	private String remark;
	private String img;
	private String content;
	
	private String type;//类型
	private String platform;//平台 
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getSort() {
		return this.sort;
	}
	public void setSort(String sort) {
		this.sort=sort;
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status=status;
	}
	public String getRemark() {
		return this.remark;
	}
	public void setRemark(String remark) {
		this.remark=remark;
	}
	public String getImg() {
		return this.img;
	}
	public void setImg(String img) {
		this.img=img;
	}
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content=content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	} 
}
