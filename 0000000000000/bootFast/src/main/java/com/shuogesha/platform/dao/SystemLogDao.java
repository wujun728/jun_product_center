package com.shuogesha.platform.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.platform.entity.SystemLog;

public interface SystemLogDao { 
	
	void saveEntity(SystemLog bean);

	SystemLog findById(Long id);

	void updateById(SystemLog bean);
 
	void removeById(Long id);
	
	List<SystemLog> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);
}