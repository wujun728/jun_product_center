package sy.util.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

/**
 * 日期工具类
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 */
public class DateUtil {

	private static final String[] PARSEPATTERNS = new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy.MM.dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd HH:mm", "yyyy.MM.dd HH:mm", "yyyy-MM-dd HH", "yyyy/MM/dd HH", "yyyy.MM.dd HH", "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd" };
	private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 将字符串转换成日期类型,自动匹配格式
	 * 
	 * @param date
	 * @return
	 */
	public static Date stringToDate(String date) {
		try {
			return DateUtils.parseDate(date, PARSEPATTERNS);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 字符串格式转日期
	 * 
	 * @param date
	 * @param parsePatterns
	 * @return
	 */
	public static Date stringToDate(String date, String... parsePatterns) {
		try {
			return DateUtils.parseDate(date, parsePatterns);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateToString(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
		return "";
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		return dateToString(date, PATTERN);
	}

	/**
	 * 增加n天后的日期
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date addDay(Date date, int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, n);// 增加n天
		return calendar.getTime();
	}

	/**
	 * 增加n个月后的日期
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date addMonth(Date date, int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, n);// 增加n个月
		return calendar.getTime();
	}

	/**
	 * 获取当前月第一天
	 * 
	 * @return
	 */
	public static Date firstDayOfMonth() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		return c.getTime();
	}

}
