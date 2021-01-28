package sy.test.sy.dao;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import sy.dao.base.BaseDaoI;

import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml", "classpath:spring-druid.xml" })
public class TestDao {

	@Autowired
	private BaseDaoI baseDao;
	
	@Test
	@Transactional
	public void test() {
		
//		List<Map> l = baseDao.findBySql("select t.DESCRIPTION dddd from syresource t");
//		System.out.println(JSON.toJSONString(l));
//
//		List<Map> l2 = baseDao.findBySql("select t.* from syresource t");
//		System.out.println(JSON.toJSONString(l2));
		
		//List<Map> l3 = baseDao.findBySql("select concat('ZB',replace(curdate(),'-',''),'001') as seqtitle;");
		//System.out.println(l3.get(0).get("seqtitle"));
		
		//l3 = baseDao.findBySql("select CONCAT('',DATE_FORMAT(now(),'%Y%m%d'),LPAD((SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_NAME='EMP_MEDICAL_DETAIL')+1,10,'0')) as seqtitle;");
		//System.out.println(l3.get(0).get("seqtitle"));
		
//		select CONCAT('',DATE_FORMAT(now(),'%Y%m%d'),LPAD((SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_NAME='medical_report_data_copy')+1,10,'0'))  
//		
	}

}
