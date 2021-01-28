package org.myframework.dao.sqlconfig.impl;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.myframework.commons.util.StringUtils;
import org.myframework.dao.sqlconfig.SqlMapper;

public class VelocitySqlTemplate extends BaseSqlTemplate {

	public String getParsedSql(String sqlKey, Map<String, Object> map) {
		VelocityContext context =  new VelocityContext(map!=null ?map : new HashMap<String, Object>() ) ;
		context.put("StringUtil",  new StringUtil());
		StringWriter w = new StringWriter();
		SqlMapper sqlMapper = sqlConfig.getSqlMapper(sqlKey);
		if (sqlMapper!=null) {
			try {
				Velocity.evaluate(context, w, this.getClass().getName(), sqlMapper.getSqlCode());
			} catch ( Exception e) {
				throw new IllegalArgumentException("Velocity.evaluate {" + sqlKey + "}   error"+ e.getMessage());
			}
		}else{
			throw new IllegalArgumentException("Velocity  sqlConfig  {" + sqlKey + "} not exist");
		}
		return  w.toString() ;
	}

	public static class StringUtil {

		public String parseToSqlOr(String srcString, String columnName){
			return StringUtils.parseToSqlOr(srcString, columnName);
		}

	}



}
