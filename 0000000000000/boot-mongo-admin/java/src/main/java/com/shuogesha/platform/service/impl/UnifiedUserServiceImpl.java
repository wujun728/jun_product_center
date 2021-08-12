package com.shuogesha.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.shuogesha.platform.dao.UnifiedUserDao;
import com.shuogesha.platform.entity.App;
import com.shuogesha.platform.entity.UnifiedUser;
import com.shuogesha.platform.service.UnifiedUserService;
import com.shuogesha.platform.service.UnifiedUserTokenService;
import com.shuogesha.platform.web.encoder.Md5PwdEncoder;
import com.shuogesha.platform.web.exception.BadCredentialsException;
import com.shuogesha.platform.web.exception.UsernameNotFoundException;
import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.web.util.RequestUtils;

@Service
public class UnifiedUserServiceImpl implements UnifiedUserService {

	@Autowired
	private UnifiedUserDao dao;

	@Autowired
	private Md5PwdEncoder pwdEncoder;
	
	@Autowired
	private UnifiedUserTokenService unifiedUserTokenService;

	@Override
	public UnifiedUser findById(String id) {
		return dao.findById(id);
	}

	@Override
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Pagination<App> page = new Pagination<App>(pageNo, pageSize, 0);   
		Query query = new Query(); 
 		if(StringUtils.isNotBlank(name)){
			query.addCriteria(Criteria.where("name").regex(name));
		} 
		return dao.findPage(page, query);
	}

	@Override
	public void save(UnifiedUser bean) {
		bean.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		dao.saveEntity(bean);
		//初始化自己
		
	}

	@Override
	public void update(UnifiedUser bean) {
		dao.updateEntityOne(bean, bean.getId());
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
	public UnifiedUser save(String nickName, String username, String password, String email, String phone, String sex,
			String ip, String type, String avatar) {
		String now = RequestUtils.getNow();
		UnifiedUser bean = new UnifiedUser();
		bean.setNickName(nickName);
		bean.setRealname(nickName);
		bean.setUsername(username);
		bean.setSex(sex);
		bean.setEmail(email);
		bean.setPhone(phone);
		bean.setAvatar(avatar);
		bean.setPassword(pwdEncoder.encodePassword(password));
		bean.setRegisterIp(ip);
		bean.setRegisterTime(now);
		bean.setLastLoginIp(ip);
		bean.setLastLoginTime(now);
		bean.setType(type);
		// 不强制验证邮箱直接激活
		bean.setActivation(true);
		save(bean);
		return bean;
	}

	@Override
	public void updatePassword(String password, String id) { 
		Update update = new Update();
 		update.set("password", pwdEncoder.encodePassword(password));
		Query query = new Query(); 
 		query.addCriteria(Criteria.where("_id").is(id));
		dao.update(query, update);
	}

	@Override
	public void activeLogin(UnifiedUser user, String ip) {
		updateLoginInfo(user.getId(), ip);
	}

	@Override
	public void updateLoginInfo(String uid, String ip) { 
		Update update = new Update();
		update.set("lastLoginIp", "ip");
		update.set("lastLoginTime", RequestUtils.getNow());
		Query query = new Query(); 
 		query.addCriteria(Criteria.where("_id").is(uid));
		dao.update(query, update);
 	}

	@Override
	public UnifiedUser login(String username, String password, String ip)
			throws UsernameNotFoundException, BadCredentialsException, BadCredentialsException {
		UnifiedUser user = findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("username not found: " + username);
		}
		pwdEncoder.encodePassword(password);
		if (!pwdEncoder.isPasswordValid(user.getPassword(), password)) {
			throw new BadCredentialsException("password invalid");
		}
		if (!user.isActivation()) {
			throw new BadCredentialsException("account not activated");
		}
		updateLoginInfo(user.getId(), ip);
		return user;
	}

	@Override
	public UnifiedUser findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Override
	public void update(String nickName, String avatar, String unifiedUserId) { 
		
		Update update = new Update();
		update.set("avatar", avatar);
		update.set("nickName", nickName);
		Query query = new Query(); 
 		query.addCriteria(Criteria.where("_id").is(unifiedUserId));
		dao.update(query, update);
	}

	@Override
	public boolean emailNotExist(String username) {
		UnifiedUser unifiedUser = dao.findByUsername(username);
		if (unifiedUser == null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean phoneNotExist(String username) {
		UnifiedUser unifiedUser = dao.findByUsername(username);
		if (unifiedUser == null) {
			return true;
		}
		return false;
	}

	@Override
	public UnifiedUser register(String nickName, String email, String phone, String password, String ip) {
		UnifiedUser bean = save(nickName, getRandomUsername(11), password, email, phone, "保密", ip, "User", "");
		return bean;
	}

	@Override
	public UnifiedUser registerType(String nickName, String email, String phone, String password, String ip,
			String type) {
		UnifiedUser bean = save(nickName, getRandomUsername(11), password, email, phone, "保密", ip, type, "");
		return bean;
	}

	public String getRandomUsername(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		while (true) {
			sb = new StringBuffer();
			for (int i = 0; i < length; i++) {
				int number = random.nextInt(base.length());
				sb.append(base.charAt(number));
			}
			if (dao.findByUsername(sb.toString()) == null) {
				break;
			}
		}
		return sb.toString();
	}

	@Override
	public void update(String id, String realname, String username, String password, String email, String phone,
			String sex, String avatar) {
		UnifiedUser bean = new UnifiedUser();
		bean.setId(id);
		bean.setNickName(realname);
		bean.setRealname(realname);
		bean.setUsername(username);
		bean.setSex(sex);
		bean.setEmail(email);
		bean.setAvatar(avatar);
		bean.setPhone(phone);
		if (StringUtils.isNotBlank(password)) {
			bean.setPassword(pwdEncoder.encodePassword(password));
		}
		dao.updateEntityOne(bean, bean.getId());
	}

	@Override
	public boolean usernameNotExist(String username) { 
		Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
		return dao.count(query) <= 0;
	}

	@Override
	public void updatePwdByIds(String[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			UnifiedUser bean = new UnifiedUser();
			bean.setId(ids[i]);
			bean.setPassword(pwdEncoder.encodePassword("123456"));
			dao.updateEntityOne(bean, bean.getId());
		}
	}

	@Override
	public UnifiedUser updateProfile(UnifiedUser unifiedUser) {
		dao.updateEntityOne(unifiedUser, unifiedUser.getId());
		return findById(unifiedUser.getId());
	}

//	@Override
//	public long findMax() {
////		return dao.findMax();
//	}

	@Override
	public void updateStatus(String[] ids, boolean disable) {
		for (int i = 0, len = ids.length; i < len; i++) {
			UnifiedUser bean = new UnifiedUser();
			bean.setId(ids[i]);
			bean.setDisabled(disable);
			if (disable) {

			}
			
			dao.updateEntityOne(bean,bean.getId());
		}
	}
	
	@Override
    public void logout(String appid, String uid) {
        String signature = unifiedUserTokenService.encryptSignature(uid.toString());
        unifiedUserTokenService.removeBySignature(appid, signature);
    }

}
