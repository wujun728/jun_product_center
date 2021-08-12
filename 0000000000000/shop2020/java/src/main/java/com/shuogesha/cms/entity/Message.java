package com.shuogesha.cms.entity;

import java.io.Serializable;

import com.shuogesha.platform.entity.UnifiedUser;

public class Message implements Serializable {

	public static final String USER = "USER";

	public static final String TYPE_ORDER = "ORDER";
	public static final String TYPE_MESSAGE = "MESSAGE";// 私信
	public static final String TYPE_ALL = "ALL";

	private Long id;
	private String name = "ORDER"; 
	private String content; 
	private String type = "USER";// send,reply 
	private String data; 
	private String dateline;
	private Long userId;// 接收用户id
	private String status = "0";// 状态0,未读，1已读
	private Long fromUid;// 发送方的用户id 
	private UnifiedUser to_unifiedUser;
	private UnifiedUser from_unifiedUser;

	public Message() {
		super();
	}

	public Message(String name, String type, String content, String data, String dateline, Long userId) {
		super();
		this.name = name;
		this.content = content;
		this.type = type;
		this.data = data;
		this.dateline = dateline;
		this.userId = userId;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDateline() {
		return dateline;
	}

	public void setDateline(String dateline) {
		this.dateline = dateline;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getFromUid() {
		return fromUid;
	}

	public void setFromUid(Long fromUid) {
		this.fromUid = fromUid;
	}

	public UnifiedUser getTo_unifiedUser() {
		return to_unifiedUser;
	}

	public void setTo_unifiedUser(UnifiedUser to_unifiedUser) {
		this.to_unifiedUser = to_unifiedUser;
	}

	public UnifiedUser getFrom_unifiedUser() {
		return from_unifiedUser;
	}

	public void setFrom_unifiedUser(UnifiedUser from_unifiedUser) {
		this.from_unifiedUser = from_unifiedUser;
	}

}
