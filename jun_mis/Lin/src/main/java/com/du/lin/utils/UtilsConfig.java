package com.du.lin.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;


@Configuration
public class UtilsConfig {
	@Bean
	public Gson gson(){
		return new Gson();
	}
	@Bean
	public TreeDataUtil treeDataUtil(){
		return new TreeDataUtil();
	}
	
	@Bean
	public BeanUtil beanUtil(){
		return new BeanUtil();
	}
	
}
