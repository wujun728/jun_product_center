package com.shuogesha.platform.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.platform.dao.ScheduleJobDao;
import com.shuogesha.platform.entity.ScheduleJob;
import com.shuogesha.platform.service.ScheduleJobService;
import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.scheduler.QuartzManager;


@Service
public class ScheduleJobServiceImpl implements ScheduleJobService{
	
	@Autowired
	private ScheduleJobDao dao;
	@Autowired
	private QuartzManager quartzManager; 
	@Override
	public ScheduleJob findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		long totalCount = dao.count(map);
		Pagination<ScheduleJob> page = new Pagination<ScheduleJob>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<ScheduleJob> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(ScheduleJob bean) { 
		 bean.setDateline(new Date());
		 dao.saveEntity(bean);
		 try {
			quartzManager.addJob(bean.getName(), Class.forName(bean.getClassName()), bean.getCronExpression(),bean);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	}

	@Override
	public void update(ScheduleJob bean) { 
		 quartzManager.removeJob(dao.findById(bean.getId()).getName()); 
		 dao.updateById(bean);
		 try {
			quartzManager.addJob(bean.getName(), Class.forName(bean.getClassName()), bean.getCronExpression(),bean);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	}

	@Override
	public void removeById(Long id) {
 		quartzManager.removeJob(dao.findById(id).getName());
		dao.removeById(id);
	}

	@Override
	public void removeByIds(Long[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			if(ids[i]!=null&&ids[i]>0){
				removeById(ids[i]);
			} 
		}
	}
	
	@Override
	public void pauseByIds(Long[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			if(ids[i]!=null&&ids[i]>0){
 				ScheduleJob bean=findById(ids[i]);
 				if(bean!=null) {
 					quartzManager.pauseJob(bean.getName(), quartzManager.JOB_GROUP_NAME); 
 					bean.setStatus("2");
 	 				update(bean);
 				} 
			} 
		}
	}
	
	@Override
	public void resumeByIds(Long[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			if(ids[i]!=null&&ids[i]>0){
 				ScheduleJob bean=findById(ids[i]);
 				if(bean!=null) {
 					quartzManager.resumeJob(bean.getName(), quartzManager.JOB_GROUP_NAME); 	
 					bean.setStatus("1");
 	 				update(bean);
 				} 
			} 
		}
	}
	
	@Override
	public void startByIds(Long[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			if(ids[i]!=null&&ids[i]>0){
 				ScheduleJob bean=findById(ids[i]);
 				if(bean!=null) {
 					quartzManager.runAJobNow(bean.getName(), quartzManager.JOB_GROUP_NAME); 	
 					bean.setStatus("1");
 	 				update(bean);
 				} 
			} 
		}
	}
	
	@Override
	public void init() {
		 List<ScheduleJob> list = dao.getList();
		 if(list!=null&&list.size()>0){
			 for (ScheduleJob bean : list) {
				 try {
					quartzManager.addJob(bean.getName(), Class.forName(bean.getClassName()), bean.getCronExpression(),bean);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}    
			}
		 }
	}

}
