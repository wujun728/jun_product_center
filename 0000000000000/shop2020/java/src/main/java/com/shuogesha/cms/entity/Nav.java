package com.shuogesha.cms.entity;

import java.io.Serializable;

public class Nav implements Serializable {
	
		private Long id;
	private String name;
	private String sort;
	private String icon;
	private String img;
	private String url;
	private String status;
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
	public String getIcon() {
		return this.icon;
	}
	public void setIcon(String icon) {
		this.icon=icon;
	}
	public String getImg() {
		return this.img;
	}
	public void setImg(String img) {
		this.img=img;
	}
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url=url;
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status=status;
	}

	
}
