package com.shuogesha.platform.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.platform.entity.User;

public interface UserDao { 
	
	void saveEntity(User bean);

	User findById(Long id);

	void updateById(User bean);
 
	void removeById(Long id);
	
	List<User> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	void updateOne(User bean);

	void addRole(User user);

	void removeRoleById(Long id);
}