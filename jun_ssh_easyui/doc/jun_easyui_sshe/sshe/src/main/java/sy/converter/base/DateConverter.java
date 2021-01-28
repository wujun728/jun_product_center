package sy.converter.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.util.StrutsTypeConverter;

/**
 * 日期转换器
 * 
 * struts2默认自带日期转换器，但是如果前台传递的日期字符串是"null"的话，会出现错误，所以这里写了一个公共的日期转换器来替代默认的
 * 
 * @author Wujun
 * 
 */
public class DateConverter extends StrutsTypeConverter {
	private static final Log logger = LogFactory.getLog(DateConverter.class);

	private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	private static final String DATE_PATTERN = "yyyy-MM-dd";

	private static final String MONTH_PATTERN = "yyyy-MM";

	private static final String FROMDATE = "yyyy-MM-dd";
	private static final String FROMDATETIME = "yyyy-MM-dd HH:mm:ss";

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (values == null || values.length == 0) {
			return null;
		}
		Date date = null;
		String dateString = values[0];
		if (!StringUtils.isBlank(dateString)) {
			try {
				DateUtils.parseDate(dateString, FROMDATETIME, FROMDATE);
				date = doConvertToDate(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	private Date doConvertToDate(Object value) {
		Date result = null;

		if (value instanceof String) {

			try {
				result = DateUtils.parseDate((String) value, new String[] {
						DATE_PATTERN, DATETIME_PATTERN, MONTH_PATTERN });
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			if (result == null && StringUtils.isNotEmpty((String) value)) {

				try {
					result = new Date(new Long((String) value).longValue());
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} else if (value instanceof Object[]) {
			// let's try to convert the first element only
			Object[] array = (Object[]) value;

			if ((array != null) && (array.length >= 1)) {
				value = array[0];
				result = doConvertToDate(value);
			}

		} else if (Date.class.isAssignableFrom(value.getClass())) {
			result = (Date) value;
		}
		return result;
	}

	@Override
	public String convertToString(Map context, Object o) {
		if (o instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat(FROMDATETIME);
			return sdf.format(o);
		}
		return "";
	}

}
