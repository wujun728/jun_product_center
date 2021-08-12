package com.shuogesha.platform.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shuogesha.platform.web.exception.BadCredentialsException;
import com.shuogesha.platform.web.exception.UsernameNotFoundException;
import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.web.session.SessionProvider;
import com.shuogesha.platform.entity.Authentication;

public interface AuthenticationService {
	/**
	 * 认证信息session key
	 */
	public static final String AUTH_KEY = "shuogesha_auth_key";
	
	Pagination getPage(String name, int pageNo, int pageSize);

	Authentication findById(String id);

	void save(Authentication bean);

	void update(Authentication bean);

	void removeById(String id);
	
	void removeByIds(String[] ids);

	Long retrieveUserIdFromSession(SessionProvider session,
			HttpServletRequest request);

	Authentication login(String username, String password, String ip,
			HttpServletRequest request, HttpServletResponse response,
			SessionProvider session) throws UsernameNotFoundException,
			BadCredentialsException ;

	Authentication retrieve(String authId);
}