package com.xiongyw.mapper;

import java.util.List;

import com.xiongyw.entity.RoleFormMap;
import com.xiongyw.mapper.base.BaseMapper;

public interface RoleMapper extends BaseMapper{
	public List<RoleFormMap> seletUserRole(RoleFormMap map);
	
	public void deleteById(RoleFormMap map);
}
