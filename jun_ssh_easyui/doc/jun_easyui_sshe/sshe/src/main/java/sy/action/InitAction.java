package sy.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.model.easyui.Json;
import sy.service.InitServiceI;

/**
 * 初始化数据库使用
 * 
 * @author Wujun
 * 
 */
@Namespace("/")
@Action
public class InitAction extends BaseAction {

	@Autowired
	private InitServiceI service;

	/**
	 * 初始化数据库
	 */
	synchronized public void doNotNeedSessionAndSecurity_initDb() {
		Json j = new Json(); 
		service.initResourceType();
		service.initResourceDb("100");
		service.initResourceDb("101");
		service.initResourceDb("102");
		service.initResourceDb("103");
		service.initResourceDb("104");
		service.initResourceDb("105");
		service.initResourceDb("106");
		service.initResourceDb("107");
		service.initResourceDb("108");
		service.initResourceDb("109");
		service.initResourceDb("110");
		service.initResourceDb("111");
		service.initResourceDb("112");
		service.initResourceDb("113");
		service.initResourceDb("114");
		service.initResourceDb("115");
		service.initResourceDb("116");
		service.initResourceDb("117");
		
		service.initDb();
		j.setSuccess(true);
		writeJson(j);

		if (getSession() != null) {
			getSession().invalidate();
		}
	}

	/**
	 * 初始化数据库的数据
	 */
	synchronized public void doNotNeedSessionAndSecurity_initDbData() {
		Json j = new Json();

		service.initDbData();

		j.setSuccess(true);
		writeJson(j);

		if (getSession() != null) {
			getSession().invalidate();
		}
	}

}
