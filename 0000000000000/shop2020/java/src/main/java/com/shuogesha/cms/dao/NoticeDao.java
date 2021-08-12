package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.Notice;

public interface NoticeDao { 
	
	void saveEntity(Notice bean);

	Notice findById(Long id);

	void updateById(Notice bean);
 
	void removeById(Long id);
	
	List<Notice> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);
}