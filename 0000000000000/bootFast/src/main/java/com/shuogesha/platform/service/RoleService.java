package com.shuogesha.platform.service;

import java.util.List;
import java.util.Set;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.entity.Menu;
import com.shuogesha.platform.entity.Role;

public interface RoleService {
	Pagination getPage(String name, int pageNo, int pageSize);

	Role findById(Long id);

	void save(Role bean);

	void update(Role bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	List<Role> getList();

	void updateRoleUser(Long id, List<Integer> list); 
	
	void saveRoleMenus(Role bean);

	List<Menu> getAllMenusByUser(Long userId,Integer type);

	Set<String> getAllPermsByUser(Long userId); 
}