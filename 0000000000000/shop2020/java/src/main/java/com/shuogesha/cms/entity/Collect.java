package com.shuogesha.cms.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Transient;

import com.alibaba.fastjson.JSONObject;
/**
 * 收藏功能
 * @author zhaohaiyuan
 *
 */
public class Collect implements Serializable {
	
		private Long id;
	private String name;//收藏的标题
	private Long referid;//对象id
	private String refer;//收藏对象
	private String dateline;
	private Long userId;//用户id
	private String content;//收藏的json  
	
	@Transient
	private JSONObject data;//收藏的json
	
	

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

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	
}
