package com.jun.plugin.demo.jpa.pojo;

import java.io.Serializable;

/**
 * The persistent class for the user database table.
 * 
 */

public class CopyOfUser2 implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long userId;

	private String account;

	private String userName;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}