package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.Code;

public interface CodeDao { 
	
	void saveEntity(Code bean);

	Code findById(Long id);

	void updateById(Code bean);
 
	void removeById(Long id);
	
	List<Code> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	Code findByName(Map<String, Object> map);
}