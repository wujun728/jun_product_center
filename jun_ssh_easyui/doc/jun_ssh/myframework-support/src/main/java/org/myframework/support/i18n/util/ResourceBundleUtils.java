package org.myframework.support.i18n.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.myframework.commons.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 国际化服务读取messages.properties工具类
 */
public class ResourceBundleUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceBundleUtils.class);
	private static final String ISO_8859_1 = "ISO-8859-1";
	private static final String UTF_8 = "UTF-8";

	/**
	 * 根据区域读取messages.properties
	 * 例如:messages_zh.properties
	 */
	public static ResourceBundle getResourceBundle(String resourceName,	Locale locale) {
		try {
			return ResourceBundle.getBundle(resourceName, locale);
		} catch (MissingResourceException e) {
			LOGGER.warn(e.getMessage());
		}
		return null;
	}

	/**
	 * 格式化消息
	 */
	public static String formatMessage(String paramString, Object[] argsArray) {
		if (argsArray == null) {
			return paramString;
		}
		MessageFormat messageFormat = null;
		String message = null;
		if (paramString != null) {
			messageFormat = new MessageFormat(paramString);
			message = messageFormat.format(argsArray);
		}

		return message;
	}

	/**
	 * 通过KEY获得消息内容
	 */
	public static String getMessageByKey(String key, ResourceBundle resource){
		Assert.notNull(key, "I18N Key must not be null");
		Assert.notNull(resource, "ResourceBundle must not be null");

		String message = null;
		try {
			message = resource.getString(key);
			LOGGER.debug(key  + " --> Final I18N Message: " + message);
			//message = new String(message.getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception e) {
			e.getMessage();
		}
		
		return message != null ? message : key;
	}
}