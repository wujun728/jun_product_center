package com.shuogesha.platform.dao;

import java.util.List;
import java.util.Map;

import com.shuogesha.platform.entity.JobLog;

public interface JobLogDao { 
	
	void saveEntity(JobLog bean);

	JobLog findById(Long id);

	void updateById(JobLog bean);
 
	void removeById(Long id);
	
	List<JobLog> queryList(Map<String, Object> map);

	long count(Map<String, Object> map);
}