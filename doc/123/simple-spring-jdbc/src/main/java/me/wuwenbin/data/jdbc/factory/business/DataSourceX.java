package me.wuwenbin.data.jdbc.factory.business;

import javax.sql.DataSource;

/**
 * the object of injection while application is starting
 * <p>
 * Created by wuwenbin on 2017/3/27.
 */
public class DataSourceX {
    private DataSource dataSource;
    private DbType initDbType;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DbType getInitDbType() {
        return initDbType;
    }

    public void setInitDbType(DbType initDbType) {
        this.initDbType = initDbType;
    }
}
