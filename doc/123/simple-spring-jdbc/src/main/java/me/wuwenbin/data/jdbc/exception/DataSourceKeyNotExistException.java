package me.wuwenbin.data.jdbc.exception;

/**
 * Check dataSource key is exist
 * <p>
 * Created by wuwenbin on 2017/1/3.
 *
 * @author wuwenbin
 * @since 1.0.0
 */
public class DataSourceKeyNotExistException extends Exception {
    public DataSourceKeyNotExistException() {
        super("dataSourceKey is not exist in the initialled bean");
    }

    public DataSourceKeyNotExistException(String message) {
        super(message);
    }
}
