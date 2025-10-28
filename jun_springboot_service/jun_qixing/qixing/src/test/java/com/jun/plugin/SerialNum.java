package com.jun.plugin;

import java.text.SimpleDateFormat;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 产生流水号工具类
 * 
 * @version V1.0
 * 
 * @date: 2013-11-16 下午5:21:37
 * 
 */

public class SerialNum {
	private static String count = "000";

	private static String dateValue = "20131115";

	/**
	 * 
	 * 产生流水号
	 * 
	 */

	public synchronized static String getMoveOrderNo(String preStr) {
		long No = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		String nowdate = sdf.format(new Date());

		No = Long.parseLong(nowdate);

		if (!(String.valueOf(No)).equals(dateValue)) {
			count = "000";

			dateValue = String.valueOf(No);

		}

		String num = String.valueOf(No);

		num += getNo(count);

		num = preStr + num;

		return num;

	}
 

	/**
	 * 
	 * 返回当天的订单数+1
	 * 
	 */

	public static String getNo(String s) {
		String rs = s;

		int i = Integer.parseInt(rs);

		i += 1;

		rs = "" + i;

		for (int j = rs.length(); j < 3; j++) {
			rs = "0" + rs;

		}

		count = rs;

		return rs;

	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(getMoveOrderNo("XM"));

		}

	}

}