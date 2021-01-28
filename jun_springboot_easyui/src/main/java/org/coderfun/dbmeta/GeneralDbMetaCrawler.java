package org.coderfun.dbmeta;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

public class GeneralDbMetaCrawler implements DbMetaCrawler {
	protected DataSource dataSource;

	public GeneralDbMetaCrawler(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Set<String> getTableNames() throws SQLException {
		Set<String> tables = new HashSet<String>();
		ResultSet rs;
		rs = getRawDatabaseMetaData().getTables(null, null, null, new String[] { "TABLE" });

		while (rs.next()) {
			String tableName = rs.getString("TABLE_NAME");
			tables.add(tableName);
		}
		rs.close();
		return tables;
	}

	@Override
	public List<ColumnMeta> getColumns(String tableName) throws SQLException {
		// TODO Auto-generated method stub
		List<ColumnMeta> columns = new ArrayList<>();
		ResultSet rs = null;

		rs = getRawDatabaseMetaData().getColumns(null, null, tableName, null);

		while (rs.next()) {
			if (!rs.getString("TABLE_NAME").equals(tableName))
				continue;
			ColumnMeta column = packColumn(rs);
			columns.add(column);
		}
		rs.close();
		return columns;
	}

	protected ColumnMeta packColumn(ResultSet rs) throws SQLException {
		String name = rs.getString("COLUMN_NAME");
		ColumnMeta column = new ColumnMeta();
		column.setName(name);
		column.setComment(rs.getString(12));// Oracle and Mssql can not get
											// comment information
		column.setDefaultValue(rs.getString("COLUMN_DEF"));
		column.setLength(rs.getInt("CHAR_OCTET_LENGTH"));
		column.setNullable(rs.getInt("NULLABLE") == 1 ? true : false);
		column.setPrecision(rs.getInt("COLUMN_SIZE"));
		column.setScale(rs.getInt("DECIMAL_DIGITS"));
		column.setType(rs.getInt("DATA_TYPE"));
		column.setTypeName(rs.getString("TYPE_NAME"));
		// column.setUnique(unique);
		return column;
	}

	@Override
	public DatabaseMetaData getRawDatabaseMetaData() throws SQLException {
		// TODO Auto-generated method stub
		return dataSource.getConnection().getMetaData();
	}
	@Override
	public String getDbProduct() throws SQLException{
		DatabaseMetaData dbm=getRawDatabaseMetaData();
		return dbm.getDatabaseProductName();	
	}
}
