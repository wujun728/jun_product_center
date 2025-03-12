package com.jun.plugin.service;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jun.plugin.snakerflow.WorkflowsConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test2YamlList {

	 
	@Test
	public void test() throws Exception {
		List<Map<String, String>> list = WorkflowsConfig.getList();
		list.forEach(item->{
			System.out.println(item);
		});
//		JSONArray.toJSON();
	}
	 
}