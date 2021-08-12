package com.shuogesha.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.shuogesha.platform.dao.SiteDao;
import com.shuogesha.platform.entity.Site;
import com.shuogesha.platform.web.mongo.MongoBaseDaoImpl;

@Repository
public class SiteDaoImpl extends MongoBaseDaoImpl<Site> implements SiteDao {

}
