package org.coderfun.fieldmeta;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourcePathTest {
	
	@Test
	public void test() throws IOException{
		ClassPathResource resource = new ClassPathResource("/");
		System.out.println(resource.getPath());
		System.out.println(resource.getFile().getAbsolutePath());
		
	}
}
