package org.zhanghua.ssm.common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.zhanghua.ssm.common.Global;
import org.zhanghua.ssm.entity.sys.Resource;
import org.zhanghua.ssm.security.SystemAuthorizingRealm.Principal;
import org.zhanghua.ssm.service.sys.ResourceService;

/**
 * 权限菜单拦截器
 * 
 * @author Wujun
 * 
 */
public class AuthInterceptor implements HandlerInterceptor {

	@Autowired
	private ResourceService resourceService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Principal principal = (Principal) subject.getPrincipal();
		if (subject.isAuthenticated() || subject.isRemembered()) {
			Object obj = subject.getSession().getAttribute(Global.MENUS);
			if (obj == null) {
				List<Resource> menus = resourceService.queryMenus(principal.getUsername());
				subject.getSession().setAttribute(Global.MENUS, menus);
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

}
