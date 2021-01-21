package com.doroodo.sys.service;

import java.util.List;

import com.doroodo.base.model.DataGrid;
import com.doroodo.sys.model.SyOrgan;
import com.doroodo.sys.model.SyRole;
import com.doroodo.sys.model.SyUser;

public interface SyUserService {

	void saveOrUpdate(SyUser syUser);

	void delete(String ids);

	DataGrid dataGrid(SyOrgan syOrgan, int page, int rows, String searchName, String searchKey);
	void updateRole(String userIds,String rolename);
	
	SyUser login(SyUser syUser);
	
	SyRole getRoleByUserId(String userid); 
	SyUser getByUserId(String userid);
	public List<SyUser> get(SyUser syUser);
}
