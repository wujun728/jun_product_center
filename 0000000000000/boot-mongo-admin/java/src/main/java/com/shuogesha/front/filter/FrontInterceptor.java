package com.shuogesha.front.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.shuogesha.platform.entity.Site;
import com.shuogesha.platform.entity.UnifiedUser;
import com.shuogesha.platform.service.AuthenticationService;
import com.shuogesha.platform.service.SiteService;
import com.shuogesha.platform.service.UnifiedUserService;
import com.shuogesha.platform.web.CmsUtils;
import com.shuogesha.platform.web.FrontUtils;
import com.shuogesha.platform.web.session.SessionProvider;
import com.shuogesha.platform.web.util.CookieUtils;

@Configuration
public class FrontInterceptor implements HandlerInterceptor {
	
	public static final String SITE_PARAM = "platform_param";
	public static final String SITE_COOKIE = "shuogesha_platform_cookie";


	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		Site site = getSite(request, response);
		FrontUtils.setSite(request, site);
		UnifiedUser unifiedUser = null;
		String userId = authenticationService.retrieveUserIdFromSession(
				session, request);
		if (userId != null) {
			unifiedUser = unifiedUserService.findById(userId);
			FrontUtils.setUnifiedUser(request, unifiedUser);
		} 
		return true;
	}
	
	private Site getSite(HttpServletRequest request,
			HttpServletResponse response) {
		Site site = getByParams(request, response);
		if (site == null) {
			site = siteService.findMaster();
		}
		if (site == null) {
			throw new RuntimeException("no site!");
		} else {
			return site;
		}
	}
	
	private Site getByParams(HttpServletRequest request,
			HttpServletResponse response) {
		String p = request.getParameter(SITE_PARAM);
		if (!StringUtils.isBlank(p)) {
			try {
				Site site = siteService.findByTplSolution(p);
				if (site != null) {
					// 若使用参数选择站点，则应该把站点保存至cookie中才好。
					CookieUtils.addCookie(request, response, SITE_COOKIE, site
							.getId().toString(), null, null);
					return site;
				}
			} catch (NumberFormatException e) {
				 
			}
		}
		return null;
	}
	

	@Autowired
	public AuthenticationService authenticationService;
	@Autowired
	private SessionProvider session;
	@Autowired
	public SiteService siteService;
	@Autowired
	public UnifiedUserService unifiedUserService;
}
