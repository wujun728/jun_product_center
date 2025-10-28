package com.jun.plugin.demo.jpa.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jun.plugin.common.dataaccess.BaseServiceImpl;
import com.jun.plugin.demo.jpa.pojo.User;
import com.jun.plugin.demo.jpa.respository.UserDAO;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService{
	@Autowired
	UserDAO userDAO;
}
