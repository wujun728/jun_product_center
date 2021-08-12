package com.shuogesha.platform.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.entity.ScheduleJob;

public interface ScheduleJobService {
	Pagination getPage(String name, int pageNo, int pageSize);

	ScheduleJob findById(String id);

	void save(ScheduleJob bean);

	void update(ScheduleJob bean);

	void removeById(String id);
	
	void removeByIds(String[] ids);
}