package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.Foot;

public interface FootDao { 
	
	void saveEntity(Foot bean);

	Foot findById(Long id);

	void updateById(Foot bean);
 
	void removeById(Long id);
	
	List<Foot> queryList(Map<String, Object> map);

	long count(Map<String, Object> map); 
	
	Foot findByRefer(Foot foot);
}