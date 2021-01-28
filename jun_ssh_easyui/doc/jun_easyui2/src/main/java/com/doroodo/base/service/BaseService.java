package com.doroodo.base.service;


import java.util.List;
import java.util.Map;

import com.doroodo.base.model.DataGrid;

public interface BaseService<T> {
	public DataGrid dataGrid(int page,int rows, String searchName, String searchKey);
	public DataGrid dataGrid(int page,int rows,T o);
	public Map getHqlByObject(T o);
	public void saveOrUpdate(T o);
	public void delete(String ids);
	public List<T> listAll();
	public List<T> get(T o);
	public T getLast(T o);
}
