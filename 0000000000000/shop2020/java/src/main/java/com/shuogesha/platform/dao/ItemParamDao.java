package com.shuogesha.platform.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.platform.entity.ItemParam;

public interface ItemParamDao { 
	
	void saveEntity(ItemParam bean);

	ItemParam findById(Long id);

	void updateById(ItemParam bean);
 
	void removeById(Long id);
	
	List<ItemParam> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	List<ItemParam> findAll();
}