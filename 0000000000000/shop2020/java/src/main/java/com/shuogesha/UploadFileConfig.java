package com.shuogesha;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UploadFileConfig {

	@Value("${file.staticAccessPath}")
	private static String staticAccessPath;
	@Value("${file.uploadFolder}")
	private static String uploadFolder;

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setLocation(uploadFolder);
		return factory.createMultipartConfig();
	}

	public static String getStaticAccessPath() {
		return staticAccessPath;
	}

	public static void setStaticAccessPath(String staticAccessPath) {
		UploadFileConfig.staticAccessPath = staticAccessPath;
	}

	public static String getUploadFolder() {
		return uploadFolder;
	}

	public static void setUploadFolder(String uploadFolder) {
		UploadFileConfig.uploadFolder = uploadFolder;
	}
	
	
}