package com.shuogesha.platform.entity;

import java.io.Serializable;

import lombok.Data;

public class App implements Serializable {
	
		private String id;
	private String name;
	private Integer sort;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	
}
