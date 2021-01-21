package org.myframework.commons.beanutil;

import java.util.Date;

import org.apache.commons.beanutils.ConvertUtilsBean;

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
public class ConvertUtilsBean2 extends ConvertUtilsBean {

	public ConvertUtilsBean2() {
		super();
		//
		register(new DateConverter(), Date.class);
		register(new StringConverter(), String.class);
	}

	protected static ConvertUtilsBean getInstance() {
		return BeanUtilsBean2.getInstance().getConvertUtils();
	}
}
