package com.bjc.lcp.api.abscomponent;

import com.alibaba.fastjson2.JSONObject;
//import com.jfinal.plugin.activerecord.Db;
//import com.jfinal.plugin.activerecord.Page;
//import com.jfinal.plugin.activerecord.Record;
import com.jun.plugin.common.base.interfaces.AbstractExecutor;
import com.jun.plugin.db.record.Db;
import com.jun.plugin.db.record.Page;
import com.jun.plugin.db.record.Record;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 组件ID：BAS000000000100
 * 描 述：执行指定的SQL语句（insert/update/delete/select语句）
 * 版本历史：1.0.0版 参数说明：
 * 参 数1：要执行的SQL语句
 * 参 数2：sql参数
 * 参 数3：执行SQL语句处理记录数存放标签
 *
 * 说明：需要把该代码放进DB，api_config，测试JSONOBject对象直接返回-保存在庫裡面
 */
@Component
public class PageJSONOService extends AbstractExecutor<JSONObject, Map<String,Object>> {

	@Override
	public JSONObject execute(Map<String, Object> params) {
		super.initDb();
		super.setParameters(params);
		int pageNumber = super.getParaInt(params,"page");
		int pageSize = super.getParaInt(params,"size");
		String tableName = super.getPara(params,"tableName");
		String columnName = super.getPara("columnName");
		Page<Record> pages = Db.use("master").paginate(pageNumber,pageSize,"select *"," from "+tableName);
		Page<Map> datas = super.getPageMaps(pages);
		return (JSONObject) JSONObject.from(datas);
//		return (JSONObject) JSONObject.toJSON(datas);
	}

}
