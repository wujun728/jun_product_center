package com.shuogesha.platform.service;

import com.shuogesha.platform.entity.ScheduleJob;
import com.shuogesha.platform.web.mongo.Pagination;

public interface ScheduleJobService {
	Pagination getPage(String name, int pageNo, int pageSize);

	ScheduleJob findById(Long id);

	void save(ScheduleJob bean);

	void update(ScheduleJob bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);
	
	void init();

	void pauseByIds(Long[] ids);
	
	void resumeByIds(Long[] ids);

	void startByIds(Long[] ids);
}