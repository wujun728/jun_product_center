package org.coderfun.fieldmeta;



import java.util.Date;

import org.coderfun.fieldmeta.entity.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerTest {
	Logger logger=LoggerFactory.getLogger(LoggerTest.class);
	
	@Test
	public void testLog(){
		

		
		logger.debug("user dao debug log");
        logger.info("user dao info log");
        logger.warn("user dao warn log");
        logger.error("user dao error log");
	}
	
	@Test
	public void testLogformat(){
		
		logger.info("meothod {} time {}", "testLogformat",new Date());
		logger.info("static resource mapping [{}] -> [{}]", "/res**","file:D:/temp");
	}
	
	@Test 
	public void testObject(){
		Project project =new Project();
		project.setName("test");		
		logger.info("project",project);
		
	}
	
	
}
