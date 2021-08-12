package com.shuogesha.platform.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.entity.JobLog;

public interface JobLogService {
	Pagination getPage(String name, int pageNo, int pageSize);

	JobLog findById(String id);

	void save(JobLog bean);

	void update(JobLog bean);

	void removeById(String id);
	
	void removeByIds(String[] ids);
}