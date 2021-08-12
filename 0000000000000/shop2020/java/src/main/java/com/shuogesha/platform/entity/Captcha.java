package com.shuogesha.platform.entity;

import java.io.Serializable;

public class Captcha implements Serializable {
	
		private Long id;
	private String name;
	private String captcha;
	private java.sql.Timestamp dateline;
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
	public String getCaptcha() {
		return this.captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha=captcha;
	}
	public java.sql.Timestamp getDateline() {
		return this.dateline;
	}
	public void setDateline(java.sql.Timestamp dateline) {
		this.dateline=dateline;
	}

	
}
