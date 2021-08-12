package com.shuogesha.platform.entity;

import java.io.Serializable;

public class File implements Serializable {
	
		private Long id;
	private String name;
	private String dateline;
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
	public String getDateline() {
		return this.dateline;
	}
	public void setDateline(String dateline) {
		this.dateline=dateline;
	}

	
}
