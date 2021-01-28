package com.doroodo.sys.service;

import java.util.List;

import com.doroodo.base.model.DataGrid;
import com.doroodo.sys.model.SyParameter;
import com.doroodo.sys.model.SyParameterUsed;

public interface SyParameterService {
	public void saveOrUpdate(SyParameter syParameter);
	public SyParameter getLastSyParameter();
	public SyParameter getUsedSyParameter();
	public DataGrid dataGrid(int page,int rows, String searchName, String searchKey);
	public void setDefult(SyParameterUsed syParameterUsed);
	public void delete(String ids);
	public List<SyParameter> get(SyParameter syParameter);
}
