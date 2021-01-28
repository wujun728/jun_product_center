package org.zhanghua.ssm.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.zhanghua.ssm.entity.sys.User;
import org.zhanghua.ssm.mapper.sys.UserMapper;
import org.zhanghua.ssm.security.SystemAuthorizingRealm.Principal;

public class UserUtils {

	private static UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);

	public static final String USER_CACHE = "userCache";

	/**
	 * 
	 * @Description 获取当前登录用户 @return @return User @throws
	 */
	public static User getUser() {
		// Object obj =
		// SecurityUtils.getSubject().getSession().getAttribute(Global.CURRENT_USER);
		// if (obj != null) {
		// return (User) obj;
		// }
		// String username = (String) SecurityUtils.getSubject().getPrincipal();
		// return userMapper.getByUsername(username);
		Principal principal = getPrincipal();
		return userMapper.getByUsername(principal.getUsername());
	}

	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static Principal getPrincipal() {
		try {
			Subject subject = getSubject();
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return principal;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
