package com.bjc.lcp.api.abscomponent;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson2.JSON;
//import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
//import com.jfinal.plugin.activerecord.Db;
//import com.jfinal.plugin.activerecord.Record;
import com.jun.plugin.common.Result;
import com.jun.plugin.common.base.interfaces.AbstractExecutor;
import com.jun.plugin.db.record.Db;
import com.jun.plugin.db.record.Record;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CompoentExecutorSave extends AbstractExecutor<Result, Map<String,Object>> {
	String appNo = "A000101";

	@PostConstruct
	public void test(){
		Console.log("url = " + SpringUtil.getProperty("spring.datasource.url"));
//		initDb(appNo, SpringUtil.getProperty("spring.datasource.url"),
//				SpringUtil.getProperty("spring.datasource.username"),
//				SpringUtil.getProperty("spring.datasource.password"));
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl( "jdbc:mysql://localhost:3306/db_qixing_bk?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2b8");
		ds.setUsername("root");
		ds.setPassword("");

		//MetaUtil.getTableMeta(ds,"").get

		Boolean isExtsis = false;
		try {
			Db.init(appNo,ds);
//			ActiveRecordPlugin arp = new ActiveRecordPlugin(appNo, ds);
//			arp.setDevMode(true);
//			arp.setShowSql(true);
//			arp.start();
			//Db.use(appNo);
		} catch (IllegalArgumentException e) {
			isExtsis = true;
		}
		if( !isExtsis ){

		}
	}

	@Override
	public Result execute(Map params) {
		System.out.println("入参：" + JSONUtil.toJsonStr(params));
		Map<String, Object> map = new HashMap<>();
		try {
			Object[] sqliParams = Arrays.asList("aaa"+ RandomUtil.randomInt(),"bbb"+ RandomUtil.randomInt(),"cccc"+ RandomUtil.randomInt(),"dddd"+ RandomUtil.randomInt()).toArray() ;
			int icount = Db.use(appNo).update("insert into test (title,content,remark,field_name_test ) VALUES ( ?, ?, ?, ? ) ", sqliParams);
			List datas = Db.use(appNo).query(" select * from  test   ");
			List<Record> datas2 = Db.use(appNo).find(" select * from  test   ");
			System.out.println(JSON.toJSONString(datas));
			map.put("datas", datas);
			map.put("datas2", datas2);
			map.put("datas22", datas2.get(0).getColumns());
			map.put("sqliParams", sqliParams);
			map.put("count", icount);
			System.out.println("返回数据为：" + JSONUtil.toJsonStr(map));
		} catch (Exception e) {
			e.printStackTrace();
			return Result.fail(e.getMessage());
		}
		return Result.success(map);
	}


}
