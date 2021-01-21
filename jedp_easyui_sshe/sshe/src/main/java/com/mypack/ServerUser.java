package com.mypack;

public class ServerUser {
	/**
	 * 服务器之间的受信任用户
	 */
	private String userName;
	/**
	 * 服务器之间的用户密码
	 */
	private String userPass;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userPass
	 */
	public String getUserPass() {
		return userPass;
	}

	/**
	 * @param userPass
	 *            the userPass to set
	 */
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

}
