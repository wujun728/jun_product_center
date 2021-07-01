package com.pms.soft.service.sys.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.soft.bean.SysUser;
import com.pms.soft.dao.SysUserDAO;
import com.pms.soft.service.sys.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService{

	@Autowired
	private SysUserDAO userDAO;
	
	@Override
	public SysUser getUserById(String id) {
		return userDAO.getUserById(id);
	}

}
