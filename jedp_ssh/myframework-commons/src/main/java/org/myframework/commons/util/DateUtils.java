package org.myframework.commons.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 
 * <ol>日期处理类，  
 * <li>{@link 常用方法1：getCurrentDateAsString() 获取当前日期 yyyy-MM-dd }</li>
 * <li>{@link 常用方法2：getCurrentDateTimeAsString() 获取当前日期时间 yyyy-MM-dd HH:mm:ss }</li>
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年11月5日
 *
 */
public class DateUtils {
	public final static String FORMAT_DATE_DEFAULT = "yyyy-MM-dd";

	public final static String FORMAT_DATE_YYYYMMDD = "yyyyMMdd";
	public final static String FORMAT_DATE_YYYYMMDDHH = "yyyyMMddHH"; 
	public final static String FORMAT_DATE_YYYYMMDDHHMM = "yyyyMMddHHmm"; 
	public final static String FORMAT_DATE_YYYY_MM_DD_HHMMSS = "yyyyMMddHHmmss";

	public final static String FORMAT_DATE_YYYY_MM_DD = "yyyy-MM-dd";
	
	public final static String FORMAT_DATE_PATTERN_1="yyyy/MM/dd";
	public final static String FORMAT_DATE_PATTERN_2="yyyy/M/dd";
	public final static String FORMAT_DATE_PATTERN_3="yyyy/MM/d";
	public final static String FORMAT_DATE_PATTERN_4="yyyy/M/d";

	public final static String FORMAT_DATE_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public final static String FORMAT_DATE_YYYY_MM_DD_HHMM = "yyyy-MM-dd HHmm";

	public final static String FORMAT_DATE_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

	public final static String FORMAT_DATE_HH_MM = "HH:mm";

	public final static String FORMAT_DATE_HH_MM_SS = "HH:mm:ss";

	public final static String FORMAT_DATE_HHMM = "HHmm";

	public final static String FORMAT_DATE_HHMMSS = "HHmmss";

	public static final String FORMAT_WORK_TIME = "yyyy-MM-dd HHmmss";
	/**
	 * Compares two Dates from their string value.
	 * 
	 * @param stringValue1
	 *            Date 1 as string value.
	 * @param stringValue2
	 *            Date 2 as string value.
	 * 
	 * @return the value <code>0</code> if the argument stringValue1 is equal
	 *         to stringValue2; a value less than <code>0</code> if this
	 *         stringValue1 is before the stringValue2 as Date; and a value
	 *         greater than <code>0</code> if this stringValue1 is after the
	 *         stringValue2.
	 * @since 1.2
	 */
	public final static int compareDate(String stringValue1, String stringValue2)
			throws ParseException {
		Date date1 = tryParse(stringValue1);
		if (date1 == null)
			throw new ParseException("Can not parse " + stringValue1
					+ " to Date.", 0);
		Date date2 = tryParse(stringValue2);
		if (date2 == null)
			throw new ParseException("Can not parse " + stringValue1
					+ " to Date.", 0);
		return date1.compareTo(date2);
	}
	
	
	public final static int compareDate(String stringValue1, String stringValue2,String formatPattern)
			throws ParseException {
		Date date1 = tryParse(stringValue1,formatPattern);
		if (date1 == null)
			throw new ParseException("Can not parse " + stringValue1
					+ " to Date.", 0);
		Date date2 = tryParse(stringValue2,formatPattern);
		if (date2 == null)
			throw new ParseException("Can not parse " + stringValue1
					+ " to Date.", 0);
		return date1.compareTo(date2);
	}

	/**
	 * Returns current system date as formatted string value with default format
	 * pattern.
	 * 
	 * @return current system date.
	 * 
	 * @see #FORMAT_DATE_DEFAULT
	 */
	public final static String getCurrentDateAsString() {
		return getCurrentDateAsString(FORMAT_DATE_DEFAULT);
	}
	
