package com.shuogesha.platform.entity;

import java.io.Serializable;

public class ItemParam implements Serializable {
	
		private Integer id;
	private String name;
	
	private String content;
	
	private String path;
	private Integer sort;
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id=id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public Integer getSort() {
		return this.sort;
	}
	public void setSort(Integer sort) {
		this.sort=sort;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	
}
