package sy.test.sy.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sy.service.InitAppServiceI;
import sy.service.InitServiceI;

/**
 * 测试初始化数据库
 * 
 * @author Wujun
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml",
		"classpath:spring-hibernate.xml", "classpath:spring-druid.xml" })
public class TestInitAppService {
	
	@Autowired
	private InitAppServiceI initAppService;
	
	@Test
	public void initDb() {
//	   initAppService.initDb();
	}
	
	@Test
	public void initDBData() {
		/**
		 * 业务层，先用sql脚本执行创建表， 再增加数据
		 */
		//initAppService.execDbData();
		//initAppService.initDb();
	}
	

	

}
