package com.bjc.lcp.api.abscomponent;

//import com.jfinal.plugin.activerecord.Db;
import com.jun.plugin.common.Result;
import com.jun.plugin.common.base.interfaces.AbstractExecutor;
import com.jun.plugin.db.record.Db;
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
public class RecordRemoveService extends AbstractExecutor<Result, Map<String,Object>> {

	@Override
	public Result execute(Map<String, Object> params) {
		super.initDb();
		super.setParameters(params);
		return delete(params);
	}

	public Result delete(Map<String, Object> params) {
		String tableName = super.getPara(params,"tableName");
		String primaryKey = super.getPara(params,"primaryKey");
		String id = super.getPara(params,"id");
		Boolean result = false;
		if(primaryKey.contains(",")){
			String [] ids = id.split(",");
			result = Db.use("master").deleteByIds(tableName,primaryKey, ids);
		}else{
			result = Db.use("master").deleteByIds(tableName,primaryKey, id);
		}
		return Result.success(result);
	}

}
