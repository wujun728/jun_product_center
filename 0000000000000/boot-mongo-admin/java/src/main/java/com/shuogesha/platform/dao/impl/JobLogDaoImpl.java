package com.shuogesha.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.shuogesha.platform.dao.JobLogDao;
import com.shuogesha.platform.entity.JobLog;
import com.shuogesha.platform.web.mongo.MongoBaseDaoImpl;

@Repository
public class JobLogDaoImpl extends MongoBaseDaoImpl<JobLog> implements JobLogDao {

}
