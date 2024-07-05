package com.bjc.lcp.api.component;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import io.github.wujun728.common.Result;
import io.github.wujun728.common.base.interfaces.IExecutor;
import io.github.wujun728.rest.util.ActiveRecordUtil;

import java.util.Map;

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
public class TestResultJSONService implements IExecutor<Result, Map<String,Object>> {

	public void initDb(String appNo, String url, String username, String password) {
		ActiveRecordUtil.initActiveRecordPlugin(appNo,url,username,password);
	}

	@Override
	public Result execute(Map<String, Object> params) throws Exception{
		
		String servletPath = (String) params.get("path");
		System.out.println(JSON.toJSONString(params));
		JSONObject json= JSON.parseObject(" {\r\n"
				+ "		\"userinfo\":{\r\n"
				+ "		\"id\":1,\r\n"
				+ "		\"username\":\"admin\",\r\n"
				+ "		\"nickname\":\"admin\",\r\n"
				+ "		\"mobile\":\"13888888888\",\r\n"
				+ "		\"avatar\":\"\",\r\n"
				+ "		\"score\":0,\r\n"
				+ "		\"token\":\"c8edcb1d-8c5c-4e5d-9e53-71d7024f9030\",\r\n"
				+ "		\"user_id\":1,\r\n"
				+ "		\"createtime\":1593422850,\r\n"
				+ "		\"expiretime\":1596014850,\r\n"
				+ "		\"expires_in\":2592000}} ");
		return Result.success(json);
	}

}
