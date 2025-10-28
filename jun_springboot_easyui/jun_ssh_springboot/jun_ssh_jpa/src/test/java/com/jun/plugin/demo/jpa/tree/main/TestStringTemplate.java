package com.jun.plugin.demo.jpa.tree.main;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TestStringTemplate {
	@Test
	public void testStringTL() throws IOException {
	    StringTemplateLoader stl = new StringTemplateLoader();
	    String template = "${key}";
	    stl.putTemplate("hello", template);
	    Object source = stl.findTemplateSource("hello");
	    Reader reader = stl.getReader(source, "utf-8");
	    String dest = IOUtils.toString(reader);
	    Assert.assertEquals(template, dest);

	}
	
	@Test
	public void testStringTmpl() throws IOException, TemplateException{
        String templateContent="欢迎：${name}";  
        stringLoader.putTemplate("myTemplate",templateContent);  
        
        StringWriter writer = new StringWriter();
        
       Template template = cfg.getTemplate("myTemplate");
       Map root = new HashMap<>();
       root.put("name", "javaboy2012");  
       template.process(root, writer);  
       System.out.println(writer.toString());
       writer.close();
	}
	
    static final Configuration cfg = new Configuration();  
    static final StringTemplateLoader stringLoader = new StringTemplateLoader(); 
    static{
    	cfg.setTemplateLoader(stringLoader);
    }
}
