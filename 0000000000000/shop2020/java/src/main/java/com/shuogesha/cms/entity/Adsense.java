package com.shuogesha.cms.entity;

import java.io.Serializable;

public class Adsense implements Serializable {
	
		private Long id;
	private String name;
	private String sort;
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

	
}
