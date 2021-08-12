package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.cms.entity.Praise;

public interface PraiseService {
	Pagination getPage(String name, int pageNo, int pageSize);

	Praise findById(Long id);

	void save(Praise bean);

	void update(Praise bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	Praise findByRefer(Praise bean);

	Praise findBy(Long userId, Long referid, String refer);
}