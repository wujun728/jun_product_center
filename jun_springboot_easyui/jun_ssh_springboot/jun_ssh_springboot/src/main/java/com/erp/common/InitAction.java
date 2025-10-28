package com.erp.common;

import org.springframework.beans.factory.annotation.Autowired;

import com.erp.common.easyui.Json;


/**
 * 初始化数据库使用
 * 
 * @author 孙宇
 * 
 */
//@Namespace("/")
//@Action
public class InitAction extends BaseAction {

	@Autowired
	private InitServiceI service;

	synchronized public void doNotNeedSessionAndSecurity_initDb() {
		Json j = new Json();
		service.initDb();
		j.setSuccess(true);
		writeJson(j);

		if (getSession() != null) {
			getSession().invalidate();
		}
	}

}
