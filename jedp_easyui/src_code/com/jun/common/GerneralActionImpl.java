package com.jun.common;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.StrutsUtil;

import com.jun.util.SSHFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 当前包下所有Action的工厂类
 * @author Wujun
 * @createTime   2011-3-27
 */
public class GerneralActionImpl extends ActionSupport implements GerneralAction{
	protected SSHFactory sshFactory;
	public SSHFactory getSshFactory() {
		return sshFactory;
	}
	public void setSshFactory(SSHFactory sshFactory) {
		this.sshFactory = sshFactory;
	}
	/**
	 * 获取Struts上下文
	 * @return
	 */
	public ActionContext getActionContext(){
		return ActionContext.getContext();
	}
	/**
	 * 获取HttpSession对象
	 * @return Map
	 */
	@SuppressWarnings("unchecked")
	public Map getSession(){
		return this.getActionContext().getSession();
	}
	
	/**
	 * 获取HttpSession对象
	 * @return HttpSession
	 */
	@SuppressWarnings("unchecked")
	public HttpSession getHttpSession(){
		return this.getRequest().getSession();
	}
	
	/**
	 * 获取request对象
	 */
	public HttpServletRequest getRequest(){
		return (HttpServletRequest)this.getActionContext().get(ServletActionContext.HTTP_REQUEST);  
	}
	
	/**
	 * 获取response对象
	 */
	public HttpServletResponse getResponse(){
		return (HttpServletResponse)this.getActionContext().get(ServletActionContext.HTTP_RESPONSE);  
	}
	
	/**
	 * 获取PageContext对象
	 */
	public PageContext getPageContext(){
		return ServletActionContext.getPageContext();
	}
	
	/**
	 * 获取项目的basePath,如：http://localhost:8080/MyOA/
	 */
	public String getBasePath(){
		return getRequest().getScheme() + "://"+getRequest().getServerName() + 
		       ":"+getRequest().getServerPort() + getRequest().getContextPath() + "/";
	}
	
	/**
	 * 设置页面不缓存并且向客户端输出Json
	 * @param message 要输出的json字符串
	 */
	public void setNoCacheAndWriteJson(String message) throws IOException{
		getResponse().setCharacterEncoding("UTF-8");
		getResponse().setHeader("Pragma","No-cache"); 
		getResponse().setHeader("Cache-Control","no-cache"); 
		getResponse().setDateHeader("Expires", 0);
		getResponse().setContentType("application/json");
		getResponse().getWriter().write(message);
	}
}
