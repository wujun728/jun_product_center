package sy.test.org.apache.commons.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.alibaba.fastjson.JSON;

public class Test1 {

	private static Connection conn;

	public static Connection getConnection() {
		String url = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=172.24.7.189)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=iovdb)))";
		String driverClassName = "oracle.jdbc.driver.OracleDriver";
		String username = "foton";
		String password = "foton";
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
			conn.setAutoCommit(true);
			List<Map> al = (List) qr.query(conn, "select t.* from IOV_VL_PLACE_POINT t where t.name is null order by t.id", new MapListHandler());
			for (Map map : al) {
				System.out.println(JSON.toJSONString(map));
				System.out.println(map.get("ID"));
				try {
					qr.update(conn, "delete IOV_VL_PLACE_POINT t where t.id = ?", map.get("ID"));
				} catch (Exception e) {
					System.out.println(map.get("ID") + "已经被引用了。");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.commitAndClose(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
