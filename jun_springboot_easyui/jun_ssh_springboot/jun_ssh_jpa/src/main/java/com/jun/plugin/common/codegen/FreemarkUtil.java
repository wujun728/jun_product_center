package com.jun.plugin.common.codegen;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkUtil {
	
	
    public static Template getTemplate(String name) {
        try {
            // 通过Freemaker的Configuration读取相应的ftl
            Configuration cfg = new Configuration();
            // 设定去哪里读取相应的ftl模板文件
            URL url=FreemarkUtil.class.getResource(Constant.TEMPLATE_DIR);
            cfg.setDirectoryForTemplateLoading(new File(url.getFile()));
            // 在模板文件目录中找到名称为name的文件
            Template temp = cfg.getTemplate(name);
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
		Template template = FreemarkUtil.getTemplate("/temp/controller2.ftl");
		System.out.println(template.toString());
	}
    
}
