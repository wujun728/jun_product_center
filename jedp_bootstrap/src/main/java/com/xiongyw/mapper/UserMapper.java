package com.xiongyw.mapper;

import java.util.List;

import com.xiongyw.entity.UserFormMap;
import com.xiongyw.mapper.base.BaseMapper;


public interface UserMapper extends BaseMapper{

	public List<UserFormMap> findUserPage(UserFormMap userFormMap);
	
}
