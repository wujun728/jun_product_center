package com.bjc.lcp.api.service;

import cn.hutool.core.map.MapUtil;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSON;
//import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
//import com.jfinal.plugin.activerecord.Db;
//import com.jfinal.plugin.activerecord.Record;
//import com.jfinal.plugin.druid.DruidPlugin;
import com.jun.plugin.common.Result;
import com.jun.plugin.common.base.interfaces.IExecutor;
import com.jun.plugin.db.record.Db;
import com.jun.plugin.db.record.Record;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 组件ID：BAS000000000100 描 述：执行指定的SQL语句（insert/update/delete/select语句）
 * 版本历史：1.0.0版 参数说明： 参 数1：要执行的SQL语句 参 数2：sql参数 参 数3：执行SQL语句处理记录数存放标签
 * 
 * 说明：需要把该代码放进DB，api_groovy，测试Jfinal-CURD-保存在庫裡面
 */
public class GenTableCRUDSQLComponent implements IExecutor<Result, Map<String,Object>> {

	public static void initDb(String configName, String url, String username, String password) {
		Db.init(url,username,password);
//		DruidPlugin dp = new DruidPlugin(url, username, password);
//		ActiveRecordPlugin arp = new ActiveRecordPlugin(configName, dp);
//		// 与 jfinal web 环境唯一的不同是要手动调用一次相关插件的start()方法
//		dp.start();
//		arp.start();
	}

	@Override
	public Result execute(Map<String, Object> params) throws Exception {
		// 校验组件参数+校验上下文参数，TODO
//		if (!checkMinParaCountAndSetRespCode(3)) {
//			return ERROR;
//		}
		// 初始化数据库
		String configName = "A000001";
		initDb(configName, SpringUtil.getProperty("spring.datasource.url"),
				SpringUtil.getProperty("spring.datasource.username"),
				SpringUtil.getProperty("spring.datasource.password"));

		DataSource ds = Db.use(configName).getConfig().getDataSource();

		String tableName = MapUtil.getStr(params, "tableName");
		Table tableMeta = MetaUtil.getTableMeta(ds, tableName);

		// 执行SQL语句 eq: delete from test where id = ?
		Object[] paras = MapUtil.getStr(params, "sParas").split(","); // sql语句参数清单 eq: id (多个逗号分隔)
		String sResultPath = MapUtil.getStr(params, "sResult");// 执行SQL语句处理记录数存放标签 eq： 1 (更新的条数)

		try {
			Object[] paramVal = paras;// { "abc", "123" };

			// 测试代码--begin
			// *************************************************************************
			Object[] sqlparams = { 22 };
			List<Record> datas = Db.use(configName)
					.find("select id,title,content,remark,field_name_test from test where id > ?", sqlparams);
			System.out.println(JSON.toJSONString(datas));

			Object[] sqlUparams = { 25 };
			Object[] paras1 = { "id" };
			int ucount = Db.use(configName).update("update test set content = content + '-666' where id = ?",
					sqlUparams);
			System.out.println(JSON.toJSONString(ucount));

			Object[] sqlDparams = { 26 };
			int dcount = Db.use(configName).update("delete from test  where id = ?", sqlDparams);
			System.out.println(JSON.toJSONString(dcount));

//			Object[] sqliParams = Arrays.asList(10,"aaa","bbb","cccc","dddd").toArray() ;
			Object[] sqliParams = new Object[5];
			sqliParams[0] = 10;
			sqliParams[1] = "aaa";
			sqliParams[2] = "bbb";
			sqliParams[3] = "cccc";
			sqliParams[4] = "dddd";
			int icount = Db.use(configName).update(
					"insert into test (id, title,content,remark,field_name_test ) VALUES ( ?,?, ?, ?, ? ) ",
					sqliParams);
			System.out.println(JSON.toJSONString(icount));
			// 测试代码--end
			// *************************************************************************

		} catch (Exception e) {
			int errorCode = ((SQLException) e).getErrorCode();
			// if (errorCode == -803) {
			if (e.getMessage().matches(".*Duplicate.*PRIMARY.*") || errorCode == -803 || errorCode == 1062) {
				e.printStackTrace();
				throw new Exception("数据库主键冲突！");
			} else {
				e.printStackTrace();
				throw new Exception("数据库操作出错！");
			}
		}
		return Result.success();
	}


}
