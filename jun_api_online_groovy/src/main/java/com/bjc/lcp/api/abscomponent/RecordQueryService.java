package com.bjc.lcp.api.abscomponent;
//import com.jfinal.plugin.activerecord.Db;
import com.jun.plugin.common.Result;
import com.jun.plugin.common.base.interfaces.AbstractExecutor;
import com.jun.plugin.db.record.Db;
import com.jun.plugin.groovy.util.JdbcUtil;
import com.jun.plugin.sql.SqlEngine;
import com.jun.plugin.sql.SqlMeta;
import org.springframework.stereotype.Component;
import java.sql.SQLException;
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
public class RecordQueryService extends AbstractExecutor<Result, Map<String,Object>> {

	@Override
	public Result execute(Map<String, Object> params) throws SQLException {
		super.initDb();
		super.setParameters(params);
		int pageNumber = super.getParaInt(params,"page");
		int pageSize = super.getParaInt(params,"size");
		params.put("start",pageSize*(pageNumber-1));
		String tableName = super.getPara(params,"tableName");
		String columnName = super.getPara("columnName");
		String sqlText = "  SELECT   \n" +
				"    id,\n" +
				"    name,\n" +
				"    age,\n" +
				"    status,\n" +
				"    birthday,\n" +
				"    remarks,\n" +
				"    create_time\n" +
				"FROM  \n" +
				"    test_simple_code_copy1   \n" +
				"WHERE \n" +
				"        1=1\n" +
				"    <if test = \"  title != null   and title != ''  \">\n" +
				"        and id = #{id}\n" +
				"    </if>\n" +
				"    <if test = \"  name != null   and name != ''  \"> \n" +
				"        and name = #{name}\n" +
				"    </if> \n" +
				"    <if test = \"  status != null   and status != ''  \"> \n" +
				"        and status = #{status}\n" +
				"    </if> \n" +
				"LIMIT \n" +
				"    #{start}, #{size}    ";
		SqlMeta sqlMeta = SqlEngine.getEngine().parse(sqlText, params);
		Object data = SqlEngine.executeSql(Db.use("master").getConfig().getConnection(), sqlMeta.getSql(), sqlMeta.getJdbcParamValues());
		return Result.success(data);
	}
}
