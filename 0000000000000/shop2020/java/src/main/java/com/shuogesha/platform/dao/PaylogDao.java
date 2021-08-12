package com.shuogesha.platform.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.platform.entity.Paylog;

public interface PaylogDao { 
	
	void saveEntity(Paylog bean);

	Paylog findById(String id);

	void updateById(Paylog bean);
 
	void removeById(String id);
	
	List<Paylog> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);
}