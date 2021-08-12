
package com.shuogesha.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/* *
 *类名：UtilDate
 *功能：自定义订单类
 *详细：工具类，可以用作获取系统日期、订单编号等
 *版本：3.3
 *日期：2012-08-17
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
public class UtilDate {

	/** 年月日时分秒(无下划线) yyyyMMddHHmmss */
	public static final String dtLong = "yyyyMMddHHmmss";

	/** 完整时间 yyyy-MM-dd HH:mm:ss */
	public static final String simple = "yyyy-MM-dd HH:mm:ss";

	/** 年月日(无下划线) yyyyMMdd */
	public static final String dtShort = "yyyyMMdd"; 

	private static SimpleDateFormat format = new SimpleDateFormat(simple);
 	private static SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM/dd");
	private static SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat format4 = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
	 * 
	 * @return 以yyyyMMddHHmmss为格式的当前系统时间
	 */
	public static String getOrderNum() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtLong);
		return df.format(date) + getThree();
	}

	/**
	 * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
	 * 
	 * @return 以yyyyMMddHHmmss为格式的当前系统时间
	 */
	public static String getTradeNo() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtLong);
		return df.format(date) + getEighteen();
	}

	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getDateFormatter() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(simple);
		return df.format(date);
	}

	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * 
	 * @return
	 */
	public static String getDate() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtShort);
		return df.format(date);
	}

	/**
	 * 产生随机的三位数
	 * 
	 * @return
	 */
	public static String getThree() {
		Random rad = new Random();
		return rad.nextInt(1000) + "";
	}

	/**
	 * 产生随机的十八数
	 * 
	 * @return
	 */
	public static String getEighteen() {
		Random rad = new Random();
		return rad.nextInt(100000) + "" + rad.nextInt(100000) + "" + rad.nextInt(100000) + "" + rad.nextInt(1000);
	}

	/**
	 * 获取现在的时间
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String date) throws ParseException {
		return format.parse(date);
	}

	/**
	 * 获取现在的时间
	 * 
	 * @return
	 */
	public static String getDateBegin() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		return f.format(new Date());
	}

	/**
	 * 获取现在的时间
	 * 
	 * @return
	 */
	public static String getDateEnd() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		return f.format(new Date());
	}

	/**
	 * 获取现在的时间
	 * 
	 * @return
	 */
	public static String getNow() {
		return format.format(new Date());
	}

	/**
	 * yyyy/MM/dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getNow1() {
		return format1.format(new Date());
	}

	/**
	 * yyyy/MM/dd
	 * 
	 * @return
	 */
	public static String getNow2() {
		return format2.format(new Date());
	}
	

	/**
	 * yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String tomorrow() {
		 Calendar calendar = Calendar.getInstance();
	        calendar.setTime(new Date());
	        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
 		return format3.format(calendar.getTime());
	} 
	/**
	 * yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String yesterday() {
		 Calendar calendar = Calendar.getInstance();
	        calendar.setTime(new Date());
	        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
		return format3.format(calendar.getTime());
	} 
	/**
	 * yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String nowDay() { 
		return format3.format(new Date());
	} 

	// 验证日期格式
	public static boolean checkNow2(String date) {
		try {
			format2.parse(date);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 获取现在的时间
	 * 
	 * @return
	 */
	public static String getDateStr(Date date) {
		return format.format(date);
	}

}
