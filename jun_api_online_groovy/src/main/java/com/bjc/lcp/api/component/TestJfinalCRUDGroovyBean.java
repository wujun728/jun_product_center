package com.bjc.lcp.api.component;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.druid.DruidPlugin;
import io.github.wujun728.common.Result;
import io.github.wujun728.common.base.interfaces.IExecutor;
import io.github.wujun728.rest.util.ActiveRecordUtil;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 需要把该代码放进DB，api_groovy，测试Jfinal-CURD-保存在庫裡面
 */
public class TestJfinalCRUDGroovyBean  implements IExecutor<Result, Map<String,Object>> {

	@PostConstruct
	public void test(){
		ActiveRecordUtil.initActiveRecordPlugin("oa", SpringUtil.getProperty("spring.datasource.url"),
				SpringUtil.getProperty("spring.datasource.username"),
				SpringUtil.getProperty("spring.datasource.password"));
	}



	@Override
	public Result execute(Map<String, Object> parms) throws Exception {
		Record data = new Record().set("title", "1111").set("content", "content").set("remark", "remark");
		Boolean resutl = Db.use("test1").save("test", data);
		Map<String, Object> params = parms;
		Map<String, Object> map = new HashMap<>();
		map.put("reward", "6666");
		map.put("resutl", resutl);
		System.out.println("返回数据为：" + JSONUtil.toJsonStr(map));
		return Result.success(map);
	}

}

