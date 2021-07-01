package com.du.lin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.du.lin.dao")
@SpringBootApplication
public class LinApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(LinApplication.class, args);
		System.out.println("**********************************");
		System.out.println("*      Lin start-up success      *");
		System.out.println("**********************************"); 
	}
}
