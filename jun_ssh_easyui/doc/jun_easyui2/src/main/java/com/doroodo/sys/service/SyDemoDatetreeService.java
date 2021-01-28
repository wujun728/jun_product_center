package com.doroodo.sys.service;

import com.doroodo.base.model.DataGrid;


import java.util.List;

import com.doroodo.sys.model.*;
public interface SyDemoDatetreeService {
	public DataGrid dataGrid(int page,int rows, String searchName, String searchKey);
	public DataGrid treeGrid(int page,int rows, String searchName, String searchKey,String year);
	public void saveOrUpdate(SyDemoDatetree syDemoDatetree);
	public void delete(String ids);
	public List<SyDemoDatetree> listAll();
	public List<SyDemoDatetree> get(SyDemoDatetree syDemoDatetree);
	public List<SyDemoDatetree> listByYear(String year);
	public List<SyDemoDatetree> listByDate(String year);
}
