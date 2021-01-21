package org.myframework.support.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class Security2Utils {

	/**
	 * 得到Shiro会话登陆用户
	 *
	 * @return
	 */
	public static IUser getLoginUser() {
		Subject subject = null;
		try {
			subject = SecurityUtils.getSubject();
		} catch (Exception e) {
			return null;
		}
		if (subject != null)
			return (IUser) subject.getPrincipal();
		return null;
	}

	public static String getUserId() {
		IUser user = getLoginUser();
		return user == null ? null : user.getId();
	}

	/**
	 * 得到Shiro主体对象
	 *
	 * @return
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * 得到会话对象
	 *
	 * @return
	 */
	public static Session getSession() {
		if (SecurityUtils.getSubject() != null) {
			return SecurityUtils.getSubject().getSession();
		}
		return null;
	}
}
