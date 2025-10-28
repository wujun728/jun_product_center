package sy.util.base;

import java.sql.Connection;

import javax.sql.DataSource;

import org.nutz.dao.ConnCallback;
import org.nutz.dao.impl.DaoRunner;
import org.springframework.jdbc.datasource.DataSourceUtils;

/**
 * spring整合nutz的dao，让spring控制dao的事物
 * 
 * @author 孙宇
 *
 */
public class SpringDaoRunner implements DaoRunner {
	@Override
	public void run(DataSource dataSource, ConnCallback callback) {
		Connection con = DataSourceUtils.getConnection(dataSource);
		try {
			callback.invoke(con);
		} catch (Exception e) {
			if (e instanceof RuntimeException) {
				throw (RuntimeException) e;
			} else {
				throw new RuntimeException(e);
			}
		} finally {
			DataSourceUtils.releaseConnection(con, dataSource);
		}
	}
}
