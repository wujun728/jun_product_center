package com.erp.cxf.demo;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.erp.model.Users;


/**
 * WebService接口定义
 * 
 * @author 孙宇
 * 
 */
@WebService
public interface WebServiceDemoI {

	public String helloWs(@WebParam(name = "userName") String name);

	public Users getUser(@WebParam(name = "userId") String id);

}
