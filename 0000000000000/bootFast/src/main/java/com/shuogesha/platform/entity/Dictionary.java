package com.shuogesha.platform.entity;

import java.io.Serializable;

/**
 * 字典选项
 * 
 * @author zhaohaiyuan
 *
 */
public class Dictionary implements Serializable {

	private Long id;
	private String name;
	private String value;
	private Integer ctgId;
	private Integer sort;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getCtgId() {
		return ctgId;
	}

	public void setCtgId(Integer ctgId) {
		this.ctgId = ctgId;
	}

}
