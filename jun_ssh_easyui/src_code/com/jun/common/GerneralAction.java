package com.jun.common;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Wujun
 * @createTime   2011-3-27
 */
public interface GerneralAction{
	/**
	 * 获取Struts上下文
	 * @return
	 */
	public ActionContext getActionContext();
	/**
	 * 获取HttpSession对象
	 * @return Map
	 */
	@SuppressWarnings("unchecked")
	public Map getSession();
	
	/**
	 * 获取HttpSession对象
	 * @return HttpSession
	 */
	@SuppressWarnings("unchecked")
	public HttpSession getHttpSession();
	
	/**
	 * 获取request对象
	 */
	public HttpServletRequest getRequest();
	
	/**
	 * 获取response对象
	 */
	public HttpServletResponse getResponse();
	
	/**
	 * 获取PageContext对象
	 */
	public PageContext getPageContext();
	/**
	 * 获取项目的basePath,如：http://localhost:8080/MyOA/
	 */
	public String getBasePath();
	/**
	 * 设置页面不缓存并且向客户端输出Json
	 * @param message 要输出的json字符串
	 */
	public void setNoCacheAndWriteJson(String message) throws IOException;
}
