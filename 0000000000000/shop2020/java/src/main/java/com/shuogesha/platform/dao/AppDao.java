package com.shuogesha.platform.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.platform.entity.App;

public interface AppDao { 
	
	void saveEntity(App bean);

	App findById(String id);

	void updateById(App bean);
 
	void removeById(String id);
	
	List<App> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	long countById(String id);
}