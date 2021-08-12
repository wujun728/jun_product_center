package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.Message;

public interface MessageDao { 
	
	void saveEntity(Message bean);

	Message findById(Long id);

	void updateById(Message bean);
 
	void removeById(Long id);
	
	List<Message> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);
}