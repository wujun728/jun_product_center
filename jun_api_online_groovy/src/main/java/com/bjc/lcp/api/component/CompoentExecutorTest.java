package com.bjc.lcp.api.component;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.jun.plugin.common.Result;
import com.jun.plugin.common.base.interfaces.IExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;

@Component
public class CompoentExecutorTest implements IExecutor<Result, Map<String,Object>> {

	@Autowired
	JdbcTemplate jdbcTemplate;

	DruidDataSource ds = new DruidDataSource();

	@PostConstruct
	public void test(){
		//DruidDataSource ds = new DruidDataSource();
		ds.setUrl(SpringUtil.getProperty("spring.datasource.url"));
		Console.log("url = " + SpringUtil.getProperty("spring.datasource.url"));
		ds.setUsername(SpringUtil.getProperty("spring.datasource.username"));
		ds.setPassword(SpringUtil.getProperty("spring.datasource.password"));
	}

	@Override
	public Result execute(Map params) {
		System.out.println("入参：" + JSONUtil.toJsonStr(params));
		if(jdbcTemplate!=null) {
			try {
				System.out.println("jdbcTemplate注入成功：" + jdbcTemplate.getDataSource().getConnection());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Map<String, Object> map = new HashMap<>();
		try {
			Long pk = null;
			Entity entity = Entity.create("test")
				    .set("content", "unitTestUser11"+RandomUtil.randomInt())
				    .set("title", "title-"+RandomUtil.randomInt())
				    .set("remark", "remark"+RandomUtil.randomInt());
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
		}
		return Result.success(map);
	}


}
