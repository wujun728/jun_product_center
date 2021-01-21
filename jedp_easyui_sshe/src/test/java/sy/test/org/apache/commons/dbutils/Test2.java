package sy.test.org.apache.commons.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import sy.model.base.Syrole;
import sy.model.base.Syuser;

import com.alibaba.fastjson.JSON;

public class Test2 {

	private static Connection conn;

	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/sshe?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
		String driverClassName = "com.mysql.jdbc.Driver";
		String username = "sshe";
		String password = "sshe";
		Connection conn = null;
		DbUtils.loadDriver(driverClassName);
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		conn = getConnection();
		QueryRunner qr = new QueryRunner();
		try {
			conn.setAutoCommit(false);
			List<Syuser> al = (List) qr.query(conn, "SELECT syuser.* FROM syuser", new BeanListHandler<>(Syuser.class));
			for (Syuser u : al) {
				List<Syrole> rl = qr.query(conn, "SELECT syrole.* FROM syrole JOIN syuser_syrole ON syuser_syrole.SYROLE_ID = syrole.ID WHERE syuser_syrole.SYUSER_ID = ?", new BeanListHandler<>(Syrole.class), u.getId());
				u.getSyroles().addAll(rl);
				System.out.println(u.getLoginname());
				System.out.println(JSON.toJSONString(u.getSyroles()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DbUtils.rollback(conn);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				DbUtils.commitAndClose(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
