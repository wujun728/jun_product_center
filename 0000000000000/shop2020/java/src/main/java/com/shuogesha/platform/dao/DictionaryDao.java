package com.shuogesha.platform.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.platform.entity.Dictionary;

public interface DictionaryDao { 
	
	void saveEntity(Dictionary bean);

	Dictionary findById(Long id);

	void updateById(Dictionary bean);
 
	void removeById(Long id);
	
	List<Dictionary> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	List<Dictionary> findByCode(Map<String, Object> map);
}