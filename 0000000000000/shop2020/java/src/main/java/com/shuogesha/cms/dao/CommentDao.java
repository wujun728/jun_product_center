package com.shuogesha.cms.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.cms.entity.Comment;

public interface CommentDao { 
	
	void saveEntity(Comment bean);

	Comment findById(Long id);

	void updateById(Comment bean);
 
	void removeById(Long id);
	
	List<Comment> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);

	List<Comment> findList(Map<String, Object> map);

	void removeByNewsId(Map<String, Object> map);
}