	/**
	 * Returns current system date as formatted string value with default format
	 * pattern.
	 * 
	 * @return current system date time .
	 * 
	 * @see #FORMAT_DATE_YYYY_MM_DD_HH_MM_SS
	 */
	public final static String getCurrentDateTimeAsString() {
		return getCurrentDateAsString(FORMAT_DATE_YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * Returns current system date as formatted string value with given format
	 * pattern.
	 * 
	 * @param formatPattern
	 *            format pattern.
	 * @return current system date.
	 * 
	 */
	public final static String getCurrentDateAsString(String formatPattern) {
		Date date = new Date();
		return format(date, formatPattern);
	}

	/**
	 * Returns current system date.
	 * 
	 * @return current system date.
	 */
	public final static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * Format Date value as string value with default format pattern.
	 * 
	 * @param date
	 *            Date value.
	 * @return formatted date as string value.
	 * 
	 * @see #FORMAT_DATE_DEFAULT
	 */
	public final static String format(Date date) {
		if (date == null) {
			return "";
		}
		return format(date, FORMAT_DATE_DEFAULT);
	}

	/**
	 * Format Date value as string value with default format pattern.
	 * 
	 * @param date
	 *            Date value.
	 * @return formatted date as string value.
	 * 
	 * @see #FORMAT_DATE_DEFAULT
	 */
	public final static String formatDateTime(Date date) {
		if (date == null) {
			return "";
		}
		return format(date, FORMAT_DATE_YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * Format Date value as string value with default format pattern.
	 * 
	 * @param date
	 *            Date value.
	 * @return formatted date as string value.
	 * 
	 * @see #FORMAT_DATE_DEFAULT
	 */
	public final static String formatTimestamp(Date date) {
		if (date == null) {
			return "";
		}
		return format(date, "yyyy-MM-dd HH:mm:ss.SSS");
	}

	/**
	 * Format Date value as string value with default format pattern.
	 * 
	 * @param date
	 *            Date value.
	 * @return formatted date as string value.
	 * 
	 * @see #FORMAT_DATE_DEFAULT
	 */
	public final static Date parseTimestamp(String date) {
		if (date == null) {
			return null;
		}
		return parse(date, "yyyy-MM-dd HH:mm:ss.SSS");
	}

	/**
	 * Format Date value as string value with given format pattern.
	 * 
	 * @param date
	 *            Date value.
	 * @param formatPattern
	 *            format pattern.
	 * @return formatted date as string value.
	 * 
	 * @see #FORMAT_DATE_DEFAULT
	 * @see #FORMAT_DATE_YYYY_MM_DD
	 * @see #FORMAT_DATE_YYYY_MM_DD_HH_MM
	 * @see #FORMAT_DATE_YYYY_MM_DD_HH_MM_SS
	 * @see #FORMAT_DATE_YYYY_MM_DD_HHMMSS
	 */
	public final static String format(Date date, String formatPattern) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(formatPattern).format(date);
	}

	/**
	 * Parse string value to Date with default format pattern.
	 * 
	 * @param stringValue
	 *            date value as string.
	 * @return Date represents stringValue.
	 * @see #FORMAT_DATE_DEFAULT
	 */
	public final static Date parse(String stringValue) {
		return parse(stringValue, FORMAT_DATE_DEFAULT);
	}

	/**
	 * Parse string value to Date with given format pattern.
	 * 
	 * @param stringValue
	 *            date value as string.
	 * @param formatPattern
	 *            format pattern.
	 * @return Date represents stringValue, null while parse exception occurred.
	 * @see #FORMAT_DATE_DEFAULT
	 */
	public final static Date parse(String stringValue, String formatPattern) {
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		try {
			return format.parse(stringValue);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Try to parse string value to date.
	 * 
	 * @param stringValue
	 *            string value.
	 * @return Date represents stringValue, null while parse exception occurred.
	 */
	public final static Date tryParse(String stringValue,String... pattern) {
		if(pattern.length==0){
			return parse(stringValue, FORMAT_DATE_YYYY_MM_DD);
		}else{
			return parse(stringValue, pattern[0]);
		}
	}

	/**
	 * get day of week
	 * @param SUN_FST_DAY_OF_WEEK
	 * @return
	 */
	public static int getDayOfWeek(int SUN_FST_DAY_OF_WEEK) {
		if (SUN_FST_DAY_OF_WEEK > 7 || SUN_FST_DAY_OF_WEEK < 1)
			return 0;
		if (SUN_FST_DAY_OF_WEEK == 1)
			return 7;
		return SUN_FST_DAY_OF_WEEK - 1;
	}

	public static Timestamp parseTimestamp(String stringValue,
			String formatPattern) {
		return new Timestamp(parse(stringValue, formatPattern).getTime());
	}

	public static Timestamp parseTimestamp(Date d) {
		return new Timestamp(d.getTime());
	}
	   //-----------------------------------------------------------------------
    /**
     * Adds a number of milliseconds to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addMilliseconds(Date date, int amount) {
        return add(date, Calendar.MILLISECOND, amount);
    }
    
    //-----------------------------------------------------------------------
    /**
     * Adds a number of minutes to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addMinutes(Date date, int amount) {
        return add(date, Calendar.MINUTE, amount);
    }
    //-----------------------------------------------------------------------
    /**
     * Adds to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param calendarField  the calendar field to add to
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     * 
     */
    public static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }
	/**
	 * Main method for test.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String stringValue = "2008/05/06";
//		System.out.println("Parse \"" + stringValue
//				+ "\" using format pattern \"" + DateUtils.FORMAT_DATE_DEFAULT
//				+ "\" with method \"DateUtils.parse()\", result: "
//				+ DateUtils.parse(stringValue));
//		stringValue = "20080506";
//		System.out.println("Parse \"" + stringValue
//				+ "\" using method \"DateUtils.tryParse()\", result: "
//				+ DateUtils.tryParse(stringValue));
//		Date d=DateUtils.tryParse(stringValue);
//		String s=DateUtils.format(d, DateUtils.FORMAT_DATE_DEFAULT);
//		System.out.print("--->>>"+s);
		try {
			System.out.println(DateUtils.compareDate("2013-02-02 20:00:10", "2013-02-02 20:00:00"));
			System.out.println(DateUtils.compareDate("2013-02-02 20:00:10", "2013-02-02 20:00:00","yyyy-MM-dd HH:mm:ss"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
