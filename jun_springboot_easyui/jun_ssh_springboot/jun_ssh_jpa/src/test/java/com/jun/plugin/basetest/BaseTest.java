package com.jun.plugin.basetest;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:app-jpa.xml")
public abstract class BaseTest {
	@Rule
	public ExpectedException thrown= ExpectedException.none();
}
