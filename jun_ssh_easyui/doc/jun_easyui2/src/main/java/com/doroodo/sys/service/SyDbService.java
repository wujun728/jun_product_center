package com.doroodo.sys.service;

import java.util.List;

import com.doroodo.code.provider.db.table.model.Table;

public interface SyDbService {
	public boolean isConnect();
	public List<Table> list();
	public Table getTableByTableName(String tableName);
	public boolean setTableColumnRemarks(String tableName,String fieldName,String Remarks);
}
