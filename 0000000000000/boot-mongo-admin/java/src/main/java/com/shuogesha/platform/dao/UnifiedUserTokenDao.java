package com.shuogesha.platform.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.query.Query;

import com.shuogesha.platform.entity.App;
import com.shuogesha.platform.entity.Authentication;
import com.shuogesha.platform.entity.UnifiedUserToken;
import com.shuogesha.platform.web.mongo.MongoBaseDao;

public interface UnifiedUserTokenDao  extends MongoBaseDao<UnifiedUserToken>{ 
	
//	long count(Map<String, Object> map);
//
//	void saveEntity(UnifiedUserToken memberToken);
//
//	UnifiedUserToken findBySignature(Map<String, Object> map);
//
//	void removeBySignature(Map<String, Object> map);
 
}