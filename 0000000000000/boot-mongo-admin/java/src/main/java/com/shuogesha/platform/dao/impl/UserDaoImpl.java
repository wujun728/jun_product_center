package com.shuogesha.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.shuogesha.platform.dao.UserDao;
import com.shuogesha.platform.entity.User;
import com.shuogesha.platform.web.mongo.MongoBaseDaoImpl;

@Repository
public class UserDaoImpl extends MongoBaseDaoImpl<User> implements UserDao {

}
