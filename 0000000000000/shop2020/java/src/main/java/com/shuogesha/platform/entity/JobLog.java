package com.shuogesha.platform.entity;

import java.io.Serializable;
/**
 * 任务日志
 * @author zhaohy
 *
 */
public class JobLog implements Serializable {
	
		private Long id;
	private String name; 
	private String content;
	private String dateline;
	private String status;

	public JobLog() {
		super();
	}

	public JobLog(String name, String content, String dateline,
			String status) {
		super();
		this.name = name;
		this.content = content;
		this.dateline = dateline;
		this.status = status;
	} 
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDateline() {
		return dateline;
	}

	public void setDateline(String dateline) {
		this.dateline = dateline;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
