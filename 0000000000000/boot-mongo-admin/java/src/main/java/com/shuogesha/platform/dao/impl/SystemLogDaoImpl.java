package com.shuogesha.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.shuogesha.platform.dao.SystemLogDao;
import com.shuogesha.platform.entity.SystemLog;
import com.shuogesha.platform.web.mongo.MongoBaseDaoImpl;

@Repository
public class SystemLogDaoImpl extends MongoBaseDaoImpl<SystemLog> implements SystemLogDao {

}
