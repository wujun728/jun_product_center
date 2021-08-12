package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.Count;

public interface CountDao { 
	
	void saveEntity(Count bean);

	Count findById(Long id);

	void updateById(Count bean);
 
	void removeById(Long id);
	
	List<Count> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);
	/**
	 * 之间全部更新
	 * @param map
	 */
	void updateAll(Map<String, Object> map);

	Count findByRefer(Map<String, Object> map);
	/**
	 * 增加并更新
	 * @param map
	 */
	void updateAllAdd(Map<String, Object> map);
}