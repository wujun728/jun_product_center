package com.bjc.lcp.api.component;

import com.alibaba.fastjson2.JSONObject;
//import com.jfinal.plugin.activerecord.Db;
//import com.jfinal.plugin.activerecord.Page;
//import com.jfinal.plugin.activerecord.Record;
import com.jun.plugin.common.base.interfaces.AbstractExecutor;
import com.jun.plugin.common.exception.BusinessException;
import com.jun.plugin.db.record.Db;
import com.jun.plugin.db.record.Page;
import com.jun.plugin.db.record.Record;
import com.jun.plugin.groovy.groovy.GroovyDynamicLoader;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TestJSONObjectService  extends AbstractExecutor<JSONObject, Map<String,Object>> {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	GroovyDynamicLoader groovyDynamicLoader;

	@Override
	public JSONObject execute(Map<String, Object> params) throws BusinessException {
//		SqlSession sqlSession= sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
//		BaseMapper mapper = (BaseMapper) sqlSession.getMapper(groovyDynamicLoader.getClassMap().get("SimpleCodeMapper"));
//		mapper.selectList(new LambdaQueryWrapper());
//		sqlSession.selectOne("SimpleCodeMapper.selectCount1",1);
		super.initDb();
		super.setParameters(params);
		if(super.request==null){
			System.out.println(1111);
		}
		if(super.parameters==null){
			System.out.println(2222);
		}
//		initDb("master", SpringUtil.getProperty("spring.datasource.url"),
//				SpringUtil.getProperty("spring.datasource.username"),
//				SpringUtil.getProperty("spring.datasource.password"));
		int pageNumber = super.getParaInt(params,"page");
		int pageSize = super.getParaInt("size");
		Page<Record> pages = Db.use("master").paginate(pageNumber,pageSize,"select *"," from test_simple_code");
		Page<Map> datas = super.getPageMaps(pages);
//		String servletPath = (String) params.get("path");
//		System.out.println(JSON.toJSONString(params));
//		JSONObject json= new JSONObject();
//		JSONObject json1= new JSONObject();
//		json1.put("username","admin");
//		json1.put("username1","admin1");
//
//		json1.put("nickname","admin2");
//		json1.put("mobile",13888888888L);
//		json.put("userinfo",json1);
////		json.put("simplecode",mapper.selectList(new LambdaQueryWrapper<>()));
////		json.put("simplecode1",mapper1.selectList(new LambdaQueryWrapper<>()));
//		json.put("datas",datas);
		return (JSONObject) JSONObject.from(datas);
	}

}
