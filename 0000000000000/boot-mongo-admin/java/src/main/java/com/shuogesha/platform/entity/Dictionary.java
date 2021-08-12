package com.shuogesha.platform.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 字典选项
 * 
 * @author zhaohaiyuan
 *
 */
@Document(collection="dictionary")
public class Dictionary implements Serializable {
	@Id 
	private String id;
	private String name;
	private String value;
	private String ctgId;
	private Integer sort;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
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

	public String getCtgId() {
		return ctgId;
	}

	public void setCtgId(String ctgId) {
		this.ctgId = ctgId;
	}

}
