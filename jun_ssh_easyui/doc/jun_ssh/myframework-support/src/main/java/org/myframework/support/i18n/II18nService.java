package org.myframework.support.i18n;  

import java.util.Locale;

/**
 * 国际化服务接口
 */
public interface II18nService {
	
	/**
	 * 获取当前已有Locale下的国际化代码的字符串
	 */
	public String getMessage(String key);
	
	public String getMessage(String key, Object[] argsArray);
	
	/**
	 * 获取给定Locale下的国际化代码的字符串
	 */
	public String getMessage(Locale locale, String key);
	
	public String getMessage(Locale locale, String key, Object[] argsArray);
	
	/**
	 * 获取给定语言国家下的国际化代码的字符串
	 * @param locale 格式 "zh_CN"
	 */
	public String getMessage(String locale, String key);
	
	public String getMessage(String locale, String key, Object[] argsArray);

}