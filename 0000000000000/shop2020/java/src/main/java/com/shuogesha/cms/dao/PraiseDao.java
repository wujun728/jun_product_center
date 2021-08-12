package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.Praise;

public interface PraiseDao { 
	
	void saveEntity(Praise bean);

	Praise findById(Long id);

	void updateById(Praise bean);
 
	void removeById(Long id);
	
	List<Praise> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	Praise findByRefer(Praise bean);

	Praise findBy(Map<String, Object> map);
}