package com.jun.plugin.utils.rss.collect.ganji;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.jun.plugin.utils.rss.collect.Main;
import com.jun.plugin.utils.rss.collect.utils.DBUtils;
import com.jun.plugin.utils.rss.collect.utils.UtilTools;

/**
 * @author Wujun
 * @Url www.cntaige.com
 * @Date 2014年11月11日
 */
public class GanjiDB {
	private DBUtils db;
	public static final String tableName = "ganji_com_zp";
	private static GanjiDB ganjiDB;

	private GanjiDB() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		try {
			db = new DBUtils(DriverManager.getConnection("jdbc:sqlite:"
					+ Main.DB_PATH));
			String sqlString = "create table if not exists "
					+ tableName
					+ "_links(link string,title string,is_collect integer DEFAULT 1,_id integer primary key autoincrement)";
			db.executeSql(sqlString);
			String sqlString2 = "create table if not exists "
					+ tableName
					+ "_info(tel string,company string,name string,company_intr string,addr string,is_export integer DEFAULT 1,category string default '无',c_time timestamp not null DEFAULT (datetime(CURRENT_TIMESTAMP,'localtime')),_id integer primary key autoincrement)";
			db.executeSql(sqlString2);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public void saveExcel(GanjiCollect ganjiZhaoPin) {
		// 导出到excel表格
		GanjiExcel ganjiExcel = new GanjiExcel();
		System.out.println("-----------------------");
		System.out.println(UtilTools.getNowTime());
		System.out.println("开始导出表格");
		try {
			ArrayList<HashMap<String, Object>> list = db.select(tableName
					+ "_info", "is_export=1");
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> hashMap = list.get(i);
				db.executeSql("update " + tableName + "_info "
						+ "set is_export=2 where _id=" + hashMap.get("_id"));
				System.out.println("导出公司：" + hashMap.get("company"));
				ganjiExcel.setCell(i + 1, (String) hashMap.get("company"),
						ganjiZhaoPin.getImage((String) hashMap.get("tel")),
						hashMap.get("name") + "", hashMap.get("category") + "",
						hashMap.get("addr") + "", hashMap.get("company_intr")
								+ "");
			}
			ganjiExcel.saveFile(list.size());

		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public void startCollectInfo(GanjiCollect ganjiZhaoPin) {
		// 循环采集公司信息
		try {
			ArrayList<HashMap<String, Object>> list = db.select(tableName
					+ "_links", "is_collect=1");
			for (HashMap<String, Object> hashMap : list) {
				ganjiZhaoPin.getCompanyInfo((String) hashMap.get("link"));
				HashMap<String, Object> hashMap2 = new HashMap();
				hashMap2.put("is_collect", 2);
				db.update(tableName + "_links", hashMap2,
						"_id=" + hashMap.get("_id"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public int insertCompanyInfo(String comName, String category,
			String telName, String telPhone, String addr, String companyIntr) {
		// 保存公司信息
		System.out.println("-------------------------");
		System.out.println(UtilTools.getNowTime());
		System.out.println("开始采集公司详细信息");
		int save = 0;
		try {
			if (db.getCount(tableName + "_info", "company='" + comName.trim()
					+ "'") == 0) {
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("company", comName.trim());
				hashMap.put("tel", telPhone.trim());
				hashMap.put("name", telName.trim());
				hashMap.put("addr", addr.trim());
				hashMap.put("category", category.trim());
				hashMap.put("company_intr", companyIntr.trim());
				save = db.insert(tableName + "_info", hashMap);
			}
			System.out.println("公司名称：" + comName);
			System.out.println("行业：" + category);
			System.out.println("电话图片地址：" + telPhone);
			System.out.println("联系人：" + telName);
			System.out.println("地址：" + addr);
			System.out.println("公司介绍：" + companyIntr);
			if (save != 0)
				System.out.println("--------采集成功！");
			else
				System.out.println("---------数据库已有此公司");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return save;
	}

	public int insertCompanyLinks(String link, String title) {
		// 保存公司链接地址
		int isSave = 0;
		try {
			if (db.getCount(tableName + "_links", "link='" + link.trim() + "'") == 0) {
				HashMap<String, Object> hashMap = new HashMap();
				hashMap.put("link", link.trim());
				hashMap.put("title", title.trim());
				isSave = db.insert(tableName + "_links", hashMap);
			}
			if (isSave != 0)
				System.out.println(title + "\n---------采集成功！");
			else
				System.out.println(title + "\n--------数据库已有此公司！");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return isSave;
	}

	public static GanjiDB getInstance() {
		if (ganjiDB == null)
			ganjiDB = new GanjiDB();
		return ganjiDB;
	}

	public void closeDB() {
		db.close();
	}
}
