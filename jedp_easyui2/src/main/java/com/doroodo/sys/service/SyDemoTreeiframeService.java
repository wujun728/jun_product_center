package com.doroodo.sys.service;


import java.util.List;

import com.doroodo.base.model.*;
import com.doroodo.base.service.BaseService;
import com.doroodo.sys.model.*;

public interface SyDemoTreeiframeService extends BaseService<SyDemoTreeiframe>{
	public DataGrid dataGrid(int page,int rows, String searchName, String searchKey);
	public DataGrid dataGrid(SyDemoTreeiframe syDemoTreeiframe, int page, int rows, String searchName,
			String searchKey);
	public void saveOrUpdate(SyDemoTreeiframe syDemoTreeiframe);
	public void delete(String ids);
	public List<SyDemoTreeiframe> listAll();
	public List<Tree> tree(String id);
}
