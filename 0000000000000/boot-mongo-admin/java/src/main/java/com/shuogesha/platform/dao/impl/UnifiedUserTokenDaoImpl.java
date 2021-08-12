package com.shuogesha.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.shuogesha.platform.dao.UnifiedUserTokenDao;
import com.shuogesha.platform.entity.UnifiedUserToken;
import com.shuogesha.platform.web.mongo.MongoBaseDaoImpl;

@Repository
public class UnifiedUserTokenDaoImpl extends MongoBaseDaoImpl<UnifiedUserToken> implements UnifiedUserTokenDao {

}
