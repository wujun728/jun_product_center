package sy.util.base;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * 
 * 
 */
public class DateUtil {

	public static void main(String[] args) {
		System.out.println(DateUtil.stringToDate("2013-01-03",null));
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            格式
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
		return dateToString(date, "yyyy-MM-dd hh:mm:ss");
	}

	public static String getNowDate() {
		String temp_str = "";
		Date dt = new Date();
		// 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		temp_str = sdf.format(dt);

		return temp_str;
	}
	
	public static Date stringToDate(String str,String formatStr) {
		if(formatStr == null ){
			formatStr = "yyyy-MM-dd hh:mm:ss";
		}
		DateFormat format = new SimpleDateFormat(formatStr);
		
		Date date = null;
		try {
			// Fri Feb 24 00:00:00 CST 2012
			date = format.parse(str);
		} catch (ParseException e) {
			//e.printStackTrace();
			format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = format.parse(str);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		// 2012-02-24
		//date = java.sql.Date.valueOf(str);

		return date;
	}
}
