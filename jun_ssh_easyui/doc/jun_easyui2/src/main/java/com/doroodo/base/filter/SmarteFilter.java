package com.doroodo.base.filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

public class SmarteFilter extends StrutsPrepareAndExecuteFilter {
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) {
		HttpServletRequest request = (HttpServletRequest) req;
		if (request.getRequestURI().contains("/plug/ueditor")) {
			try {
				chain.doFilter(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				super.doFilter(req, res, chain);// 采用默认父类的拦截器，即 struts2
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
