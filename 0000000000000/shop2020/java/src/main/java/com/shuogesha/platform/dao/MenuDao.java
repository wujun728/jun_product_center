package com.shuogesha.platform.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.platform.entity.Menu;

public interface MenuDao { 
	
	void saveEntity(Menu bean);

	Menu findById(Long id);

	void updateById(Menu bean);
 
	void removeById(Long id);
	
	List<Menu> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	List<Menu> findAll(Map<String, Object> map);

	List<Menu> queryMenuList(Map<String, Object> map);

	List<Menu> getAllMenus(Map<String, Object> map);
}