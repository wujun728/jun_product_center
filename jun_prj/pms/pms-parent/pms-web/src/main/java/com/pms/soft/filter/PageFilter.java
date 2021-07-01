package com.pms.soft.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.soft.bean.SysUser;

public class PageFilter implements Filter {

	//定义不需要拦截的请求后缀
	private static List<String> excludesList = new ArrayList<String>();

	static {
		excludesList.add("jpg");
		excludesList.add("gif");
		excludesList.add("png");
		excludesList.add("js");
		excludesList.add("css");
		excludesList.add("/login.html");
		excludesList.add("/login");
	}

	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 
		HttpServletRequest req 	 =  (HttpServletRequest) request;
		HttpServletResponse res  =  (HttpServletResponse) response;
		String uri = req.getRequestURI();
		if (isExcluded(uri)) {
			chain.doFilter(request, response);
			return;
		}
		
		SysUser user  =  (SysUser)req.getSession().getAttribute("sessionUser");
		if(user==null) {
			res.sendRedirect("/login.html");
			return;
		}
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		 
	}

	private boolean isExcluded(String path) {
		for (String excludeStr : excludesList) {
			if (path.endsWith(excludeStr)) {
				return true;
			}
		}
		return false;
	}
}
