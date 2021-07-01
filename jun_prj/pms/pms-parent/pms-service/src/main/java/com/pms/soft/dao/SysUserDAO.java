package com.pms.soft.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pms.soft.bean.SysUser;

@Mapper
public interface SysUserDAO {

	public SysUser getUserById(String id);
}
