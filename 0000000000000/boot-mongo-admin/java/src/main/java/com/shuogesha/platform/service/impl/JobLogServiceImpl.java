package com.shuogesha.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.shuogesha.platform.dao.JobLogDao;
import com.shuogesha.platform.service.JobLogService;
import com.shuogesha.platform.entity.JobLog; 
 
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class JobLogServiceImpl implements JobLogService{
	
	@Autowired
	private JobLogDao dao;

	@Override
	public JobLog findById(String id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage(String name,int pageNo, int pageSize) { 
		Pagination<JobLog> page = new Pagination<JobLog>(pageNo, pageSize, 0);   
		Query query = new Query(); 
		if(StringUtils.isNotBlank(name)){
			query.addCriteria(Criteria.where("name").regex(name));
		} 
		return dao.findPage(page, query); 
	}

	@Override
	public void save(JobLog bean) {
		bean.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		dao.saveEntity(bean);
	}

	@Override
	public void update(JobLog bean) { 
		 dao.updateEntity(bean,bean.getId());
	}

	@Override
	public void removeById(String id) {
		dao.removeById(id);
	}

	@Override
	public void removeByIds(String[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			removeById(ids[i]);
		}
	}

}
