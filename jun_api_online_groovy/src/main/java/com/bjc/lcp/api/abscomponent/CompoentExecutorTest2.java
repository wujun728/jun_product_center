package com.bjc.lcp.api.abscomponent;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.jun.plugin.common.base.interfaces.AbstractExecutor;
import com.jun.plugin.common.base.interfaces.Context;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;

@Component
public class CompoentExecutorTest2 extends AbstractExecutor<Integer, Map<String,Object>> {

//	public CompoentExecutorTest2(Context context) {
//		super(context);
//	}

	protected static ThreadLocal<Context> localContext = new ThreadLocal<Context>();

	protected static ThreadLocal<Integer> localRetCode = new ThreadLocal<Integer>();

	protected static ThreadLocal<Map<String,Object>> localParams = new ThreadLocal<Map<String,Object>>();

	protected static ThreadLocal<Object> localResult = new ThreadLocal<Object>();

	private DruidDataSource ds = new DruidDataSource();


	@PostConstruct
	public void test(){
		//DruidDataSource ds = new DruidDataSource();
		ds.setUrl(SpringUtil.getProperty("spring.datasource.url"));
		Console.log("url = " + SpringUtil.getProperty("spring.datasource.url"));
		ds.setUsername(SpringUtil.getProperty("spring.datasource.username"));
		ds.setPassword(SpringUtil.getProperty("spring.datasource.password"));
	}

	@Override
	public Integer execute(Map<String,Object> params) {
		//BAS000000000274
		Context context = localContext.get();
		String param1 = context.getGlobalStr("param1");

		System.out.println("入参：" + JSONUtil.toJsonStr(params));
		Map<String, Object> map = new HashMap<>();
		try {
			Long pk = null;
			Entity entity = Entity.create("test");
			entity.set("content", "unitTestUser11"+RandomUtil.randomInt());
			entity.set("title", "title-"+RandomUtil.randomInt());
			entity.set("remark", "remark"+RandomUtil.randomInt());;
			try {
				pk = Db.use(ds).insertForGeneratedKey(
					    entity
					);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			entity.set("pk", pk);
			map.put("record", entity);
			System.out.println("返回数据为：" + JSONUtil.toJsonStr(map));
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		//super.localResult.set(map);
		return 0;
	}


}
