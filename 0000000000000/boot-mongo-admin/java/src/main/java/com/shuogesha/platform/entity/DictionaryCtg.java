package com.shuogesha.platform.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 字典分类
 * 
 * @author zhaohaiyuan
 *
 */
@Document(collection="dictionaryCtg")
public class DictionaryCtg implements Serializable {
	@Id 
	private String id;
	private String name;
	private String code;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
