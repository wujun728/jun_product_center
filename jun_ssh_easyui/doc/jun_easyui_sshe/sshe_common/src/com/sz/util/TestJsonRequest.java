package com.sz.util;

import com.sz.message.request.UserRegRequestMessage;
import com.sz.message.retun.ReturnMessage.ERRCODE;
import com.sz.message.retun.UserRegReturnMessage;
import com.sz.message.vo.UserInfoData;
import com.sz.tool.UserRegMessageHandle;

public class TestJsonRequest {
	public static void main(String[] args) {
		long t = System.currentTimeMillis();
		
		UserRegRequestMessage userRegRequestMessage = new UserRegRequestMessage();
		userRegRequestMessage.setUserId("111");
		UserInfoData userInfoData = new UserInfoData();
		userInfoData.setAccounts("account001");

		userRegRequestMessage.setUserInfoData(userInfoData);
		userRegRequestMessage.setTime(System.currentTimeMillis() + "");
		String str = JsonUtils.getJson(userRegRequestMessage);

		System.out.println("����ע����Ϣ:  " + str);
		
		UserRegReturnMessage userRegReturnMessage = new UserRegReturnMessage(
				"1", ERRCODE.ERRCODE_NONE,"��gogn", "2013");
		
		String returnstr = JsonUtils.getJson(userRegReturnMessage);
		
		System.out.println("������Ϣ:  " + returnstr);
		
		UserRegReturnMessage attachObj = JsonUtils.readValue(returnstr,
				UserRegReturnMessage.class);
		
		System.out.println("���:  " + attachObj);
		
		UserInfoData vv = (UserInfoData) (new UserRegMessageHandle()
				.toBean(str));
		
	}
}
