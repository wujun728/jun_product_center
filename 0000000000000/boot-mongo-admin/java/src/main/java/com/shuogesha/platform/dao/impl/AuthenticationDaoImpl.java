package com.shuogesha.platform.dao.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.shuogesha.platform.dao.AuthenticationDao;
import com.shuogesha.platform.entity.Authentication;
import com.shuogesha.platform.web.mongo.MongoBaseDaoImpl;

@Repository
public class AuthenticationDaoImpl extends MongoBaseDaoImpl<Authentication> implements AuthenticationDao {

	@Override
	public void removeByUid(String uid) {
		Query query = new Query();
        query.addCriteria(Criteria.where("uid").is(uid));
        this.remove(query);
	}

}
