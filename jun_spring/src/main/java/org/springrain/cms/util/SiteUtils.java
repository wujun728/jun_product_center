package org.springrain.cms.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class SiteUtils {
	
	private SiteUtils(){
		throw new IllegalAccessError("工具类不能实例化");
	}
	
	public static String getBaseURL(HttpServletRequest request){
		String path = request.getContextPath(); 
		if("/".equals(path)){
			path="";
		}
		String basePath = request.getScheme()+"://"+request.getServerName();
		if(80-request.getServerPort()!=0){
		  basePath=basePath+":"+request.getServerPort();
		}
		basePath=basePath+path;
		
		return basePath;
	}
	
	
	
	public static  String getRequestURL(HttpServletRequest request)throws Exception{
		StringBuffer uri=request.getRequestURL();
		String param=request.getQueryString();
		if(StringUtils.isNotBlank(param)){
			uri=uri.append("?").append(param);
		}
		return uri.toString();
	}
	
	
	
	
}
