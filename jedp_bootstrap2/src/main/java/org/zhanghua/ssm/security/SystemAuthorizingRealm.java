package org.zhanghua.ssm.security;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zhanghua.ssm.common.security.Encodes;
import org.zhanghua.ssm.entity.sys.Resource;
import org.zhanghua.ssm.entity.sys.User;
import org.zhanghua.ssm.service.sys.ResourceService;
import org.zhanghua.ssm.service.sys.UserService;

import com.google.common.base.Objects;

/**
 * 扩展AuthorizingRealm
 * 
 * 实现登录数据Realm和password加密
 * 
 * 
 * @author Wujun
 * 
 */
public class SystemAuthorizingRealm extends AuthorizingRealm {

	protected static Logger logger = LoggerFactory.getLogger(SystemAuthorizingRealm.class);

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private UserService userService;

	/**
	 * 鉴权的时候调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) principals.getPrimaryPrincipal();
		List<Resource> permissions = resourceService.queryPermissions(principal.getUsername());
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		for (Resource r : permissions) {
			if (StringUtils.isNotBlank(r.getPermission())) {
				authorizationInfo.addStringPermission(r.getPermission());
			}
		}
		return authorizationInfo;

	}

	/**
	 * 登录验证数据正确性
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;
		// 通过表单接收的用户名
		String username = token.getUsername();
		// 查询用户
		User user = userService.getByUsername(username);
		if (null == user) {
			logger.debug("没有找到帐号");
			throw new UnknownAccountException(); // 没有找到帐号
		}
		if (1 == user.getIsLock()) {
			logger.debug("帐号锁定");
			throw new LockedAccountException(); // 帐号锁定
		}
		// 解密盐
		byte[] salt = Encodes.decodeHex(user.getSalt());
		return new SimpleAuthenticationInfo(new Principal(user.getId(),user.getUsername(), user.getNickname()), user.getPassword(), ByteSource.Util.bytes(salt), getName());
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class Principal implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;
		
		private String id; //用户Id
		private String username; // 用户名 用于登录
		private String name; // 姓名 用于显示

		public Principal(String id,String username, String name) {
			this.id = id;
			this.username = username;
			this.name = name;
		}
		
		public String getId(){
			return id;
		}

		public String getName() {
			return name;
		}

		public String getUsername() {
			return username;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return username;
		}

		/**
		 * 重载hashCode,只计算loginName;
		 */
		@Override
		public int hashCode() {
			return Objects.hashCode(username);
		}

		/**
		 * 重载equals,只计算loginName;
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Principal other = (Principal) obj;
			if (username == null) {
				if (other.username != null) {
					return false;
				}
			} else if (!username.equals(other.username)) {
				return false;
			}
			return true;
		}
	}

}
