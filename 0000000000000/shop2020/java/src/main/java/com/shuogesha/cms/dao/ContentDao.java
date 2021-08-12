package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.Content;

public interface ContentDao { 
	
	void saveEntity(Content bean);

	Content findById(Long id);

	void updateById(Content bean);
 
	void removeById(Long id);
	
	List<Content> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);
}