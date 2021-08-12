package com.shuogesha.platform.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.query.Query;

import com.shuogesha.platform.entity.Authentication;
import com.shuogesha.platform.entity.DictionaryCtg;
import com.shuogesha.platform.web.mongo.MongoBaseDao;

public interface DictionaryCtgDao  extends MongoBaseDao<DictionaryCtg>{

	String findBycodeToName(Query query);

	List<DictionaryCtg> findList(); 
	
//	void saveEntity(DictionaryCtg bean);
//
//	DictionaryCtg findById(Long id);
//
//	void updateById(DictionaryCtg bean);
// 
//	void removeById(Long id);
//	
//	List<DictionaryCtg> queryList(Map<String, Object> map);
//
//	long count(Map<String, Object> map);
//
//	String findBycodeToName(Map<String, Object> map);
//
//	long countByCode(Map<String, Object> map);
//
//	List<DictionaryCtg> findList();
}