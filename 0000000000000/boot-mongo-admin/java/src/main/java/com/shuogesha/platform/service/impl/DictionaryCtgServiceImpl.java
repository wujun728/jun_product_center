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

import com.shuogesha.platform.dao.DictionaryCtgDao;
import com.shuogesha.platform.entity.App;
import com.shuogesha.platform.entity.DictionaryCtg;
import com.shuogesha.platform.service.DictionaryCtgService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class DictionaryCtgServiceImpl implements DictionaryCtgService{
	
	@Autowired
	private DictionaryCtgDao dao;

	@Override
	public DictionaryCtg findById(Long id) {
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
	public void save(DictionaryCtg bean) {
		 bean.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		 dao.saveEntity(bean);
	}

	@Override
	public void update(DictionaryCtg bean) { 
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
	public String findBycodeToName(String code, String value) { 
		Query query = new Query(); 
		query.addCriteria(Criteria.where("code").is(code)); 
		query.addCriteria(Criteria.where("value").is(value));  
		return dao.findBycodeToName(query);
	}

	@Override
	public boolean codeNotExist(String code) {
 		Query query = new Query(); 
		query.addCriteria(Criteria.where("code").is(code)); 
  		return dao.count(query) <= 0;
	}

	@Override
	public List<DictionaryCtg> findList() {
		return dao.findList();
	}

}
