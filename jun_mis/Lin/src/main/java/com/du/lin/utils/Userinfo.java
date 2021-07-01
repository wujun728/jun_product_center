package com.du.lin.utils;

import org.apache.shiro.SecurityUtils;

import com.du.lin.bean.User;

/**
 * 用户信息类 可从中获取当前登陆用的信息
 */
public class Userinfo {
	/**
	 * 记录用户操作时间，若时间过长则需重新登陆
	 */
	private static long operateTime;
	public static String getUsername(){
			return getUser().getUsername();
	}
	
	public static User getUser(){
			return (User) SecurityUtils.getSubject().getPrincipal();
	}
	
	public static String getSex(){
		if (getUser()==null || "".equals(getUser().getAvator())) {
			return "unknow";
		}else{
			return getUser().getAvator().equals("女")?"girl":"boy";
		}
	}


	public static long getOperateTime() {
		return operateTime;
	}

	public static void setOperateTime(long operateTime) {
		Userinfo.operateTime = operateTime;
	}
	
	public static int getRoleid(){
		return getUser().getRoleid();
	}
	public static int getUserid(){
		return getUser().getId();
	}
	
	
}
