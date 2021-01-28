package com.mypack;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationUserList {
	private static List<ServerUser> userList;

	public void init() {
		if (this.userList == null) {
			this.userList = new ArrayList<ServerUser>();
		}
	}

	public void addServerUser(ServerUser serverUser) {
		this.init();
		if (this.userList.indexOf(serverUser) != -1) {
			return;
		}
		this.userList.add(serverUser);
	}

	/**
	 * 获得用户数量
	 * 
	 * @return
	 */
	public int size() {
		return this.userList.size();
	}

	/**
	 * 获得用户
	 * 
	 * @param serverUser
	 * @return
	 */
	public ServerUser get(ServerUser serverUser) {
		if (serverUser != null) {
			int index = this.userList.indexOf(serverUser);
			if (index != -1) {
				return this.userList.get(index);
			}
		}
		return null;
	}
	
}
