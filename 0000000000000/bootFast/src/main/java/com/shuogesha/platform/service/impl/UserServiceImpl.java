package com.shuogesha.platform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.platform.dao.UserDao;
import com.shuogesha.platform.entity.Role;
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
	public User findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		long totalCount = dao.count(map);
		Pagination<User> page = new Pagination<User>(pageNo, pageSize, totalCount);
		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<User> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(User bean) {
		 dao.saveEntity(bean);
	}

	@Override
	public void update(User bean) { 
		 dao.updateById(bean);
		 //先删除后添加
		 dao.removeRoleById(bean.getId()); 
		 if(bean.getRoles()!=null){
			dao.addRole(bean);
		 } 
	}

	@Override
	public void removeById(Long id) {
		dao.removeById(id);
		unifiedUserService.removeById(id);
	}

	@Override
	public void removeByIds(Long[] ids) {
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
		dao.saveEntity(user);
		if(user.getRoles()!=null){
			dao.addRole(user);
		} 
	}

	@Override
	public boolean usernameNotExist(String username) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		return dao.count(map)<=0;
	}

	@Override
	public void update(User bean, String password, String email, Long id) {
		if (StringUtils.isNotBlank(password)) {
			unifiedUserService.updatePassword(password, id);
		} 
		dao.updateOne(bean);  
		dao.removeRoleById(bean.getId());
		if(bean.getRoles()!=null){
			dao.addRole(bean);
		} 
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
	public void update(Long id, String username, String realname,
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
		dao.updateOne(user);
		unifiedUserService.update(id,realname,username, password, email, phone, sex,"");
	}

}
