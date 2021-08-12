package com.shuogesha.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.shuogesha.platform.dao.DictionaryDao;
import com.shuogesha.platform.entity.Dictionary;
import com.shuogesha.platform.web.mongo.MongoBaseDaoImpl;

@Repository
public class DictionaryDaoImpl extends MongoBaseDaoImpl<Dictionary> implements DictionaryDao {

}
