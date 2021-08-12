package com.shuogesha.platform.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.platform.entity.DictionaryCtg;

public interface DictionaryCtgDao { 
	
	void saveEntity(DictionaryCtg bean);

	DictionaryCtg findById(Long id);

	void updateById(DictionaryCtg bean);
 
	void removeById(Long id);
	
	List<DictionaryCtg> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	String findBycodeToName(Map<String, Object> map);

	long countByCode(Map<String, Object> map);

	List<DictionaryCtg> findList();
}