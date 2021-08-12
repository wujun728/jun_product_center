package com.shuogesha.cms.entity;

import java.io.Serializable;
/**
 * 用户足迹
 * @author zhaohaiyuan
 *
 */
public class Foot implements Serializable {
	
		private Long id;
	private String name;
	private Long referid;//对象id
	private String refer;//对象
	private String dateline;
	private Long userId;
	private String content;//对象的json字符串 
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
	public Long getReferid() {
		return this.referid;
	}
	public void setReferid(Long referid) {
		this.referid=referid;
	}
	public String getRefer() {
		return this.refer;
	}
	public void setRefer(String refer) {
		this.refer=refer;
	}
	public String getDateline() {
		return this.dateline;
	}
	public void setDateline(String dateline) {
		this.dateline=dateline;
	}
	 
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content=content;
	}

	
}
