package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.Ad;

public interface AdDao { 
	
	void saveEntity(Ad bean);

	Ad findById(Long id);

	void updateById(Ad bean);
 
	void removeById(Long id);
	
	List<Ad> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	List<Ad> findList(Map<String, Object> map);
}