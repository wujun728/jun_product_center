package com.projectm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
  * 设置虚拟路径，访问绝对路径下资源
  *
  */
@Configuration
public class UploadFilePathConfig implements WebMvcConfigurer{
    @Value("${mproject.staticUpload}")
    private String staticUpload;
    @Value("${mproject.uploadFolderPath}")
    private String uploadFolderPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(staticUpload).addResourceLocations("file:" + uploadFolderPath);
    }
}