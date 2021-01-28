package org.coderfun.gen.service;

import java.io.File;
import java.io.IOException;

import org.coderfun.fieldmeta.service.TemplateFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;


/**
 * 
 * 依赖 TemplateFileService
 * 
 * @author klguang[klguang@foxmail.com]
 * @date 2019年8月22日
 */
@Component
public class FreemarkerHelper {

	private static Configuration configuration;
	private static TemplateFileService templateFileService;

	public FreemarkerHelper(@Autowired TemplateFileService templateFileService) {
		try {
			FreemarkerHelper.templateFileService = templateFileService;
			configuration = buildConfiguration(templateFileService.getTemplateAbsoluteDir());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Template getTemplate(String name) {
		try {
			return configuration.getTemplate(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
	private Configuration buildConfiguration(String templateDir) throws IOException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setDirectoryForTemplateLoading(new File(templateDir));
		cfg.setLogTemplateExceptions(false);
		cfg.setWrapUncheckedExceptions(true);
		return cfg;
	}
}
