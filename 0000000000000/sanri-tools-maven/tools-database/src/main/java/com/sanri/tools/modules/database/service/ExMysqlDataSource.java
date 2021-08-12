package com.sanri.tools.modules.database.service;


import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ExMysqlDataSource extends MysqlDataSource {
    @Override
    public Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("password",password);
        properties.setProperty("user",user);
        properties.setProperty("remarks", "true");
        properties.setProperty("useInformationSchema", "true");
        properties.setProperty("Unicode","true");
        properties.setProperty("characterEncoding","UTF-8");
        properties.setProperty("serverTimezone","Asia/Shanghai");
        properties.setProperty("useAffectedRows","true");
        properties.setProperty("useSSL","false");
        Connection connection = super.getConnection(properties);
        return connection;
    }
}
