package com.shuogesha.platform.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.shuogesha.platform.dao.DictionaryCtgDao;
import com.shuogesha.platform.dao.DictionaryDao;
import com.shuogesha.platform.entity.App;
import com.shuogesha.platform.entity.Dictionary;
import com.shuogesha.platform.entity.DictionaryCtg;
import com.shuogesha.platform.service.DictionaryService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class DictionaryServiceImpl implements DictionaryService{
	
	@Autowired
	private DictionaryDao dao;
	
	@Autowired
	private DictionaryCtgDao dictionaryCtgDao;


	@Override
	public Dictionary findById(String id) {
		return dao.findById(id);
	}
	
	@Override
	public Pagination getPage(String ctgId, String name,int pageNo, int pageSize) { 
		Pagination<App> page = new Pagination<App>(pageNo, pageSize, 0);   
		Query query = new Query(); 
		query.addCriteria(Criteria.where("ctgId").is(ctgId));
		if(StringUtils.isNotBlank(name)){
			query.addCriteria(Criteria.where("name").regex(name));
		} 
		return dao.findPage(page, query);
	}

	@Override
	public void save(Dictionary bean) {
		 bean.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		 dao.saveEntity(bean);
	}

	@Override
	public void update(Dictionary bean) { 
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

	@Override
	public List<Dictionary> findByCode(String code) { 
		Query query = new Query(); 
		query.addCriteria(Criteria.where("code").is(code));
		
		DictionaryCtg dictionaryCtg=dictionaryCtgDao.findOne(query);
		if(dictionaryCtg!=null) {
			query = new Query(); 
			query.addCriteria(Criteria.where("ctgId").is(dictionaryCtg.getId()));
	 		return dao.find(query);
		}
		return null;
	}

}
