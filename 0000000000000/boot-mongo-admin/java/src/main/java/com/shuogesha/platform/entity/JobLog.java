package com.shuogesha.platform.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * 任务日志
 */
@Document(collection="jobLog")
public class JobLog implements Serializable {
	
	@Id 
	private String id;
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
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
