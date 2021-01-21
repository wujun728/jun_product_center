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
		List<Map> l = baseDao.findBySql("select t.DESCRIPTION dddd from syresource t");
		System.out.println(JSON.toJSONString(l));

		List<Map> l2 = baseDao.findBySql("select t.* from syresource t");
		System.out.println(JSON.toJSONString(l2));
	}

}
