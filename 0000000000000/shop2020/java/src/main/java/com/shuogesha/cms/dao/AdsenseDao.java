package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.Adsense;

public interface AdsenseDao { 
	
	void saveEntity(Adsense bean);

	Adsense findById(Long id);

	void updateById(Adsense bean);
 
	void removeById(Long id);
	
	List<Adsense> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	List<Adsense> findAll();
}