package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.cms.entity.Notice;

public interface NoticeService {
	Pagination getPage(String name, int pageNo, int pageSize);

	Notice findById(Long id);

	void save(Notice bean);

	void update(Notice bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);
}