package sy.converter.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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

	private static final String FROMDATE = "yyyy-MM-dd";
	private static final String FROMDATETIME = "yyyy-MM-dd HH:mm:ss";

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (values == null || values.length == 0) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(FROMDATETIME);
		Date date = null;
		String dateString = values[0];
		if (dateString != null && !dateString.trim().equals("") && !dateString.trim().equals("null")) {
			try {
				date = sdf.parse(dateString);
			} catch (ParseException e) {
				date = null;
			}
			if (date == null) {
				sdf = new SimpleDateFormat(FROMDATE);
				try {
					date = sdf.parse(dateString);
				} catch (ParseException e) {
					date = null;
				}
			}
		}
		return date;
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
