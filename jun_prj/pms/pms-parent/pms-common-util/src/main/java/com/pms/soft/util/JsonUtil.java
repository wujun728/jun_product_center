package com.pms.soft.util;

public class JsonUtil {

	/***
	 * 将json信息发送给前台展现
	 * 
	 * @param fangleComments
	 */
	public static String returnObjectToJson(Object obj) {

		return JsonBinder.buildNormalBinder().toJson(obj);
	}
}
