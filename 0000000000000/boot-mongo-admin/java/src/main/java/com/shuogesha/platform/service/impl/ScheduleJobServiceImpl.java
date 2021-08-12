package com.shuogesha.platform.service.impl;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.shuogesha.platform.dao.ScheduleJobDao;
import com.shuogesha.platform.entity.ScheduleJob;
import com.shuogesha.platform.service.ScheduleJobService;
import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.scheduler.QuartzUtil;


@Service
public class ScheduleJobServiceImpl implements ScheduleJobService{
	
	@Autowired
	private ScheduleJobDao dao;
	@Autowired
	private QuartzUtil quartzUtil; 

	@Override
	public ScheduleJob findById(String id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage(String name,int pageNo, int pageSize) { 
		Pagination<ScheduleJob> page = new Pagination<ScheduleJob>(pageNo, pageSize, 0);   
		Query query = new Query(); 
		if(StringUtils.isNotBlank(name)){
			query.addCriteria(Criteria.where("name").regex(name));
		} 
		return dao.findPage(page, query); 
	}

	@Override
	public void save(ScheduleJob bean) {
		bean.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		dao.saveEntity(bean);
		try {  
			if(ScheduleJob.ON.equals(bean.getStatus())){
				quartzUtil.addJob(bean); 
			} 
 		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	}

	@Override
	public void update(ScheduleJob bean) {  
		 try { 
			 quartzUtil.deleteJob(bean);
			 if(ScheduleJob.ON.equals(bean.getStatus())){
				 quartzUtil.addJob(bean); 
			 } 
 		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		dao.updateEntity(bean,bean.getId());
	}

	@Override
	public void removeById(String id) {
		quartzUtil.deleteJob(findById(id));
		dao.removeById(id); 
	}

	@Override
	public void removeByIds(String[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			removeById(ids[i]);
		}
	}

}
