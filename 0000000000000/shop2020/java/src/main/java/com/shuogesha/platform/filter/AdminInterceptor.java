package com.shuogesha.platform.filter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.Authentication;
import com.shuogesha.platform.entity.Site;
import com.shuogesha.platform.entity.User;
import com.shuogesha.platform.service.AuthenticationService;
import com.shuogesha.platform.service.SiteService;
import com.shuogesha.platform.service.UserService;
import com.shuogesha.platform.web.CmsUtils;
import com.shuogesha.platform.web.session.SessionProvider;
import com.shuogesha.platform.web.util.CookieUtils;
import com.shuogesha.platform.web.util.ResponseUtils;

@Configuration
public class AdminInterceptor implements HandlerInterceptor {
	public static final String SITE_PARAM = "platform_param";
	public static final String SITE_COOKIE = "shuogesha_platform_cookie";
	public static final String PERMISSION_KEY = "_permission_key";

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
 		Site site = getSite(request, response);
		CmsUtils.setSite(request, site);
		User user = null;
		Long userId = authenticationService.retrieveUserIdFromSession(session, request);
//		// userId如果是空
//		if (userId == null) {
//			String authId = request.getHeader("ssid");
//			if (StringUtils.isNotBlank(authId)) {
//				Authentication auth = authenticationService.findById(authId);
//				if (auth != null) {
//					userId = auth.getUid();
//				}
//			}
//		}
		// userId如果是空
		if (userId == null) {
			String authId = request.getHeader("shuogesha_auth_id");
			if (StringUtils.isNotBlank(authId)) {
				Authentication auth = authenticationService.findById(authId);
				if (auth != null) {
					userId = auth.getUid();
				}
			} 
		}
		if (userId != null) {
			user = userService.findById(userId);
			CmsUtils.setUser(request, user);
		}
		if (!isAuth()) {// 是否开启认证
			return true;
		}
		if (user == null) {
			ResponseUtils.renderJson(response, new JsonResult(ResultCode.INVALID_AUTHCODE, "授权认证失败",null).toString());
			return false;
		}
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setHeader("Access-Control-Allow-Methods","POST");
		response.setHeader("Access-Control-Allow-Headers","Access-Control");
		response.setHeader("Allow","POST");
		return true;
	}

	private Site getSite(HttpServletRequest request, HttpServletResponse response) {
		Site site = getByParams(request, response);
		if (site == null) {
			site = getByCookie(request);
		}
		// TODO 多站时候用
//		if (site == null&&CmsUtils.getUser(request)!=null) {
//			site = CmsUtils.getUser(request).getSite();
//		}
		if (site == null) {
			site = siteService.findMaster();
		}
		if (site == null) {
			throw new RuntimeException("no site!");
		} else {
			return site;
		}
	}

	private Site getByParams(HttpServletRequest request, HttpServletResponse response) {
		String p = request.getParameter(SITE_PARAM);
		if (!StringUtils.isBlank(p)) {
			try {
				Site site = siteService.findByTplSolution(p);
				if (site != null) {
					// 若使用参数选择站点，则应该把站点保存至cookie中才好。
					CookieUtils.addCookie(request, response, SITE_COOKIE, site.getId().toString(), null, null);
					return site;
				}
			} catch (NumberFormatException e) {

			}
		}
		return null;
	}

	private Site getByCookie(HttpServletRequest request) {
		Cookie cookie = CookieUtils.getCookie(request, SITE_COOKIE);
		if (cookie != null) {
			String v = cookie.getValue();
			if (!StringUtils.isBlank(v)) {
				try {
					return siteService.findById(Long.valueOf(v));
				} catch (NumberFormatException e) {

				}
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
	public UserService userService;

	public static String admin;

	public boolean auth = true;//开启认证
	private String[] excludeUrls = new String[] { "/login", "/logout" };
	private String loginUrl;
	private String processUrl;
	private String returnUrl;

	public boolean isAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	public String[] getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(String[] excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getProcessUrl() {
		return processUrl;
	}

	public void setProcessUrl(String processUrl) {
		this.processUrl = processUrl;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

}
