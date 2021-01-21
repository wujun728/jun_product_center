package org.myframework.commons.beanutil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.converters.DateTimeConverter;

public class DateConverter extends DateTimeConverter {

	public DateConverter() {
		// TODO Auto-generated constructor stub
	}

	protected String convertToString(Object value) throws Throwable {
		if(value instanceof Date) {
			 DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
			 return format.format(value);
		}
        return value.toString();
    }

	@Override
	public <T> T convert(Class<T> type, Object value) {

		return super.convert(type, value);
	}

	@Override
	protected <T> T convertToType(Class<T> targetType, Object value)
			throws Exception {
		super.setUseLocaleFormat(true);
		super.setPatterns(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
		return super.convertToType(targetType, value);
	}

	@Override
	protected Class<?> getDefaultType() {
		return Date.class;
	}
}
