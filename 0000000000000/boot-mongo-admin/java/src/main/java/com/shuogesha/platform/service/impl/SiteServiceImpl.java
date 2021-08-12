package com.shuogesha.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.shuogesha.platform.dao.SiteDao;
import com.shuogesha.platform.entity.App;
import com.shuogesha.platform.entity.Site;
import com.shuogesha.platform.service.SiteService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class SiteServiceImpl implements SiteService{
	
	@Autowired
	private SiteDao dao;

	@Override
	public Site findById(Long id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Pagination<App> page = new Pagination<App>(pageNo, pageSize, 0);   
		Query query = new Query(); 
 		if(StringUtils.isNotBlank(name)){
			query.addCriteria(Criteria.where("name").regex(name));
		} 
		return dao.findPage(page, query);
	}

	@Override
	public void save(Site bean) {
		 dao.saveEntity(bean);
	}

	@Override
	public void update(Site bean) { 
		 dao.updateEntity(bean,bean.getId());
	}

	@Override
	public void removeById(Long id) {
		dao.removeById(id);
	}

	@Override
	public void removeByIds(Long[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			removeById(ids[i]);
		}
	}

	@Override
	public Site findByTplSolution(String tplsolution) {
		Query query = new Query(); 
 		query.addCriteria(Criteria.where("tplsolution").is(tplsolution)); 
		return dao.findOne(query);
	}

	@Override
	public Site findMaster() {
		return dao.findOne(new Query());
	}

	@Override
	public void updateCountClearTime(String dateStr, Long id) { 
		Update update = new Update();
		update.set("countClearTime", dateStr);
 		Query query = new Query();  
		query.addCriteria(Criteria.where("_id").is(id)); 
		dao.update(query, update);
	}

	@Override
	public void updateCountCopyTime(String dateStr, Long id) { 
		Update update = new Update();
		update.set("countCopyTime", dateStr);
 		Query query = new Query();  
		query.addCriteria(Criteria.where("_id").is(id));  
	}

}
