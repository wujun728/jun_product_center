package org.myframework.commons.beanutil;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.myframework.commons.util.config.PropertiesParser;

/**
 * 
 * <ol>
 * <li>{@link  }</li>
 * 
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2016年2月17日
 *
 */
public class BeanUtilsBean2 extends BeanUtilsBean {

	private final Log log = LogFactory.getLog(BeanUtilsBean2.class);

	private final static BeanUtilsBean2 beanUtilsBean = new BeanUtilsBean2();
	
	public static BeanUtilsBean getInstance( ) {
		return beanUtilsBean;
	}

	public BeanUtilsBean2() {
		 super(new ConvertUtilsBean2(), new PropertyUtilsBean());
	}

	@Override
	public void copyProperties(Object dest, Object orig)
			throws IllegalAccessException, InvocationTargetException {
		boolean stripNull = true;
		// Validate existence of the specified beans
		if (dest == null) {
			throw new IllegalArgumentException("No destination bean specified");
		}
		if (orig == null) {
			throw new IllegalArgumentException("No origin bean specified");
		}
		if (log.isDebugEnabled()) {
			log.debug("BeanUtils.copyProperties(" + dest + ", " + orig + ")");
		}

		// Copy the properties, converting as necessary
		if (orig instanceof DynaBean) {
			DynaProperty[] origDescriptors = ((DynaBean) orig).getDynaClass()
					.getDynaProperties();
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				// Need to check isReadable() for WrapDynaBean
				// (see Jira issue# BEANUTILS-61)
				if (getPropertyUtils().isReadable(orig, name)
						&& getPropertyUtils().isWriteable(dest, name)) {
					Object value = ((DynaBean) orig).get(name);
					if (stripNull && value == null) {
						log.debug("value is null orig (" + orig + ", name= "
								+ name + ")");
					} else {
						copyProperty(dest, name, value);
					}
				}
			}
		} else if (orig instanceof Map) {
			@SuppressWarnings("unchecked")
			// Map properties are always of type <String, Object>
			Map<String, Object> propMap = (Map<String, Object>) orig;
			for (Map.Entry<String, Object> entry : propMap.entrySet()) {
				String name = entry.getKey();
				if (getPropertyUtils().isWriteable(dest, name)) {
					copyProperty(dest, name, entry.getValue());
				}
			}
		} else if (orig instanceof Properties) {
			@SuppressWarnings("unchecked")
			// copy from Properties file
			Properties propMap = (Properties) orig;
			try {
				setBeanProps(dest, propMap);
			} catch (Exception e) {
				// Should not happen;
			}
		} else /* if (orig is a standard JavaBean) */ {
			PropertyDescriptor[] origDescriptors = getPropertyUtils()
					.getPropertyDescriptors(orig);
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				if ("class".equals(name)) {
					continue; // No point in trying to set an object's class
				}
				if (getPropertyUtils().isReadable(orig, name)
						&& getPropertyUtils().isWriteable(dest, name)) {
					try {
						Object value = getPropertyUtils()
								.getSimpleProperty(orig, name);
						if (stripNull && value == null) {
							log.debug("value is null orig (" + orig + ", name= "
									+ name + ")");
						} else {
							copyProperty(dest, name, value);
						}
					} catch (NoSuchMethodException e) {
						// Should not happen
					}
				}
			}
		}

	}

	void setBeanProps(Object obj, Properties props) throws Exception {
		java.util.Enumeration<Object> keys = props.keys();
		while (keys.hasMoreElements()) {
			String name = (String) keys.nextElement();
			String c = name.substring(0, 1).toUpperCase(Locale.US);
			String methName = "set" + c + name.substring(1);
			java.lang.reflect.Method setMeth = getSetMethod(methName,
					obj.getClass());
			try {
				if (setMeth == null) {
					throw new NoSuchMethodException(
							"No setter for property '" + name + "'");
				}

				Class<?>[] params = setMeth.getParameterTypes();
				if (params.length != 1) {
					throw new NoSuchMethodException(
							"No 1-argument setter for property '" + name + "'");
				}

				// does the property value reference another property's value?
				// If so, swap to look at its value
				PropertiesParser refProps = new PropertiesParser(props);
				String refName = name;

				if (params[0].equals(int.class)) {
					setMeth.invoke(obj, new Object[] { Integer
							.valueOf(refProps.getIntProperty(refName)) });
				} else if (params[0].equals(long.class)) {
					setMeth.invoke(obj, new Object[] {
							Long.valueOf(refProps.getLongProperty(refName)) });
				} else if (params[0].equals(float.class)) {
					setMeth.invoke(obj, new Object[] { Float
							.valueOf(refProps.getFloatProperty(refName)) });
				} else if (params[0].equals(double.class)) {
					setMeth.invoke(obj, new Object[] { Double
							.valueOf(refProps.getDoubleProperty(refName)) });
				} else if (params[0].equals(boolean.class)) {
					setMeth.invoke(obj, new Object[] { Boolean
							.valueOf(refProps.getBooleanProperty(refName)) });
				} else if (params[0].equals(String.class)) {
					setMeth.invoke(obj, new Object[] {
							refProps.getStringProperty(refName) });
				} else {
					throw new NoSuchMethodException(
							"No primitive-type setter for property '" + name
									+ "'");
				}
			} catch (NumberFormatException nfe) {
				throw new Exception("Could not parse property '" + name
						+ "' into correct data type: " + nfe.toString());
			}
		}
	}

	<T> java.lang.reflect.Method getSetMethod(String name, Class<T> clz) {
		Method[] methods = clz.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().equals(name)) {
				return method;
			}
		}
		return null;
	}

}
