package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;

import java.util.List;

import com.shuogesha.cms.entity.Nav;

public interface NavService {
	Pagination getPage(String name, int pageNo, int pageSize);

	Nav findById(Long id);

	void save(Nav bean);

	void update(Nav bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	List<Nav> findList();
}