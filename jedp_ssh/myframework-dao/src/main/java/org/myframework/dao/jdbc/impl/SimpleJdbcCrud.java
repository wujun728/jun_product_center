package org.myframework.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.myframework.commons.util.StringUtils;
import org.myframework.dao.id.IdGenerator;
import org.myframework.dao.id.IdUtil;
import org.myframework.dao.jdbc.ISimpleCrud;
import org.myframework.dao.jdbc.dialect.Dialect;
import org.myframework.dao.jdbc.dialect.impl.OracleDialect;
import org.myframework.dao.metadata.ColumnInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcAccessor;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;

/**
 * 1.实现单表增删改及本地化SQL查询
 * <ol>
 * <li>{@see ISimpleCrud}
 * </ol>
 *
 * @author Wujun
 * @since 1.0
 */
public class SimpleJdbcCrud  extends JdbcAccessor implements ISimpleCrud  {
	private static Log log = LogFactory.getLog(SimpleJdbcCrud.class);

	private Dialect dialect = new OracleDialect();

	private IdUtil idUtil = new IdUtil();

	NamedParameterJdbcOperations jdbc ;

	/**
	 * 获取表字段相关信息(是否主键,字段名,字段类型,字段长度)
	 *
	 * @param strTableName
	 * @return
	 */
	public List<ColumnInfo> getTableColumnInfos(String strTableName) {
		DataSource dataSource = this.getDataSource();
		ResultSet rs = null;
		ResultSet rs2 = null;
		Connection conn = null;
		DatabaseMetaData dmd = null;
		List<ColumnInfo> columnInfos = new ArrayList<ColumnInfo>();
		try {
			conn = dataSource.getConnection();
			dmd = conn.getMetaData();
			rs = dmd.getColumns(null, "%", strTableName, "%");
			rs2 = dmd.getPrimaryKeys(null, "%", strTableName);
			final List<String> pkCols = getPkColumnInfos(rs2);
			while (rs.next()) {
				final String strFieldName = rs
						.getString(ColumnInfo.COLUMN_NAME);
				final String strFieldType = rs.getString(ColumnInfo.TYPE_NAME);
				final int nullable = rs.getInt(ColumnInfo.NULLABLE);
				final String fieldLabel = rs.getString(ColumnInfo.REMARKS);
				final int precision = rs.getInt(ColumnInfo.DECIMAL_DIGITS);
				final int colSize = rs.getInt(ColumnInfo.COLUMN_SIZE);
				final int charLen = rs.getInt(ColumnInfo.CHAR_OCTET_LENGTH);
				ColumnInfo columnInfo = new ColumnInfo() {
					@Override
					public boolean isPk() {
						return pkCols.contains(strFieldName);
					}

					@Override
					public String getColumnLabel() {
						return fieldLabel;
					}

					@Override
					public int getColumnSize() {
						return colSize;
					}

					@Override
					public String getColumnName() {
						return strFieldName;
					}

					@Override
					public String getColTypeName() {
						return strFieldType;
					}

					@Override
					public int getColPrecision() {
						return precision;
					}

					@Override
					public int getColScale() {
						return 0;
					}

					@Override
					public int getLength() {
						return charLen;
					}

					@Override
					public int isNullable() {
						return nullable;
					}

					@Override
					public String toString() {
						return  strFieldName
								+">>>"+ this.isPk() + ">>>" + getColPrecision()
								+ ">>>" + getColumnSize() + ">>>" + ">>>"
								+ strFieldType + ">>>" + nullable + ">>>"
								+ getColPrecision()  + ">>>"+fieldLabel;
					}
				};
				columnInfos.add(columnInfo);
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		} finally {
			JdbcUtils.closeResultSet(rs2);
			JdbcUtils.closeResultSet(rs);
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
		return columnInfos;
	}

	private List<String> getPkColumnInfos(ResultSet rs) throws SQLException {
		List<String> pkCols = new ArrayList<String>();
		while (rs.next()) {
			String strFieldName = rs.getString(ColumnInfo.COLUMN_NAME);
			pkCols.add(strFieldName);
		}
		return pkCols;
	}

	/**
	 * 根据表字段数据类型的定义对map中的value值进行转化
	 *
	 * @param mapKey
	 * @param columnInfo
	 * @param map
	 */
	private void convertMapValue(String mapKey, ColumnInfo columnInfo,
			Map<String, Object> map) {
		String value = StringUtils.asString(map.get(mapKey));
		String colType = columnInfo.getColTypeName();
		if (dialect.isNumber(colType) && !StringUtils.isEmpty(value)) {
			if (columnInfo.getColPrecision() > 0)
				map.put(mapKey, Double.valueOf(value));
			else
				map.put(mapKey, Long.valueOf(value));
		}
		if (dialect.isDate(colType) && !StringUtils.isEmpty(value)) {
			map.put(mapKey, DateTime.parse(value).toDate());
		}
		if (columnInfo.isPk()) {
			// 当新增操作时才传入idType参数，其他操作不能传入idType
			String idGeneratorType = StringUtils.asString(map
					.get(IdGenerator.ID_TYPE_KEY));
			String sequence = StringUtils.asString(map
					.get(IdGenerator.SEQUENCE_KEY));
			if (!StringUtils.isEmpty(idGeneratorType)) {
				idUtil.setJdbc(jdbc);
				map.put(mapKey, idUtil.generateValue(idGeneratorType, sequence));
			}
		}

	}

	/**
	 * 将bean的名字转化对应表名 ，然后查询表字段 再结合传入的参数值，生成SQL语句
	 *
	 * @param beanName
	 * @param prefix
	 * @param map
	 * @return
	 */
	private String getDmlSql(String beanName, String prefix,
			Map<String, Object> map, String sqlType) {
		String tableName = StringUtils.toDBPatternStr(beanName, prefix);
		List<ColumnInfo> columnInfos = this.getTableColumnInfos(tableName);
		Assert.notEmpty(columnInfos, tableName + "Column Info not exists  !! ");
		List<String> colsList = new ArrayList<String>();
		List<String> allCols = new ArrayList<String>();
		List<String> insertParamList = new ArrayList<String>();
		List<String> updateParamList = new ArrayList<String>();
		List<String> wherePkParamList = new ArrayList<String>();
		for (ColumnInfo columnInfo : columnInfos) {
			String colName = columnInfo.getColumnName();
			String mapKey = StringUtils.toBeanPatternStr(colName);
			allCols.add(colName);
			convertMapValue(mapKey, columnInfo, map);
			if (map.containsKey(mapKey)) {
				colsList.add(colName);
				insertParamList.add(":" + mapKey);
				updateParamList.add(colName + "= :" + mapKey);
				if (columnInfo.isPk()) {
					if (map.containsKey(mapKey)) {
						wherePkParamList.add(colName + "= :" + mapKey);
					}
				}
			}
		}
		String createSql = "INSERT INTO " + tableName + "(  "
				+ StringUtils.join(colsList) + " ) VALUES ("
				+ StringUtils.join(insertParamList) + " )";
		String updateSql = "UPDATE " + tableName + " SET "
				+ StringUtils.join(updateParamList) + "   WHERE   "
				+ StringUtils.join(wherePkParamList, " AND ");
		String deleteSql = "DELETE FROM  " + tableName + "    WHERE   "
				+ StringUtils.join(wherePkParamList, " AND ");
		String readSql = "SELECT " + StringUtils.join(allCols) + "  FROM  "
				+ tableName + "    WHERE   "
				+ StringUtils.join(wherePkParamList, " AND ");
		if ("C".equals(sqlType)) {
			return createSql;
		} else if ("U".equals(sqlType)) {
			return updateSql;
		} else if ("D".equals(sqlType)) {
			return deleteSql;
		} else {
			return readSql;
		}
	}

	private String getCreateSql(String beanName, String prefix,
			Map<String, Object> map) {
		return getDmlSql(beanName, prefix, map, "C");
	}

	private String getUpdateSql(String beanName, String prefix,
			Map<String, Object> map) {
		return getDmlSql(beanName, prefix, map, "U");
	}

	private String getDeleteSql(String beanName, String prefix,
			Map<String, Object> map) {
		return getDmlSql(beanName, prefix, map, "D");
	}

	private String getReadSql(String beanName, String prefix,
			Map<String, Object> map) {
		return getDmlSql(beanName, prefix, map, "R");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hollyframework.jdbc.SimpleCrud#save(java.lang.String,
	 * java.lang.String, java.util.Map)
	 */
	@Override
	public void save(String beanName, String prefix, Map<String, Object> param) {
		log.debug("-------------sql before convert--------------------");
		log.debug("\n\n SAVE " + beanName + ":" + param + "\n");
		log.debug("-------------sql before convert--------------------");
		String sql = getCreateSql(beanName, prefix, param);
		jdbc.update(sql, param);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hollyframework.jdbc.SimpleCrud#save(java.lang.String,
	 * java.util.Map)
	 */
	@Override
	public void save(String beanName, Map<String, Object> param) {
		save(beanName, "", param);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hollyframework.jdbc.SimpleCrud#updateTable(java.lang.String,
	 * java.lang.String, java.util.Map)
	 */
	@Override
	public void update(String beanName, String prefix, Map<String, Object> param) {
		log.debug("-------------sql before convert--------------------");
		log.debug("\n\n update  " + beanName + ":" + param + "\n");
		log.debug("-------------sql before convert--------------------");
		String sql = getUpdateSql(beanName, prefix, param);
		jdbc.update(sql, param);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hollyframework.jdbc.SimpleCrud#updateTable(java.lang.String,
	 * java.util.Map)
	 */
	@Override
	public void update(String beanName, Map<String, Object> param) {
		update(beanName, "", param);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hollyframework.jdbc.SimpleCrud#delete(java.lang.String,
	 * java.lang.String, java.util.Map)
	 */
	@Override
	public void delete(String beanName, String prefix, Map<String, Object> param) {
		log.debug("-------------sql before convert--------------------");
		log.debug("\n\n delete  " + beanName + ":" + param + "\n");
		log.debug("-------------sql before convert--------------------");
		String sql = getDeleteSql(beanName, prefix, param);
		jdbc.update(sql, param);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hollyframework.jdbc.SimpleCrud#delete(java.lang.String,
	 * java.util.Map)
	 */
	@Override
	public void delete(String beanName, Map<String, Object> param) {
		delete(beanName, "", param);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hollyframework.jdbc.SimpleCrud#read(java.lang.String,
	 * java.lang.String, java.util.Map)
	 */
	@Override
	public Map<String, Object> read(String beanName, String prefix,
			Map<String, Object> param) {
		log.debug("-------------sql before convert--------------------");
		log.debug("\n\n get  " + beanName + ":" + param + "\n");
		log.debug("-------------sql before convert--------------------");
		String sql = getReadSql(beanName, prefix, param);
		return jdbc.queryForMap(sql, param);
	}

	@Override
	public Map<String, Object> read(String beanName, Map<String, Object> param) {
		return read(beanName, "", param);
	}

	public SimpleJdbcCrud( ) {
		super();
	}

	public SimpleJdbcCrud(DataSource dataSource ) {
		super.setDataSource( dataSource);
	}

	public SimpleJdbcCrud( NamedParameterJdbcOperations namedParameterJdbcTemplate  ) {
		super();
		jdbc = namedParameterJdbcTemplate ;
	}

	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		jdbc = new NamedParameterJdbcTemplate(new JdbcTemplate(this.getDataSource()));
	}

}
