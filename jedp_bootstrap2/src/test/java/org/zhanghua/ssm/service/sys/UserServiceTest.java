package org.zhanghua.ssm.service.sys;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.zhanghua.ssm.entity.sys.User;
import org.zhanghua.ssm.service.sys.OrgService;
import org.zhanghua.ssm.service.sys.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
// defaultRollback = true会回滚掉测试操作产生的数据
public class UserServiceTest {

	@Autowired
	private UserService userService;
	@Autowired
	private OrgService orgService;

	@Test
	public void testSave() throws Exception {
//		Org org=orgService.get(2);
//		for (int i = 1; i <= 1000; i++) {
//			User user=new User();
//			user.setUsername("test"+i);
//			user.setPassword("123456");
//			user.setPhone("0755-12341234");
//			user.setIsDelete(0);
//			user.setIsLock(0);
//			user.setCreateTime(new Date());
//			user.setOrg(org);
//			userService.save(user);
//		}
	}
	
	@Test
	public void testGet()throws Exception{
		
		System.out.println("--------------1------------------");
		User user1 = userService.get("1");
		System.out.println(user1);
		System.out.println("--------------2------------------");
		User user2 = userService.get("1");
		System.out.println(user2);
	}
	
	
	@Test
	public void testQueryList()throws Exception{
		System.out.println("--------------1------------------");
		List<User> users = userService.queryList();
		System.out.println(users);
		System.out.println("--------------2------------------");
		List<User> users2 = userService.queryList();
		System.out.println(users2);
	}
	
}
