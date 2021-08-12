package com.shuogesha.cms.entity;

import java.io.Serializable;

import com.shuogesha.platform.entity.UnifiedUser;
/**
 * 点赞喜欢功能
 * @author zhaohaiyuan
 *
 */
public class Praise implements Serializable {
	
		private Long id;
	private String name;
	private String refer;//对象 
	private Long referid;//对象id
	private Long userId;
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
	public String getRefer() {
		return this.refer;
	}
	public void setRefer(String refer) {
		this.refer=refer;
	}
	public Long getReferid() {
		return this.referid;
	}
	public void setReferid(Long referid) {
		this.referid=referid;
	}
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId=userId;
	}
	public String getDateline() {
		return this.dateline;
	}
	public void setDateline(String dateline) {
		this.dateline=dateline;
	}

	
}
