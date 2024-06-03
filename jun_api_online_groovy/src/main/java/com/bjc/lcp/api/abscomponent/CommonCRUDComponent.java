package com.bjc.lcp.api.abscomponent;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson2.JSON;
//import com.jfinal.plugin.activerecord.Db;
//import com.jfinal.plugin.activerecord.Record;
//import com.jfinal.plugin.activerecord.generator.MetaBuilder;
//import com.jfinal.plugin.activerecord.generator.TableMeta;

import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.jun.plugin.common.base.interfaces.AbstractExecutor;
import com.jun.plugin.common.base.interfaces.Context;
import com.jun.plugin.db.record.Db;
import com.jun.plugin.db.record.Record;

/**
 * 组件ID：BAS000000000100
 * 描 述：执行指定的SQL语句（insert/update/delete/select语句） 
 * 版本历史：1.0.0版 参数说明： 
 * 参 数1：要执行的SQL语句 
 * 参 数2：sql参数 
 * 参 数3：执行SQL语句处理记录数存放标签
 * 
 * 说明：需要把该代码放进DB，api_groovy，测试Jfinal-CURD-保存在庫裡面
 */
public class CommonCRUDComponent extends AbstractExecutor<String,Map<String,Object>> {

	@Override
	public String execute(Map<String, Object> params) throws Exception {
		//校验组件参数+校验上下文参数，TODO
//		if (!checkMinParaCountAndSetRespCode(3)) {
//			return ERROR;
//		}
		super.parameters = params;
		
		Context context = super.getContext();
		
		String sSqlStr = getPara("sSqlStr"); // 执行SQL语句 eq: delete from test where id = ?
		Object[] paras = getParas("sParas"); // sql语句参数清单 eq: id (多个逗号分隔)
		String sResultPath = getPara("sResult");// 执行SQL语句处理记录数存放标签 eq： 1 (更新的条数)
		
		String appNo = "A000101";
		super.initDb(appNo, SpringUtil.getProperty("spring.datasource.url"),
				SpringUtil.getProperty("spring.datasource.username"),
				SpringUtil.getProperty("spring.datasource.password"));

		try {
			Object[] paramVal = paras ;//{ "abc", "123" };
			for(int i = 0 ; i < paras.length ; i ++) {
				paramVal[i] = context.getGlobal(String.valueOf(paras[i]));
			}
			if(sSqlStr.trim().toLowerCase().startsWith("select")) {
				List<Record> datas = Db.use(appNo).find(sSqlStr, paramVal);
				if (sResultPath.length() > 0) {
					context.setGlobal("sResult", datas);
				}
			}else {
				int result = Db.use(appNo).update(sSqlStr, paramVal);
				if (sResultPath.length() > 0) {
					context.setGlobal("sResult", result);
				}
			}
			// 测试代码--begin *************************************************************************
			Object[] sqlparams = { 22 };
			List<Record> datas = Db.use(appNo).find("select id,title,content,remark,field_name_test from test where id > ?", sqlparams);
			Record data = Db.use(appNo).findByIds("test", "id", sqlparams);
			
//			MetaUtil.getTableMeta(Db.use(appNo).getConfig().getDataSource(), "test").getPkNames()
//			MetaBuilder mb = new MetaBuilder(Db.use(appNo).getConfig().getDataSource());
//			List<TableMeta> tmetas= mb.build();
			Record data111 = Db.use(appNo).findByIds("test", "id", sqlparams);
			//Record data = Db.use(appNo).getSql(appNo)
			System.out.println(JSON.toJSONString(datas));
			
			Object[] sqlUparams = { 25 };
			Object[] paras1 = { "id" };
			int ucount = Db.use(appNo).update("update test set content = content + '-666' where id = ?", sqlUparams);
			System.out.println(JSON.toJSONString(ucount));
			
			Object[] sqlDparams = { 26 };
			int dcount = Db.use(appNo).update("delete from test  where id = ?", sqlDparams);
			System.out.println(JSON.toJSONString(dcount));
			
//			Object[] sqliParams = Arrays.asList(10,"aaa","bbb","cccc","dddd").toArray() ;
			Object[] sqliParams = new Object[5] ;
			sqliParams[0] = 10;
			sqliParams[1] = "aaa";
			sqliParams[2] = "bbb";
			sqliParams[3] = "cccc";
			sqliParams[4] = "dddd";
			int icount = Db.use(appNo).update("insert into test (id, title,content,remark,field_name_test ) VALUES ( ?,?, ?, ?, ? ) ", sqliParams);
			System.out.println(JSON.toJSONString(icount));
			// 测试代码--end *************************************************************************

		} catch (Exception e) {
			int errorCode = ((SQLException) e).getErrorCode();
			//if (errorCode == -803) {
			if (e.getMessage().matches(".*Duplicate.*PRIMARY.*") || errorCode == -803 || errorCode==1062) {
				e.printStackTrace();
				throw new Exception("数据库主键冲突！");
			} else {
				e.printStackTrace();
				throw new Exception("数据库操作出错！");
			}
		}
		return "";
	}


}
