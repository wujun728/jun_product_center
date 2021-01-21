package org.myframework.support.i18n.impl;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.myframework.support.i18n.II18nService;
import org.myframework.support.i18n.util.ResourceBundleUtils;
import org.myframework.web.commons.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
/**
 * 国际化服务实现
 */
@Service("i18nService")
public class SimpleI18NServiceImpl implements II18nService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleI18NServiceImpl.class);

	private String defaultResourceName = "messages";
	private String currentResourceName;
	private Locale currentLocale;
	private static Map<String, ResourceBundle> resourceMap = new HashMap<String, ResourceBundle>();

	/**
	 * 获取当前已有Locale下的国际化代码的字符串
	 */
	public String getMessage(String key) {
		if (this.currentLocale == null) {
			if ((WebUtils.getRequest()  != null)
					&& (WebUtils.getRequest().getLocale() != null))
				this.currentLocale = WebUtils.getRequest().getLocale();
			else {
				this.currentLocale = Locale.CHINESE;
			}
		}

		ResourceBundle resource = null;
		if (resourceMap.keySet().contains(this.currentLocale.getCountry())) {
			resource = (ResourceBundle) resourceMap.get(this.currentLocale
					.getCountry());
		} else {
			resource = ResourceBundle.getBundle(this.defaultResourceName,
					this.currentLocale);
			if (resource != null) {
				resourceMap.put(this.currentLocale.getCountry(), resource);
			}
		}
		return ResourceBundleUtils.getMessageByKey(key, resource);
	}

	/**
	 * 获取Locale下的国际化代码的字符串
	 */
	public String getMessage(Locale locale, String key) {
		if (locale == null) {
			return key;
		}

		ResourceBundle resource = null;
		if (resourceMap.keySet().contains(locale.getCountry())) {
			resource = (ResourceBundle) resourceMap.get(locale.getCountry());
		} else {
			if (StringUtils.isNotBlank(this.currentResourceName))
				resource = ResourceBundle.getBundle(this.currentResourceName,
						locale);
			else {
				resource = ResourceBundle.getBundle(this.defaultResourceName,
						locale);
			}
			if (resource != null) {
				resourceMap.put(locale.getCountry(), resource);
			}
		}
		return ResourceBundleUtils.getMessageByKey(key, resource);
	}

	/**
	 * 获取Locale下的国际化代码的字符串
	 */
	public String getMessage(String locale, String key) {
		if (locale == null) {
			return key;
		}

		String language = null;
		String country = null;
		int index = locale.indexOf("_");
		if (index != -1) {
			language = locale.substring(0, index);
			country = locale.substring(index + 1);
		}

		if ((language == null) || (country == null)) {
			return key;
		}

		ResourceBundle resource = null;
		if (resourceMap.keySet().contains(country)) {
			resource = (ResourceBundle) resourceMap.get(country);
		} else {
			if (StringUtils.isNotBlank(this.currentResourceName))
				resource = ResourceBundle.getBundle(this.currentResourceName, new Locale(language, country));
			else {
				resource = ResourceBundle.getBundle(this.defaultResourceName, new Locale(language, country));
			}
			if (resource != null) {
				resourceMap.put(country, resource);
			}
		}
		return ResourceBundleUtils.getMessageByKey(key, resource);
	}

	/**
	 * 获取国际化代码的字符串
	 */
	public String getMessage(String key, Object[] argsArray) {
		String paramString = getMessage(key);
		return ResourceBundleUtils.formatMessage(paramString, argsArray);
	}

	public String getMessage(Locale locale, String key, Object[] argsArray) {
		String paramString = getMessage(locale, key);
		return ResourceBundleUtils.formatMessage(paramString, argsArray);
	}

	public String getMessage(String locale, String key, Object[] argsArray) {
		String paramString = getMessage(locale, key);
		return ResourceBundleUtils.formatMessage(paramString, argsArray);
	}

	public String getCurrentResourceName() {
		return this.currentResourceName;
	}

	public void setCurrentResourceName(String currentResourceName) {
		this.currentResourceName = currentResourceName;
	}

	public Locale getCurrentLocale() {
		return this.currentLocale;
	}

	public void setCurrentLocale(Locale currentLocale) {
		this.currentLocale = currentLocale;
	}
}