package com.shuogesha.platform.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.shuogesha.platform.dao.UserDao;
import com.shuogesha.platform.entity.App;
import com.shuogesha.platform.entity.UnifiedUser;
import com.shuogesha.platform.entity.User;
import com.shuogesha.platform.service.UnifiedUserService;
import com.shuogesha.platform.service.UserService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao dao;
	@Autowired
	private UnifiedUserService unifiedUserService;
	
	@Override
	public User findById(String id) {
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
	public void save(User bean) {
		 dao.saveEntity(bean);
	}

	@Override
	public void update(User bean) { 
		 dao.updateEntity(bean,bean.getId());  
	}

	@Override
	public void removeById(String id) {
		dao.removeById(id);
		unifiedUserService.removeById(id);
	}

	@Override
	public void removeByIds(String[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			removeById(ids[i]);
		}
	}

	@Override
	public void saveAdmin(User user, String username, String password,
			String email, String ip) {
		// 添加統一会员信息
		UnifiedUser bean = unifiedUserService.save(username,username, password, email,user.getPhone(),user.getSex(),
				ip,"Admin","");
		user.setUsername(username);
		user.setEmail(email);
		user.setId(bean.getId()); 
		user.setUnifiedUser(bean);
		dao.saveEntity(user); 
	}

	@Override
	public boolean usernameNotExist(String username) { 
		Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
		return dao.count(query)<=0;
	}

	@Override
	public void update(User bean, String password, String email, String id) {
		if (StringUtils.isNotBlank(password)) {
			unifiedUserService.updatePassword(password, id);
		} 
		dao.updateEntityOne(bean,bean.getId());   
	}

	@Override
	public User save(String username,String realname, String password, String email,
			String phone, String sex, String remark, String ip, String type) {
		// 添加統一会员信息
		UnifiedUser bean = unifiedUserService.save(realname,username, password, email,phone,sex,
				ip,type,"");
		User user= new User();
		user.setId(bean.getId());
		user.setAdmin(false);
		user.setUsername(username);
		user.setSex(sex);
		user.setEmail(email);
		user.setPhone(phone);
		user.setRealname(realname);
		user.setRemark(remark);
		dao.saveEntity(user);
 		return user;
	}

	@Override
	public void update(String id, String username, String realname,
			String password, String email, String phone, String sex,
			String remark) {
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setSex(sex);
		user.setEmail(email);
		user.setPhone(phone);
		user.setRealname(realname);
		user.setRemark(remark);
		dao.updateEntityOne(user,user.getId());
		unifiedUserService.update(id,realname,username, password, email, phone, sex,"");
		 
	}

}
