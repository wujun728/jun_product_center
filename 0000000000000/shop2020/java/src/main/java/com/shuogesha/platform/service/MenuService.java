package com.shuogesha.platform.service;

import com.shuogesha.platform.web.mongo.Pagination;

import java.util.List;

import com.shuogesha.platform.entity.Menu;

public interface MenuService {
	Pagination getPage(String name, int pageNo, int pageSize);

	Menu findById(Long id);

	void save(Menu bean);

	void update(Menu bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	List<Menu> queryMenuList(Long pid);

	List<Menu> findAll(Long pid);

	List<Menu> getAllMenus(Long pid,String type);
}