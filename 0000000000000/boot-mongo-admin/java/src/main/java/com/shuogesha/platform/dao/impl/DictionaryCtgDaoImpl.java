package com.shuogesha.platform.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.shuogesha.platform.dao.DictionaryCtgDao;
import com.shuogesha.platform.entity.DictionaryCtg;
import com.shuogesha.platform.web.mongo.MongoBaseDaoImpl;

@Repository
public class DictionaryCtgDaoImpl extends MongoBaseDaoImpl<DictionaryCtg> implements DictionaryCtgDao {

	@Override
	public String findBycodeToName(Query query) {
		DictionaryCtg bean = this.findOne(query);
		if(bean!=null) {
			return bean.getName();
		}
		return null;
	}

	@Override
	public List<DictionaryCtg> findList() { 
		return this.find(new Query());
	}

}
