package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.cms.entity.Foot;

public interface FootService {
	Pagination getPage(String name,Long userId, int pageNo, int pageSize);

	Foot findById(Long id);

	void save(Foot bean);

	void update(Foot bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);

	Foot saveByFoot(Foot foot);
}