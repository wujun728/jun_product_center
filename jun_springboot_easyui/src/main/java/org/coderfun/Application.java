package org.coderfun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RestController;



@RestController
@SpringBootApplication
@ImportResource(locations={"classpath:app-jpa.xml"})
public class Application {
	
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);	
	}
}
