package com.shuogesha.platform.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.platform.entity.UnifiedUser;

public interface UnifiedUserDao { 
	
	void saveEntity(UnifiedUser bean);

	UnifiedUser findById(Long id);

	void updateById(UnifiedUser bean);
 
	void removeById(Long id);
	
	List<UnifiedUser> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	void updateOne(UnifiedUser bean);

	UnifiedUser findByUsername(String username);

	long countByUsername(Map<String, Object> map);

	void saveProfile(Map<String, Object> map);
	
	long findMax();

	void updateStatus(UnifiedUser bean);
}