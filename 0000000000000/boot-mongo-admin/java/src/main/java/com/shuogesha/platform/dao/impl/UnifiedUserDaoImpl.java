package com.shuogesha.platform.dao.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.shuogesha.platform.dao.UnifiedUserDao;
import com.shuogesha.platform.entity.UnifiedUser;
import com.shuogesha.platform.web.mongo.MongoBaseDaoImpl;

@Repository
public class UnifiedUserDaoImpl extends MongoBaseDaoImpl<UnifiedUser> implements UnifiedUserDao {

	@Override
	public UnifiedUser findByUsername(String username) {
		Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        return this.findOne(query);
	}

}
