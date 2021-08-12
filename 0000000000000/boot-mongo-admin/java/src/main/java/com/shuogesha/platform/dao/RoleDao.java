package com.shuogesha.platform.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.shuogesha.platform.entity.Authentication;
import com.shuogesha.platform.entity.Menu;
import com.shuogesha.platform.entity.Role;
import com.shuogesha.platform.web.mongo.MongoBaseDao;

public interface RoleDao  extends MongoBaseDao<Role>{

	List<String> getAllPerms(List<String> ids);

	List<Menu> findMenu(List<String> ids); 
	
//	void saveEntity(Role bean);
//
//	Role findById(Long id);
//
//	void updateById(Role bean);
// 
//	void removeById(Long id);
//	
//	List<Role> queryList(Map<String, Object> map);
//
//	long count(Map<String, Object> map);
//
//	List<Role> findList();
//	
//	List<Role> findByUserId(Long id);
//
//	void addRolePerms(Role bean);
//
//	void removeRoleById(Long id);
//
//	void removeRoleUser(Map<String, Object> map);
//
//	void addRoleUser(Map<String, Object> map);
//
//	void removeRoleMenuById(Long id);
//
//	void addRoleMenus(Role bean);
//
//	long countAllperms(Map<String, Object> map);
//
//	List<Menu> getAllMenusByUser(Map<String, Object> map);
//
//	Set<String> getAllPermsByUser(Map<String, Object> map);
//	
//	Set<String> getAllPerms(Map<String, Object> map);

 }