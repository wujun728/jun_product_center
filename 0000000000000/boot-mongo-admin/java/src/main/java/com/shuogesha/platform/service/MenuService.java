package com.shuogesha.platform.service;

import com.shuogesha.platform.web.mongo.Pagination;

import java.util.List;

import com.shuogesha.platform.entity.Menu;

public interface MenuService {
	Pagination getPage(String name, int pageNo, int pageSize);

	Menu findById(String id);

	void save(Menu bean);

	void update(Menu bean);

	void removeById(String id);
	
	void removeByIds(String[] ids);

	List<Menu> queryMenuList(String pid);

	List<Menu> findAll(String pid);

	List<Menu> getAllMenus(String pid,String type);

	List<String> getAllPerms();
}