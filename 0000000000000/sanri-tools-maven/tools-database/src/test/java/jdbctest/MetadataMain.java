package jdbctest;

import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

public class MetadataMain {
//    private static final String driver = "com.mysql.jdbc.Driver";
//    private static final String url = "jdbc:mysql://localhost:3306/test";
//    private static final String username = "root";
//    private static final String password = "h123";

    private static final String driver = "org.postgresql.Driver";
    private static final String url = "jdbc:postgresql://10.101.72.43:5432/hdsc_db";
    private static final String username = "postgres";
    private static final String password = "postgres";

    @Before
    public void init() throws ClassNotFoundException {
        Class.forName(driver);
    }



    /**
     * mysql 使用的 catlog
     * postgresql 使用的 schema
     * @throws SQLException
     */
    @Test
    public void testMetadata() throws SQLException {
        Properties props =new Properties();
        props.put("remarksReporting","true");
        // mysql 设置下面两项显示注释
        props.setProperty("remarks", "true");
        props.setProperty("useInformationSchema", "true");
        props.put("user", username);
        props.put("password", password);
        Connection connection = DriverManager.getConnection(url, props);
        DatabaseMetaData metaData = connection.getMetaData();
        String[] types = { "TABLE" };
        ResultSet mct = metaData.getTables(null, null, "%", types);
        ResultSetMetaData resultSetMetaData = mct.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        while (mct.next()){
            for (int i = 1; i <= columnCount; i++) {
                Object object = mct.getObject(i);
                System.out.print(object+"\t");
            }
            System.out.println();
        }

        mct.close();
        connection.close();
    }

    @Test
    public void testMetadataColumn() throws SQLException {
        Properties props =new Properties();
        props.put("remarksReporting","true");
        props.setProperty("remarks", "true");
        props.setProperty("useInformationSchema", "true");
        props.put("user", username);
        props.put("password", password);
        Connection connection = DriverManager.getConnection(url, props);
        DatabaseMetaData metaData = connection.getMetaData();
        String[] types = { "TABLE" };
        ResultSet mct = metaData.getColumns(null, null, "%", "%");
        ResultSetMetaData resultSetMetaData = mct.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        while (mct.next()){
            for (int i = 1; i <= columnCount; i++) {
                Object object = mct.getObject(i);
                System.out.print(object+"\t");
            }
            System.out.println();
        }

        mct.close();
        connection.close();
    }

    @Test
    public void primaryKeys() throws SQLException {
        Properties props =new Properties();
        props.put("remarksReporting","true");
        props.setProperty("remarks", "true");
        props.setProperty("useInformationSchema", "true");
        props.put("user", username);
        props.put("password", password);
        Connection connection = DriverManager.getConnection(url, props);
        DatabaseMetaData metaData = connection.getMetaData();
        String[] types = { "TABLE" };
        ResultSet mct = metaData.getPrimaryKeys(null, null, "%");
        ResultSetMetaData resultSetMetaData = mct.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        while (mct.next()){
            for (int i = 1; i <= columnCount; i++) {
                Object object = mct.getObject(i);
                System.out.print(object+"\t");
            }
            System.out.println();
        }

        mct.close();
        connection.close();
    }

    @Test
    public void index() throws SQLException {
        Properties props =new Properties();
        props.put("remarksReporting","true");
        props.setProperty("remarks", "true");
        props.setProperty("useInformationSchema", "true");
        props.put("user", username);
        props.put("password", password);
        Connection connection = DriverManager.getConnection(url, props);
        DatabaseMetaData metaData = connection.getMetaData();
        String[] types = { "TABLE" };
        ResultSet mct = metaData.getIndexInfo("hdsc_db", null, "id_device_play_record",false,true);
        ResultSetMetaData resultSetMetaData = mct.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        while (mct.next()){
            for (int i = 1; i <= columnCount; i++) {
                Object object = mct.getObject(i);
                System.out.print(object+"\t");
            }
            System.out.println();
        }

        mct.close();
        connection.close();
    }


    @Test
    public void catalogs() throws SQLException {
        Properties props =new Properties();
        props.put("remarksReporting","true");
        props.setProperty("remarks", "true");
        props.setProperty("useInformationSchema", "true");
        props.put("user", username);
        props.put("password", password);
        Connection connection = DriverManager.getConnection(url, props);
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet mct = metaData.getCatalogs();
        ResultSetMetaData resultSetMetaData = mct.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        while (mct.next()){
            for (int i = 1; i <= columnCount; i++) {
                Object object = mct.getObject(i);
                System.out.print(object+"\t");
            }
            System.out.println();
        }

        mct.close();
        connection.close();
    }

    @Test
    public void schemas() throws SQLException {
        Properties props =new Properties();
        props.put("remarksReporting","true");
        props.setProperty("remarks", "true");
        props.setProperty("useInformationSchema", "true");
        props.put("user", username);
        props.put("password", password);
        Connection connection = DriverManager.getConnection(url, props);
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet mct = metaData.getSchemas(null, null);
        ResultSetMetaData resultSetMetaData = mct.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        while (mct.next()){
            for (int i = 1; i <= columnCount; i++) {
                Object object = mct.getObject(i);
                System.out.print(object+"\t");
            }
            System.out.println();
        }

        mct.close();
        connection.close();
    }

}
