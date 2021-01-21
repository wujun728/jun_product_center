package com.sz.message.request;

import com.sz.message.RequestMessage;
import com.sz.message.vo.UserPayReadyData;

public class UserPayReadyRequestMessage extends RequestMessage {
	
	private UserPayReadyData userPayReadyData;

	public UserPayReadyData getUserPayReadyData() {
		return userPayReadyData;
	}

	public void setUserPayReadyData(UserPayReadyData userPayReadyData) {
		this.userPayReadyData = userPayReadyData;
	}

	@Override
	public String toString() {
		return "UserPayReadyRequestMessage [userPayReadyData="
				+ userPayReadyData + "]";
	}

}
