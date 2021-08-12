package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.Yaoqing;

public interface YaoqingDao { 
	
	void saveEntity(Yaoqing bean);

	Yaoqing findById(Long id);

	void updateById(Yaoqing bean);
 
	void removeById(Long id);
	
	List<Yaoqing> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	long countCode(String code);

	Yaoqing findByCode(String code);

	long countChild(Map<String, Object> map);

 }