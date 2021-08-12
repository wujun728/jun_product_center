package com.shuogesha.platform.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.platform.entity.Authentication;

public interface AuthenticationDao { 
	
	void saveEntity(Authentication bean);

	Authentication findById(String id);

	void updateById(Authentication bean);
 
	void removeById(String id);
	
	List<Authentication> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	void deleteExpire(String dateline);

	void removeByUid(Long uid);
}