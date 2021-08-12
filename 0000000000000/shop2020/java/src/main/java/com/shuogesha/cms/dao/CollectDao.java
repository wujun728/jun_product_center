package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.Collect;

public interface CollectDao { 
	
	void saveEntity(Collect bean);

	Collect findById(Long id);

	void updateById(Collect bean);
 
	void removeById(Long id);
	
	List<Collect> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	Collect findBy(Map<String, Object> map);

	Collect findByRefer(Collect bean); 

    void removeByReferid(Long referid, String refer);
}