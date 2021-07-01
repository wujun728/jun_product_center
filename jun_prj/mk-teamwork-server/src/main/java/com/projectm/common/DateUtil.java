package com.projectm.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 *
 * @description: dateUtil类
 * @ClassName: DateUtil.java
 * @author: gencya
 * @Version:1.0
 *
 */
public class DateUtil {

	public static final String PATTERN_DATE = "yyyy-MM-dd";
	public static final String PATTERN_TIME = "HH:mm:ss";
	public static final String PATTERN_HOURMINUTE = "HH:mm";
	public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_DATETIME_MIN = "yyyy-MM-dd HH:mm";
	public static final String PATTERN_MONTH = "yyyy-MM";
	public static final String PATTERN_YEAR = "yyyy";
	public static final String PATTERN_MONTH_DAY = "MM-dd";

	private static final String PATTERN_FULL = "yyyy-MM-dd HH:mm:ss.SSS";

	public static final Date parse(String pattern, String source) {
		try {
			return new SimpleDateFormat(pattern, Locale.US).parse(source);
		} catch (ParseException e) {
			throw new RuntimeException("parse date error : ", e);
		}
		}

   	public static boolean isSameDay(Date date1, Date date2) {
		if(date1 != null && date2 != null) {
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date1);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date2);
			return isSameDay(cal1, cal2);
		} else {
			throw new IllegalArgumentException("The date must not be null");
		}
	}
   	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if(cal1 != null && cal2 != null) {
			return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
		} else {
			throw new IllegalArgumentException("The date must not be null");
		}
	}

	/**
	 * 将 String 转换为Date
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static final Date parseStr2Date(String source, String pattern) {
		try {
			return new SimpleDateFormat(pattern).parse(source);
		} catch (ParseException e) {
			throw new RuntimeException("parse date error : ", e);
		}
	}

	/***
	 * 将java.util.Date 转换为 String
	 * @param date
	 * @param pattern DatePatternEnum
	 * @return
	 */
	public static final String parseDate2Str(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	public static final Date parseDateTime(String source) {
		try {
			return new SimpleDateFormat(PATTERN_DATETIME).parse(source);
		} catch (ParseException e) {
			throw new RuntimeException("parse date error : ", e);
		}
	}

	public static final Date parseDate(String source) {
		try {
			return new SimpleDateFormat(PATTERN_DATE).parse(source);
		} catch (ParseException e) {
			throw new RuntimeException("parse date error : ", e);
		}
	}

	public static final Date parseTime(String source) {
		try {
			return new SimpleDateFormat(PATTERN_TIME).parse(source);
		} catch (ParseException e) {
			throw new RuntimeException("parse date error : ", e);
		}
	}

	public static final Date parseFull(String source) {
		try {
			return new SimpleDateFormat(PATTERN_FULL).parse(source);
		} catch (ParseException e) {
			throw new RuntimeException("parse date error : ", e);
		}
	}

	public static final String format(String pattern, Date date) {
		return new SimpleDateFormat(pattern, Locale.US).format(date);
	}

	public static final String formatDateTime(Date date) {
		return new SimpleDateFormat(PATTERN_DATETIME).format(date);
	}

	public static final String getCurrentDateTime(){
		return formatDateTime(new Date());
	}

	public static final String formatDate(Date date) {
		return new SimpleDateFormat(PATTERN_DATE).format(date);
	}

	public static final String formatTime(Date date) {
		return new SimpleDateFormat(PATTERN_TIME).format(date);
	}

	public static final String formatFull(Date date) {
		return new SimpleDateFormat(PATTERN_FULL).format(date);
	}

	public static final String format(String outPatt, String inPatt, String source) {
		return format(outPatt, parse(inPatt, source));
	}

	public static final String getTimestamp(String pattern) {
		return format(pattern, new Date());
	}

	public static final int calDValueOfYear(Date fromDate, Date toDate) {
		Calendar sCal = Calendar.getInstance();
		Calendar eCal = Calendar.getInstance();
		sCal.setTime(fromDate);
		eCal.setTime(toDate);

		return eCal.get(Calendar.YEAR) - sCal.get(Calendar.YEAR);
	}

	public static final int calDValueOfMonth(Date fromDate, Date toDate) {
		Calendar sCal = Calendar.getInstance();
		Calendar eCal = Calendar.getInstance();
		sCal.setTime(fromDate);
		eCal.setTime(toDate);

		return 12 * (eCal.get(Calendar.YEAR) - sCal.get(Calendar.YEAR))
				+ (eCal.get(Calendar.MONTH) - sCal.get(Calendar.MONTH));
	}

	public static final int calDValueOfDay(Date fromDate, Date toDate) {
		return (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
	}

	public static final Date getFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public static final Date getFirstDayOfWeek(Date date) {
		return getFirstDayOfWeek(date, Calendar.MONDAY);
	}

	public static final Date getLastDayOfWeek(Date date) {
		return getLastDayOfWeek(date, Calendar.MONDAY);
	}

	public static final Date getFirstDayOfWeek(Date date, int firstDayOfWeek) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(firstDayOfWeek);

		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public static final Date getLastDayOfWeek(Date date, int firstDayOfWeek) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(firstDayOfWeek);

		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		calendar.add(Calendar.DAY_OF_YEAR, 7);
		calendar.add(Calendar.MILLISECOND, -1);

		return calendar.getTime();
	}

	public static final Date[] getWeek(Date date) {
		return getWeek(date, Calendar.MONDAY);
	}

	public static final Date[] getWeek(Date date, int firstDayOfWeek) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(firstDayOfWeek);

		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date firstDate = calendar.getTime();

		calendar.add(Calendar.DAY_OF_YEAR, 7);
		calendar.add(Calendar.MILLISECOND, -1);
		Date lastDate = calendar.getTime();

		return new Date[] { firstDate, lastDate };
	}

	public static java.sql.Date toSQLDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	public static java.sql.Date getSQLDate() {
		return new java.sql.Date(System.currentTimeMillis());
	}

	public static java.sql.Timestamp getSQLTimestamp() {
		return new java.sql.Timestamp(System.currentTimeMillis());
	}

	public static java.sql.Timestamp getTimestamp(int day) {
		return new java.sql.Timestamp(System.currentTimeMillis() + 24 * 60 * 60 * 1000 * day);
	}

	/**
	 * 对输入日期进行增减
	 *
	 * @param date      输入日期
	 * @param field     1则代表的是对年份操作，2是对月份操作，3是对星期操作，5是对日期操作，11是对小时操作，12是对分钟操作，13是对秒操作，14是对毫秒操作
	 * @param increment 要增减的值
	 *           e.g. 增加一天 （date,11,24） or (date,5,1)
	 */
	public static Date add(Date date, int field, int increment) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.add(field, increment);
		return cal.getTime();
	}

	public static Date set(Date date, int field, int value) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(field, value);
		return cal.getTime();
	}

	/**
	 * 返回指定日期的 凌晨 和 最后一秒
	 *
	 * @param date 当前时间
	 * @flag 0 返回yyyy-MM-dd 00:00:00日期<br>
	 *       1 返回yyyy-MM-dd 23:59:59日期
	 * @return
	 */
	public static Date weeHours(Date date, int flag) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		// 时分秒（毫秒数）
		long millisecond = hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000;
		// 凌晨00:00:00
		cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);

		if (flag == 0) {
			return cal.getTime();
		} else if (flag == 1) {
			// 凌晨23:59:59
			cal.setTimeInMillis(cal.getTimeInMillis() + 23 * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000);
		}
		return cal.getTime();
	}

	public static Long getMillisTime() {
		return System.currentTimeMillis();
	}

	/**
	 * isOverlap:(判断时间是否有交集). <br/>
	 */
	public static boolean isOverlap(LocalDateTime startdate1, LocalDateTime enddate1, LocalDateTime startdate2,
			LocalDateTime enddate2) {
		Long leftStartDate = startdate1.toInstant(ZoneOffset.of("+8")).toEpochMilli();
		Long leftEndDate = enddate1.toInstant(ZoneOffset.of("+8")).toEpochMilli();
		Long rightStartDate = startdate2.toInstant(ZoneOffset.of("+8")).toEpochMilli();
		Long rightEndDate = enddate2.toInstant(ZoneOffset.of("+8")).toEpochMilli();
		return ((leftStartDate >= rightStartDate) && leftStartDate < rightEndDate)
				|| ((leftStartDate > rightStartDate) && leftStartDate <= rightEndDate)
				|| ((rightStartDate >= leftStartDate) && rightStartDate < leftEndDate)
				|| ((rightStartDate > leftStartDate) && rightStartDate <= leftEndDate);
	}

	public static List<String> findDaysStr(String begintTime, String endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dBegin = null;
		Date dEnd = null;
		try {
			dBegin = sdf.parse(begintTime);
			dEnd = sdf.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<String> daysStrList = new ArrayList<String>();
		daysStrList.add(sdf.format(dBegin));
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(dEnd);
		while (dEnd.after(calBegin.getTime())) {
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			String dayStr = sdf.format(calBegin.getTime());
			daysStrList.add(dayStr);
		}
		return daysStrList;
	}

}
