package com.doroodo.sys.service;


import java.util.List;

import com.doroodo.base.model.DataGrid;
import com.doroodo.base.model.Tree;
import com.doroodo.sys.model.SyOrgan;

public interface SyOrganService {

	void saveOrUpdate(SyOrgan syOrgan);

	void delete(String ids);

	List<Tree> tree(String id);
	 List<Tree> gTree_(String id);

	DataGrid dataGrid(SyOrgan syOrgan, int page, int rows, String searchName,
			String searchKey);
	public List<SyOrgan> get(SyOrgan syOrgan);
	
	List<SyOrgan> getLeave(int iLeave);
	public List<SyOrgan> getLeave(int iLeave ,String upOrganId);

}
