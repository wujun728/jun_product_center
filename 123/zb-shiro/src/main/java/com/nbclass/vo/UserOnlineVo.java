package com.nbclass.vo;

import com.nbclass.model.User;

import java.io.Serializable;
import java.util.Date;

/**
 * @version V1.0
 * @date 2018年7月20日
 * @author superzheng
 */
public class UserOnlineVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String sessionId;
	private String username;
	private String host;
	private Date startTime;
	private Date lastAccess;
	private Date lastLoginTime;
	private long timeout;
	private boolean sessionStatus = Boolean.TRUE;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public boolean isSessionStatus() {
		return sessionStatus;
	}

	public void setSessionStatus(boolean sessionStatus) {
		this.sessionStatus = sessionStatus;
	}
}
