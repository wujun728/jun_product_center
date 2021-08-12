package com.shuogesha.common.util;

import java.math.BigDecimal;

public class BigDecimalUtils {
	private BigDecimalUtils() {

	}

	/**
	 * 判断num1是否小于num2
	 * 
	 * @param num1
	 * @param num2
	 * @return num1小于num2返回true
	 */
	public static boolean lessThan(BigDecimal num1, BigDecimal num2) {
		return num1.compareTo(num2) == -1;
	}

	/**
	 * 判断num1是否小于等于num2
	 * 
	 * @param num1
	 * @param num2
	 * @return num1小于或者等于num2返回true
	 */
	public static boolean lessEqual(BigDecimal num1, BigDecimal num2) {
		return (num1.compareTo(num2) == -1) || (num1.compareTo(num2) == 0);
	}

	/**
	 * 判断num1是否大于num2
	 * 
	 * @param num1
	 * @param num2
	 * @return num1大于num2返回true
	 */
	public static boolean greaterThan(BigDecimal num1, BigDecimal num2) {
		return num1.compareTo(num2) == 1;
	}

	/**
	 * 判断num1是否大于等于num2
	 * 
	 * @param num1
	 * @param num2
	 * @return num1大于或者等于num2返回true
	 */
	public static boolean greaterEqual(BigDecimal num1, BigDecimal num2) {
		return (num1.compareTo(num2) == 1) || (num1.compareTo(num2) == 0);
	}

	/**
	 * 判断num1是否等于num2
	 * 
	 * @param num1
	 * @param num2
	 * @return num1等于num2返回true
	 */
	public static boolean equal(BigDecimal num1, BigDecimal num2) {
		return num1.compareTo(num2) == 0;
	}
}
