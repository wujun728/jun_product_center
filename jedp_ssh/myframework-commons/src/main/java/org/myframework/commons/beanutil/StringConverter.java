package org.myframework.commons.beanutil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.converters.AbstractConverter;

public class StringConverter extends AbstractConverter {

	public StringConverter() {
	}

	public StringConverter(Object defaultValue) {
		super(defaultValue);
	}

	protected Class getDefaultType() {
		return String.class;
	}

	protected Object convertToType(Class type, Object value) throws Throwable {
		return value.toString();
	}

	@Override
	public String convertToString(Object value) {
		if (value instanceof Date) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return format.format(value);
		}
		return value.toString();
	}

}
