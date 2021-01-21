package com.doroodo.sys.service;

import java.util.List;

import com.doroodo.base.model.DataGrid;
import com.doroodo.sys.model.SyRole;

public interface SyRoleService {

	void saveOrUpdate(SyRole syRole);

	void delete(String ids);
	public List<SyRole> get(SyRole syRole);

	DataGrid dataGrid( int page, int rows, String searchName,
			String searchKey);
	SyRole getBySyRoleName(String rolename);
}
