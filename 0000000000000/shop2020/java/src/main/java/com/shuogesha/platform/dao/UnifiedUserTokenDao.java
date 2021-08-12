package com.shuogesha.platform.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.platform.entity.App;
import com.shuogesha.platform.entity.UnifiedUserToken;

public interface UnifiedUserTokenDao {  
	
	long count(Map<String, Object> map);

	void saveEntity(UnifiedUserToken memberToken);

	UnifiedUserToken findBySignature(Map<String, Object> map);

	void removeBySignature(Map<String, Object> map);
 
}