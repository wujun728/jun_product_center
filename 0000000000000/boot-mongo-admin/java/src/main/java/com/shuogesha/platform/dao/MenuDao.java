package com.shuogesha.platform.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.shuogesha.platform.entity.Authentication;
import com.shuogesha.platform.entity.Menu;
import com.shuogesha.platform.web.mongo.MongoBaseDao;

public interface MenuDao  extends MongoBaseDao<Menu>{

	void updateAdd(Query query, Update update);
	/**
	 * 获取全部权限
	 * @return
	 */
	List<String> getAllPerms(); 
	
//	void saveEntity(Menu bean);
//
//	Menu findById(Long id);
//
//	void updateById(Menu bean);
// 
//	void removeById(Long id);
//	
//	List<Menu> queryList(Map<String, Object> map);
//
//	long count(Map<String, Object> map);
//
//	List<Menu> findAll(Map<String, Object> map);
//
//	List<Menu> queryMenuList(Map<String, Object> map);
//
//	List<Menu> getAllMenus(Map<String, Object> map);
}