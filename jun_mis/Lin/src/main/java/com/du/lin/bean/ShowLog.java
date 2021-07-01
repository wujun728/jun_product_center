package com.du.lin.bean;


/**
 * 用于前后端传输数据
 * 包括业务日志和登陆日志
 */
public class ShowLog {

	private String logname;

	private String username;

	private String createtime;

	public String getLogname() {
		return logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	

	
	

}