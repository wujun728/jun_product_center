package org.myframework.commons.beanutil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.Converter;

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
public class BeanUtils2 extends BeanUtils {
	
	public static void register(Converter converter, Class<?> clazz) {
		ConvertUtilsBean2.getInstance().register(converter, clazz);
	}

	public static void copyProperties(Object dest, Object orig) {
		try {
			BeanUtilsBean2.getInstance().copyProperties(dest, orig);
		} catch ( Exception e) {
			 
		}
	}

}
