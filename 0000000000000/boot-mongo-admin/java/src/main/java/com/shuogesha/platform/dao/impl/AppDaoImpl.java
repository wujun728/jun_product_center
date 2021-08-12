package com.shuogesha.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.shuogesha.platform.dao.AppDao;
import com.shuogesha.platform.entity.App;
import com.shuogesha.platform.web.mongo.MongoBaseDaoImpl;

@Repository
public class AppDaoImpl extends MongoBaseDaoImpl<App> implements AppDao {

}
