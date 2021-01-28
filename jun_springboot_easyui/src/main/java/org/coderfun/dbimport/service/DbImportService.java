package org.coderfun.dbimport.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.coderfun.dbmeta.DbMetaCrawler;
import org.coderfun.fieldmeta.entity.PageField;

public interface DbImportService {

	public Set<String> getTableNames() throws SQLException;
	
	/**
	 * 从数据库导入表结构
	 * @param tableName
	 * @throws SQLException 
	 */
	
	public void importTable(String tableName, DbImportTableOption option) throws SQLException;
	public void importTables(List<String> tableNames, DbImportTableOption option) throws SQLException;
}
