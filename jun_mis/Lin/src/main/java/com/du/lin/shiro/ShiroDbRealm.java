package com.du.lin.shiro;


import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.du.lin.bean.ShiroUser;
import com.du.lin.bean.User;
import com.du.lin.dao.RoleMapper;
import com.du.lin.dao.UserMapper;
import com.du.lin.utils.BeanUtil;



public class ShiroDbRealm extends AuthorizingRealm{
	
	@Resource
	private UserMapper userMapper;
	@Resource
	private RoleMapper roleMapper;
	@Autowired
	private BeanUtil beanUtil;
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		User user = (User) principals.getPrimaryPrincipal();
		info.addRole(user.getRole());
		return info;
	}
	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationtoken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationtoken;
		ShiroUser temp = new ShiroUser();
		temp.setUsername(token.getUsername());
		ShiroUser dbUser = userMapper.selectOne(temp);
		User user =beanUtil.toUser(dbUser);
		
		if (dbUser == null){
			throw new CredentialsException();
		}    
		SimpleAuthenticationInfo authinfo = new SimpleAuthenticationInfo(user, 
				dbUser.getPassword(), ByteSource.Util.bytes(dbUser.getSalt()), getName());
		return authinfo;
	}


	
}
