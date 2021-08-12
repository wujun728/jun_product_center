package com.shuogesha.cms.entity;

import java.io.Serializable;

import com.shuogesha.platform.entity.User;

public class Content implements Serializable {
	
		private Long id;
	private String name;
	private String content;
	private String dateline;
	private String description;
	private String img; 
		private User user;
	private Channel channel;
	private Count count;
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
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content=content;
	}
	public String getDateline() {
		return this.dateline;
	}
	public void setDateline(String dateline) {
		this.dateline=dateline;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public String getImg() {
		return this.img;
	}
	public void setImg(String img) {
		this.img=img;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Count getCount() {
		return count;
	}

	public void setCount(Count count) {
		this.count = count;
	}
	
		
	
}
