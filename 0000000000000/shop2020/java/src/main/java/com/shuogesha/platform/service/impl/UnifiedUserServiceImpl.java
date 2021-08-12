package com.shuogesha.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.platform.dao.UnifiedUserDao;
import com.shuogesha.platform.entity.UnifiedUser;
import com.shuogesha.platform.service.AccountService;
import com.shuogesha.platform.service.UnifiedUserService;
import com.shuogesha.platform.service.UnifiedUserTokenService;
import com.shuogesha.platform.web.encoder.Md5PwdEncoder;
import com.shuogesha.platform.web.exception.BadCredentialsException;
import com.shuogesha.platform.web.exception.UsernameNotFoundException;
import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.web.util.RequestUtils;


@Service
public class UnifiedUserServiceImpl implements UnifiedUserService{
	
	@Autowired
	private UnifiedUserDao dao;
	
	@Autowired
	private Md5PwdEncoder pwdEncoder;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UnifiedUserTokenService unifiedUserTokenService;
	  
	
	@Override
	public UnifiedUser findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		long totalCount = dao.count(map);
		Pagination<UnifiedUser> page = new Pagination<UnifiedUser>(pageNo, pageSize, totalCount);
		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<UnifiedUser> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(UnifiedUser bean) {
		 dao.saveEntity(bean);
		 //初始化
		 accountService.init(bean.getId());
	}

	@Override
	public void update(UnifiedUser bean) { 
		 dao.updateOne(bean);
	}

	@Override
	public void removeById(Long id) {
		dao.removeById(id);
		accountService.removeById(id);
	}

	@Override
	public void removeByIds(Long[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			removeById(ids[i]);
		}
	}

	@Override
	public UnifiedUser save(String nickName, String username, String password,
			String email, String phone,String sex, String ip, String type,String avatar) {
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
	public void updatePassword(String password, Long id) {
		UnifiedUser bean = new UnifiedUser();
		bean.setId(id);
		bean.setPassword(pwdEncoder.encodePassword(password));
		dao.updateOne(bean);
	}

	@Override
	public void activeLogin(UnifiedUser user, String ip) {
		updateLoginInfo(user.getId(), ip);
	}

	@Override
	public void updateLoginInfo(Long uid, String ip) {
		UnifiedUser bean = new UnifiedUser();
		bean.setId(uid);
		bean.setLastLoginIp(ip);
		bean.setLastLoginTime(RequestUtils.getNow()); 
		dao.updateOne(bean);
	}

	@Override
	public UnifiedUser login(String username, String password, String ip) 
			throws UsernameNotFoundException, BadCredentialsException,
			BadCredentialsException {
		UnifiedUser user = findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("username not found: "
					+ username);
		}
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
	public void update(String nickName, String avatar, Long unifiedUserId) {
		UnifiedUser bean = new UnifiedUser();
		bean.setId(unifiedUserId);
		bean.setAvatar(avatar);
		bean.setNickName(nickName);
 		dao.updateOne(bean);
	}

	@Override
	public boolean emailNotExist(String username) { 
		UnifiedUser unifiedUser = dao.findByUsername(username);
		if (unifiedUser==null) {
			return true;
		}
		return false;
	} 

	@Override
	public boolean phoneNotExist(String username) {
		UnifiedUser unifiedUser = dao.findByUsername(username);
		if (unifiedUser==null) {
			return true;
		}
		return false;
	}
	
	@Override
	public UnifiedUser register(String nickName, String email, String phone,
			String password, String ip) {
		UnifiedUser bean = save(nickName,getRandomUsername(11), password, email, phone,"保密", ip,"User","");
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
	    	if(dao.findByUsername(sb.toString())==null){
	    		break;
	    	}
		}
	    return sb.toString();     
	}

	@Override
	public void update(Long id, String realname, String username,
			String password, String email, String phone, String sex,String avatar) {
		UnifiedUser bean = new UnifiedUser();
		bean.setId(id);
		bean.setNickName(realname);
		bean.setRealname(realname);
		bean.setUsername(username);
		bean.setSex(sex);
		bean.setEmail(email);
		bean.setAvatar(avatar);
		bean.setPhone(phone);
		if(StringUtils.isNotBlank(password)){
			bean.setPassword(pwdEncoder.encodePassword(password));
		}
		dao.updateOne(bean);
	}

	@Override
	public boolean usernameNotExist(String username) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("username", username);
 		return dao.countByUsername(map)<= 0;
	}

	@Override
	public void updatePwdByIds(Long[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			UnifiedUser bean = new UnifiedUser();
			bean.setId(ids[i]);
			bean.setPassword(pwdEncoder.encodePassword("123456"));
			dao.updateOne(bean);
		} 
	}

	@Override
    public UnifiedUser updateProfile(UnifiedUser unifiedUser) {
        dao.updateOne(unifiedUser);
        return findById(unifiedUser.getId());
    }
	
	@Override
	public long findMax() {
 		return dao.findMax();
	} 
	
	@Override
    public void logout(String appid, Long uid) {
        String signature = unifiedUserTokenService.encryptSignature(uid.toString());
        unifiedUserTokenService.removeBySignature(appid, signature);
    }

	@Override
	public void updateStatus(Long[] ids, boolean disable) {
		 for (int i = 0, len = ids.length; i < len; i++) {
	            UnifiedUser bean = new UnifiedUser();
	            bean.setId(ids[i]);
	            bean.setDisabled(disable);
	            if (disable) {
	                unifiedUserTokenService.removeByUid(ids[i]);
	            }
	            dao.updateStatus(bean); 
	        }
	}
}
