package org.myframework.commons.util.config;

import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 
 * <ol>读取application.properties配置文件
 * <li>{@link  }</li>
 * 
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年12月17日
 *
 */
public class AppConfigUtils {
	private static Log logger = LogFactory.getLog(AppConfigUtils.class);
	
	private   PropertiesConfiguration config = new PropertiesConfiguration();
	
	private static AppConfigUtils INSTANCE = new AppConfigUtils();
	
	private AppConfigUtils() {
		config.setFileName("application.properties");
		config.setReloadingStrategy(new FileChangedReloadingStrategy());
		try {
			config.load();
		} catch (ConfigurationException e) {
			logger.error(e.getMessage());
		}
	}
	
	public PropertiesConfiguration getPropertiesConfiguration() {
		return config;
	}

	public static AppConfigUtils getInstance() {
		return INSTANCE ;
	}
	
	public static PropertiesConfiguration getConfig() {
		return AppConfigUtils.getInstance().getPropertiesConfiguration() ;
	}
	
}
