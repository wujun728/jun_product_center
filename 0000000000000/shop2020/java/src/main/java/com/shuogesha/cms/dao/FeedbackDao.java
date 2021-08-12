package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.Feedback;

public interface FeedbackDao { 
	
	void saveEntity(Feedback bean);

	Feedback findById(Long id);

	void updateById(Feedback bean);
 
	void removeById(Long id);
	
	List<Feedback> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);
}