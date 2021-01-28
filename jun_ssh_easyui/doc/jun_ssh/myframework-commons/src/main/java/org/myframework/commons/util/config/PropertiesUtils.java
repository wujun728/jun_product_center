package org.myframework.commons.util.config;

import java.util.Properties;  

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

  
public class PropertiesUtils {  
	private static Properties props = new Properties(); 
	private static String DEFAULT_PROPERTIES="application.properties";
    private static Logger log = LoggerFactory.getLogger(PropertiesUtils.class);  
	
    public static String getProperty(String key,String... fileName) {  
        try {  
    		props.load(PropertiesUtils.class.getResourceAsStream("/" + ((fileName.length>0)?fileName[0]:DEFAULT_PROPERTIES)));  
        } catch (Exception e) {  
        	log.error("读取配置文件失败："+e.getMessage());
            e.printStackTrace();  
        }  
    	return (String) props.get(key);  
    }
    
    public static void setProperty(String key,String value,String... fileName) {    
        try {  
    		props.load(PropertiesUtils.class.getResourceAsStream("/" + (fileName.length==0?DEFAULT_PROPERTIES:fileName[0])));  
            props.setProperty(key, value); 
        } catch (Exception e) {  
        	log.error("写入配置文件失败："+e.getMessage());
            e.printStackTrace();  
        }  
    }

    
}  