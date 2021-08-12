package com.shuogesha.platform.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.entity.App;

public interface AppService {
	Pagination getPage(String name, int pageNo, int pageSize);

	App findById(String id);

	void save(App bean);

	void update(App bean);

	void removeById(String id);
	
	void removeByIds(String[] ids);

	long countById(String appid);
}