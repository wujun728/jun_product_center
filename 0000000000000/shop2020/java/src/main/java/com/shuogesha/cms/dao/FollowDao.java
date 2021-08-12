package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.Follow;

public interface FollowDao { 
	
	void saveEntity(Follow bean);

	Follow findById(Long id);

	void updateById(Follow bean);
 
	void removeById(Long id);
	
	List<Follow> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	Follow findByFollw(Follow bean);

    List<Follow> queryFollowList(Map<String, Object> map);
}