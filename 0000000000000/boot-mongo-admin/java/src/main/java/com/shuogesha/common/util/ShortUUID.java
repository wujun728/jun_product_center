package com.shuogesha.common.util;

import java.util.UUID;

public class ShortUUID {
	private final static char[] DIGITS64 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_"
			.toCharArray();
	private final static char[] DIGITS64Str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
			.toCharArray();

	public static String next() {
		UUID u = UUID.randomUUID(); 
		return toIDString(u.getMostSignificantBits()) + toIDString(u.getLeastSignificantBits());
	}
	
	public static String nextStr() {
		UUID u = UUID.randomUUID(); 
		return toIDStr(u.getMostSignificantBits()) + toIDStr(u.getLeastSignificantBits());
	}
	
	private static String toIDStr(long l) {
		char[] buf = "00000000000".toCharArray(); // 限定11位长度
		int length = 11;
		long least = 61L; // 0x0000003FL
		do {
			buf[--length] = DIGITS64Str[(int) (l & least)]; // l & least取低6位
			/*
			 * 无符号的移位只有右移，没有左移 使用“>>>”进行移位
			 */
			l >>>= 6;
		} while (l != 0);
		return new String(buf);
	}

	private static String toIDString(long l) {
		char[] buf = "00000000000".toCharArray(); // 限定11位长度
		int length = 11;
		long least = 63L; // 0x0000003FL
		do {
			buf[--length] = DIGITS64[(int) (l & least)]; // l & least取低6位
			/*
			 * 无符号的移位只有右移，没有左移 使用“>>>”进行移位
			 */
			l >>>= 6;
		} while (l != 0);
		return new String(buf);
	}

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		for (int i = 0; i < 1000000L; i++) {
			System.out.println(next());
		}
		System.out.println(System.currentTimeMillis() - time);
	}
}
