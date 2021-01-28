package com.sz.message;

import org.codehaus.jackson.annotate.JsonIgnore;

public class RequestMessage implements IRequestMessage {
	private String time;
	private String userId;
	private String name;
	
	public RequestMessage() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTime() {
		return this.time;
	}

//	@JsonIgnore
//	public boolean isValid() {
//		return false;
//	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@JsonIgnore
	public String getName() {
		return "��������Ϣ";
	}
}
