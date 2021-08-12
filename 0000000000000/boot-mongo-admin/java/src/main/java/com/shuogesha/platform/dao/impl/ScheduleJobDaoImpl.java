package com.shuogesha.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.shuogesha.platform.dao.ScheduleJobDao;
import com.shuogesha.platform.entity.ScheduleJob;
import com.shuogesha.platform.web.mongo.MongoBaseDaoImpl;

@Repository
public class ScheduleJobDaoImpl extends MongoBaseDaoImpl<ScheduleJob> implements ScheduleJobDao {

}
