//package com.jun.plugin.test;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.alibaba.fastjson2.JSON;
//import com.google.common.collect.Maps;
//import com.jun.plugin.module.utils.PinYinUtil;
//
//import lombok.extern.slf4j.Slf4j;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Slf4j
//public class UpdateDataTest {
//
//	@Resource
//    private JdbcTemplate jdbcTemplate;
//
////    @Test
//    public void contextLoads() throws IOException {
//    	List<Map<String, Object>> list = this.jdbcTemplate.queryForList(" select * from sys_user ");
//    	list.forEach(item->{
//    		log.error("item="+JSON.toJSONString(item));
//    		item.forEach((key,value)->{
//    			log.info("key="+key,"value="+value);
//    			if(key.equalsIgnoreCase("full_name")) {
//    				String id = String.valueOf(item.get("id"));
//    				String username = PinYinUtil.getPinyin(String.valueOf(item.get("real_name")));
//    				this.jdbcTemplate.update("update sys_user set username = ? where id = ? and username !='admin' ", new Object[] {username,id});
//    			}
//    		});
//    	});
//    }
//
//    @Test
//    public void queryMysqlTreeData() throws IOException {
//    	StringBuffer sb = new StringBuffer();
//    	sb.append(" WITH RECURSIVE temp AS (\r\n"
//    			+ "    SELECT rr.*  FROM hr_templet_quota_detail rr WHERE rr.pid is null   \r\n"
//    			+ "  UNION ALL\r\n"
//    			+ "    SELECT r.*   FROM hr_templet_quota_detail r  ,temp tt WHERE tt.id = r.pid\r\n"
//    			+ ") select * from temp tt WHERE  templateid = 1  order by tt.pid,tt.qorder ");
//    	List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sb.toString());
//    	list.forEach(item->{
//    		log.error("item="+JSON.toJSONString(item));
////    		item.forEach((key,value)->{
////    			log.info("key="+key,"value="+value);
////    		});
//    	});
//    }
//
//
//
//
//
//}
