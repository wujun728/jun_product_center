package org.myframework.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.myframework.codeutil.Column;

public class JdbcUtils {
	private final static Log logger = LogFactory.getLog(JdbcUtils.class);

	public static Connection getConnection(Properties properties) throws Exception {
	    Properties localProperties = new Properties();
	    localProperties.put("remarksReporting","true");//注意这里
        localProperties.put("user", properties.getProperty("jdbc.username"));
        localProperties.put("password", properties.getProperty("jdbc.password"));
        String driver = properties.getProperty("jdbc.driver");
//        System.out.println(driver);
//        Class.forName().newInstance();
	    Connection conn= DriverManager.getConnection(properties.getProperty("jdbc.url"),localProperties);
	    return conn;
	}

	public static List<Column> getLsColumns(Connection conn ,String tableName) throws Exception {
		List<Column> lsColumns = new ArrayList<Column>(10);
		PreparedStatement stmt = conn.prepareStatement("select *  from "+tableName+" where 1=0 ");
		ResultSet resultSet = stmt.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int n = rsmd.getColumnCount();
		logger.debug("============读取表相关的字段信息 START ================" );
		for (int i = 1; i <= n; i++)
		{
			String colName = rsmd.getColumnName(i);
			String fieldName = StringUtil.toBeanPatternStr(colName);
			String dataType = rsmd.getColumnClassName(i);
			if("java.math.BigDecimal".equals(dataType)&&rsmd.getScale(i)==0)
				dataType= "Long";
			if("oracle.sql.CLOB".equals(dataType) )
				dataType= "String";
			Column column = new Column();
			column.setName(colName) ;
			column.setJavaName(fieldName) ;
			column.setDataType(dataType.endsWith("Timestamp")?"java.util.Date":dataType);
			column.setPrecision(String.valueOf(rsmd.getPrecision(i)));
			column.setScale( String.valueOf(rsmd.getScale(i)) );
			column.setLength( String.valueOf(rsmd.getColumnDisplaySize(i)));
			column.setNullable(String.valueOf(1==rsmd.isNullable(i)));

//			获取列注释
			DatabaseMetaData dbmd = conn.getMetaData();
			ResultSet rs = dbmd.getColumns(null, null, tableName, null);
			while (rs.next()) {
				if (colName.equals(rs.getString("COLUMN_NAME"))){
					String  comments = rs.getString("REMARKS");
					column.setComments(StringUtil.asString(comments));
				}
			}
		/*	rs =  dbmd.getColumns(null, null, tableName, "%");
			while(rs.next()){
				System.out.println(rs.getString("COLUMN_NAME"));
			}*/
		//				获取主键列
			ResultSet rs2 = dbmd.getPrimaryKeys(null, null, tableName);
			while (rs2.next()) {
				if (colName.equals(rs2.getString("COLUMN_NAME")))
					column.setColumnKey("TRUE");
			}
			logger.debug("TABLE COLUMN INFO>>>>>"+column+"---------------------" );
			lsColumns.add(column);
		}
		logger.debug("============读取表相关的字段信息 END ================" );
		return lsColumns;
	}
}
