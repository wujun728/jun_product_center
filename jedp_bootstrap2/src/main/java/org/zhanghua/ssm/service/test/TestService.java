package org.zhanghua.ssm.service.test;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhanghua.ssm.common.service.CrudService;
import org.zhanghua.ssm.entity.test.Test;
import org.zhanghua.ssm.mapper.test.TestMapper;

@Service
public class TestService extends CrudService<Test, String> {

	@Autowired
	private TestMapper mapper;

	public void saveTx() throws Exception {
		// 测试事务是否正常
		Test test = new Test();
		test.setName("Tx");
		test.setCreateTime(new Date());
		mapper.save(test);
		int i = 2 / 0; // 抛出异常
		System.out.println(i);
	}

}
