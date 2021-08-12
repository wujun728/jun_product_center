package com.shuogesha.platform.web;

import javax.servlet.http.HttpServletRequest;

import com.shuogesha.platform.entity.Site;
import com.shuogesha.platform.entity.UnifiedUser;
import com.shuogesha.platform.entity.User;

/**
 * 提供一些CMS系统中使用到的共用方法
 * 
 * 比如获得会员信息,获得后台站点信息
 */
public class FrontUtils {
	/**
	 * 用户KEY
	 */
	public static final String USER_KEY = "_shuogesha_unified_user_key";
	/**
	 * 站点KEY
	 */
	public static final String SITE_KEY = "_shuogesha_unified_site_key";

	/**
	 * 获得用户
	 * 
	 * @param request
	 * @return
	 */
	public static UnifiedUser getUnifiedUser(HttpServletRequest request) {
		return (UnifiedUser) request.getAttribute(USER_KEY);
	}

	/**
	 * 获得用户ID
	 * 
	 * @param request
	 * @return
	 */
	public static Long getUnifiedUserId(HttpServletRequest request) {
		UnifiedUser user = getUnifiedUser(request);
		if (user != null) {
			return user.getId();
		} else {
			return null;
		}
	}

	/**
	 * 设置用户
	 * 
	 * @param request
	 * @param user
	 */
	public static void setUnifiedUser(HttpServletRequest request, UnifiedUser user) {
		request.setAttribute(USER_KEY, user);
	}

	/**
	 * 获得站点
	 * 
	 * @param request
	 * @return
	 */
	public static Site getSite(HttpServletRequest request) {
		return (Site) request.getAttribute(SITE_KEY);
	}

	/**
	 * 设置站点
	 * 
	 * @param request
	 * @param site
	 */
	public static void setSite(HttpServletRequest request, Site site) {
		request.setAttribute(SITE_KEY, site);
	}

	/**
	 * 获得站点ID
	 * 
	 * @param request
	 * @return
	 */
	public static Long getSiteId(HttpServletRequest request) {
		return getSite(request).getId();
	}
}
