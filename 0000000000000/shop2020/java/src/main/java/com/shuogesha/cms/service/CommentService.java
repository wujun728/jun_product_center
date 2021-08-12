package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;

import java.util.List;

import com.shuogesha.cms.entity.Comment;

public interface CommentService {
	Pagination getPage(String name,String refer, Long referid,int pageNo, int pageSize);

	Comment findById(Long id);

	void save(Comment bean);

	void update(Comment bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	List<Comment> findList(String refer, Long referid, Integer parent);

	void removeByNewsId(String refer, Long referid);
}