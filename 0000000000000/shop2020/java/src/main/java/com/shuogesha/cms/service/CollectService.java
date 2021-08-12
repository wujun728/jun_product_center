package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.cms.entity.Collect;
import com.shuogesha.cms.entity.Praise;

public interface CollectService {
	Pagination getPage(String name,Long userId, int pageNo, int pageSize);
	
	Collect findById(Long id);

	void save(Collect bean);

	void update(Collect bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	Collect findByRefer(Collect bean);
	
	Collect findBy(Long userId,Long referid, String refer);
}