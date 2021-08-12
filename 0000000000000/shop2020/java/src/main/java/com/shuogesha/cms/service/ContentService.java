package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.cms.entity.Content;

public interface ContentService {
	Pagination getPage(String name,Integer channelId, int pageNo, int pageSize);

	Content findById(Long id);

	void save(Content bean);

	void update(Content bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);
}