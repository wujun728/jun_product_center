package com.shuogesha.platform.entity;

import java.io.Serializable;
import java.util.Date;

public class SystemLog implements Serializable {
	
	public static final String PC="PC";
	public static final String APP="APP";  
		private Long id; 	private String name; 	private String username;  	private Date dateline; 	private String content; 	private String type; //PC APP
	private String ip;
	private String url;
	private String method;
	private String param;
	private Long userId;
	
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
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username=username;
	} 
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content=content;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type=type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	} 

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setDateline(Date dateline) {
		this.dateline = dateline;
	}

	public Date getDateline() {
		return dateline;
	} 
	
	
}
