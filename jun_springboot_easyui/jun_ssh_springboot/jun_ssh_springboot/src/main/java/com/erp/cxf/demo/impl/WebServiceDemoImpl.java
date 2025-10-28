package com.erp.cxf.demo.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.erp.cxf.demo.WebServiceDemoI;
import com.erp.model.Users;
import com.erp.service.UserService;

/**
 * WebService接口实现类
 * 
 * @author 孙宇
 * 
 */
@WebService(endpointInterface = "com.erp.cxf.demo.WebServiceDemoI", serviceName = "webServiceDemo")
public class WebServiceDemoImpl implements WebServiceDemoI {

	@Autowired
	private UserService userService;

	@Override
	public String helloWs(String name) {
		if (name == null || name.trim().equals("")) {
			name = "SunYu";
		}
		String str = "hello[" + name.trim() + "]WebService test ok!";
		System.out.println(str);
		return str;
	}

	@Override
	public Users getUser(String id) {
		if (id == null || id.trim().equals("")) {
			id = "0";
		}
		Users user = null;//userService.getById(id.trim());
		Users u = new Users();
		u.setName(user.getName());
		u.setAccount(user.getAccount());
		return u;
	}

}
