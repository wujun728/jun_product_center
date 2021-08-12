package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.cms.entity.Message;

public interface MessageService {
	Pagination getPage(String name,Long userId, int pageNo, int pageSize);

	Message findById(Long id);

	void save(Message bean);

	void update(Message bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids); 

	void saveSend(Message bean, Boolean send);
}