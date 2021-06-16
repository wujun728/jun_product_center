package com.wangzhixuan.test.base;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 通用测试类
 * @author L.cm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring-mvc.xml",
		"classpath:spring-config.xml"
})
@WebAppConfiguration
public abstract class BaseTest {
	protected MockMvc mockMvc;
	protected MockHttpServletRequest request;
	protected MockHttpServletResponse response;

	@Autowired protected WebApplicationContext wac;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.alwaysDo(MockMvcResultHandlers.print())
				.build();
		request = new MockHttpServletRequest();
		request.setCharacterEncoding("UTF-8");
		response = new MockHttpServletResponse();
	}

}
