package com.shuogesha.platform.entity;

import java.io.Serializable;

public class DictionaryCtg implements Serializable {
	
		private Long id;
	private String name;
	private String code;
	private Integer sort;
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
	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code=code;
	}
	public Integer getSort() {
		return this.sort;
	}
	public void setSort(Integer sort) {
		this.sort=sort;
	}

	
}
