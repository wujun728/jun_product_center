package org.coderfun.dbmeta;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface DbMetaCrawler {

	public String getDbProduct() throws SQLException;
	DatabaseMetaData getRawDatabaseMetaData() throws SQLException;

	Set<String> getTableNames()throws SQLException;
	
	List<ColumnMeta> getColumns(String tableName) throws SQLException;
}
