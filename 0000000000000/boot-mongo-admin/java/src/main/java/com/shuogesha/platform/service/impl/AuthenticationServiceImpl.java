package com.shuogesha.platform.service.impl;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.shuogesha.platform.dao.AuthenticationDao;
import com.shuogesha.platform.entity.App;
import com.shuogesha.platform.entity.Authentication;
import com.shuogesha.platform.entity.UnifiedUser;
import com.shuogesha.platform.service.AuthenticationService;
import com.shuogesha.platform.service.UnifiedUserService;
import com.shuogesha.platform.web.exception.BadCredentialsException;
import com.shuogesha.platform.web.exception.UsernameNotFoundException;
import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.web.session.SessionProvider;
import com.shuogesha.platform.web.util.CookieUtils;


@Service
public class AuthenticationServiceImpl implements AuthenticationService{
	
	@Autowired
	private AuthenticationDao dao;
	@Autowired
	private UnifiedUserService unifiedUserService;
	@Override
	public Authentication findById(String id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Pagination<App> page = new Pagination<App>(pageNo, pageSize, 0);   
		Query query = new Query(); 
		if(StringUtils.isNotBlank(name)){
			query.addCriteria(Criteria.where("name").regex(name));
		} 
		return dao.findPage(page, query); 
	}

	@Override
	public void save(Authentication bean) {
		 if(bean.getUid()!=null) {
			 dao.removeByUid(bean.getUid());
		 }
		 bean.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		 bean.setLoginTime(new Date());
		 bean.setUpdateTime(new Date());
		 dao.saveEntity(bean);
	}

	@Override
	public void update(Authentication bean) { 
		 dao.updateEntity(bean,bean.getId());
	}

	@Override
	public void removeById(String id) {
		dao.removeById(id);
	}

	@Override
	public void removeByIds(String[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			removeById(ids[i]);
		}
	}

	@Override
	public String retrieveUserIdFromSession(SessionProvider session,
			HttpServletRequest request) {
		String authId = (String) session.getAttribute(request, AUTH_KEY); 
		if (authId == null) {
			Cookie cookie=CookieUtils.getCookie(request, "ssid"); 
			if(cookie!=null&&cookie.getValue()!=null){
				authId=cookie.getValue(); 
			}else{
				return null;
			} 
		} 
		Authentication auth = retrieve(authId);
		if (auth == null) {
			return null;
		}
		return auth.getUid();
	}
	
	public Authentication retrieve(String authId) {
		long current = System.currentTimeMillis();
		// 是否刷新数据库
//		if (refreshTime < current) {
//			refreshTime = getNextRefreshTime(current, interval);
//			String dateline = RequestUtils.getDateStr(new Date(current - timeout));
//			dao.deleteExpire(dateline);
//		}
		Authentication auth = findById(authId);
		if (auth != null && (auth.getUpdateTime().getTime() + timeout) > current) {
			auth.setUpdateTime(new Date(current));
			dao.updateEntity(auth,auth.getId());
			return auth;
		} else {
			return null;
		}
	}
	
	public Authentication login(String username, String password, String ip,
			HttpServletRequest request, HttpServletResponse response,
			SessionProvider session) throws UsernameNotFoundException,
			BadCredentialsException {
		UnifiedUser unifiedUser = unifiedUserService.login(username, password, ip);
		Authentication auth = new Authentication();
		auth.setUid(unifiedUser.getId());
		auth.setUsername(unifiedUser.getUsername());
		auth.setEmail(unifiedUser.getEmail());
		auth.setPhone(unifiedUser.getPhone());
		auth.setLoginIp(ip); 
		save(auth);
		session.setAttribute(request, response, AUTH_KEY, auth.getId());
		return auth;
	}
	
	public Authentication activeLogin(UnifiedUser unifiedUser, String ip,
			HttpServletRequest request, HttpServletResponse response,
			SessionProvider session) {
		unifiedUserService.activeLogin(unifiedUser, ip);
		Authentication auth = new Authentication();
		auth.setUid(unifiedUser.getId());
		auth.setUsername(unifiedUser.getUsername());
		auth.setEmail(unifiedUser.getEmail());
		auth.setPhone(unifiedUser.getPhone());
		auth.setLoginIp(ip);
		save(auth);
		session.setAttribute(request, response, AUTH_KEY, auth.getId());
		return auth;
	}

	
	// 过期时间
		private int timeout = 24*30 * 60 * 1000; // 30分钟

		// 间隔时间
		private int interval = 10 * 60 * 1000; // 10分钟

		// 刷新时间。
		private long refreshTime = getNextRefreshTime(System.currentTimeMillis(),
				this.interval);
		
		/**
		 * 设置认证过期时间。默认30分钟。
		 * 
		 * @param timeout
		 *            单位分钟
		 */
		public void setTimeout(int timeout) {
			this.timeout = timeout * 60 * 1000;
		}

		/**
		 * 设置刷新数据库时间。默认10分钟。
		 * 
		 * @param interval
		 *            单位分钟
		 */
		public void setInterval(int interval) {
			this.interval = interval * 60 * 1000;
			this.refreshTime = getNextRefreshTime(System.currentTimeMillis(),
					this.interval);
		}

		/**
		 * 获得下一个刷新时间。
		 * 
		 * 
		 * 
		 * @param current
		 * @param interval
		 * @return 随机间隔时间
		 */
		private long getNextRefreshTime(long current, int interval) {
			return current + interval;
			// 为了防止多个应用同时刷新，间隔时间=interval+RandomUtils.nextInt(interval/4);
			// return current + interval + RandomUtils.nextInt(interval / 4);
		}

}
