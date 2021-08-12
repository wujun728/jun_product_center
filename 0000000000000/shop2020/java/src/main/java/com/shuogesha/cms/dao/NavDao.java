package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.Nav;

public interface NavDao { 
	
	void saveEntity(Nav bean);

	Nav findById(Long id);

	void updateById(Nav bean);
 
	void removeById(Long id);
	
	List<Nav> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	List<Nav> findList();
}