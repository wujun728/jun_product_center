package sy.util.base;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	 * 日期类型转字符串类型
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static String dateToString(Date date, String dateFormat) {
		if (date == null)
			return "";
		try {
			return new SimpleDateFormat(dateFormat).format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 日期类型转字符串类型，默认返回yyyy-MM-dd HH:mm:ss格式
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		return dateToString(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static void main(String[] args) {
		System.out.println(dateToString(new Date()));
	}

}
