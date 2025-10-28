package com.jun.plugin.demo.hello.Main;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jun.plugin.basetest.BaseTest;
import com.jun.plugin.common.utils.MyPrinter;
import com.jun.plugin.demo.jpa.service.UserService;

public class ServiceTest extends BaseTest {
	@Autowired
	UserService userService;

	@Test
	public void testService() {
		MyPrinter.printJson(userService.getById(1));

	}
}
