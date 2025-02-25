package com.bjc.lcp.api.component;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.jun.plugin.common.Result;
import com.jun.plugin.common.base.interfaces.IExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;

@Component
public class CommonDBCompoent implements IExecutor<Result,Map<String,Object>> {

	DruidDataSource ds = new DruidDataSource();

	@PostConstruct
	public void test(){
		ds.setUrl(SpringUtil.getProperty("project.datasource.url"));
		ds.setUsername(SpringUtil.getProperty("project.datasource.username"));
		ds.setPassword(SpringUtil.getProperty("project.datasource.password"));
	}

	@Override
	public Result execute(Map params) {
		JdbcTemplate jt = new JdbcTemplate(ds);
		if(jt!=null) {
			System.out.println("jdbcTemplate初始化成功" );
		}
		Object[] sqliParams = Arrays.asList("aaa"+ RandomUtil.randomInt(),"bbb"+ RandomUtil.randomInt(),"cccc"+ RandomUtil.randomInt(),"dddd"+ RandomUtil.randomInt()).toArray() ;
		int icount = jt.update("insert into test (title,content,remark,field_name_test ) VALUES ( ?, ?, ?, ? ) ", sqliParams);
		return Result.success(icount);
	}


}
