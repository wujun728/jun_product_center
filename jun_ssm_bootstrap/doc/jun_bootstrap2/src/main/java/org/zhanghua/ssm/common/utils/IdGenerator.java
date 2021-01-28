/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package org.zhanghua.ssm.common.utils;

import java.security.SecureRandom;
import java.util.UUID;

import org.zhanghua.ssm.common.security.Encodes;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * 
 * @author Wujun
 * @version 2013-01-15
 */
public class IdGenerator {

	private static SecureRandom random = new SecureRandom();

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 使用SecureRandom随机生成Long.
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return Encodes.encodeBase62(randomBytes);
	}

	public static void main(String[] args) {
		/*
		 * System.out.println(IdGen.uuid()); System.out.println(IdGen.uuid().length()); for (int i = 0; i < 1000; i++) { System.out.println(IdGen.randomLong() + "  " + IdGen.randomBase62(5)); }
		 */

		/*
		 * AlternativeJdkIdGenerator idGenerator = new AlternativeJdkIdGenerator(); System.out.println(idGenerator.generateId());
		 */
	}

}
