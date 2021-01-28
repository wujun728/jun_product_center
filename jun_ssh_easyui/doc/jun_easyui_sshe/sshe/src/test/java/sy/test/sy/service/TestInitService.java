package sy.test.sy.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import sy.service.InitAppServiceI;
import sy.service.InitServiceI;

/**
 * 测试初始化数据库
 * 
 * @author Wujun
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml", "classpath:spring-druid.xml" })
public class TestInitService {

	@Autowired
	private InitServiceI initService;
	//@Autowired
	//private InitAppServiceI initAppService;
	@Test
	public void initResourceDb() {
		//initService.initResourceDb("1161");
//		initService.initResourceDb("100");
//		initService.initResourceDb("101");
//		initService.initResourceDb("102");
//		initService.initResourceDb("103");
//		initService.initResourceDb("104");
//		initService.initResourceDb("105");
//		initService.initResourceDb("106");
//		initService.initResourceDb("107");
//		initService.initResourceDb("108");
//		initService.initResourceDb("109");
//		initService.initResourceDb("110");
//		initService.initResourceDb("111");
//		initService.initResourceDb("112");
//		initService.initResourceDb("113");
//		initService.initResourceDb("114");
//		initService.initResourceDb("115");
//		initService.initResourceDb("116");
//		initService.initResourceDb("117");
		initService.initResourceDb("1071011");
		
		
		//initService.execDbData();
		//initAppService.execDbData();
		//initAppService.initDb();
	}
	
//	@Test
	public void initDb() {
		initService.initDb();
		//initService.execDbData();
		//initAppService.execDbData();
		//initAppService.initDb();
	}
	
	//@Test
	public void initDBData() {
		//String sql = "INSERT INTO sys_def VALUES ('10100100', '2013-12-15 19:29:41', '证件类型', null, null, null, '1', '0', null, '证件类型', '0', '0', '2013-12-15 19:29:51');";
		//initService.execSQL(sql);
		//initService.initDbData();
	}

}
