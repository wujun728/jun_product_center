package com.jun.plugin.dbimport.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.jun.plugin.dbmeta.DbMetaCrawler;
import com.jun.plugin.fieldmeta.entity.PageField;

public interface DbImportService {

	public List<String> getTableNames() throws SQLException;
	
	/**
	 * 从数据库导入表结构
	 * @param tableName
	 * @throws SQLException 
	 */
	
	public void importTable(String tableName, DbImportTableOption option) throws SQLException;
	public void importTables(List<String> tableNames, DbImportTableOption option) throws SQLException;
}
