package com.shuogesha.common.util;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 
 * @author zhaohaiyuan
 *
 */
public class StrUtil {
	public static String genRandomNum() {
		int maxNum = 36;
		int i;
		int count = 0;
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'c', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");
		char a=(char)(Math.random()*26+'a');//随机字母开头
		pwd.append(a);
		Random r = new Random();
		while (count < 11) {
			i = Math.abs(r.nextInt(maxNum));
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]); 
				count++;
			}
		}
		return pwd.toString();
	}

	/**
	 * 字母加字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String genRandom(int length) {
		String str = "";
		String num = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

			if ("char".equalsIgnoreCase(charOrNum)) // 字符串
			{
//				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
//				str += (char) (choice + random.nextInt(26));
				int choice = 97; // 指定字符串为小写字母
				str += (char) (choice + random.nextInt(26));
 			} else if ("num".equalsIgnoreCase(charOrNum)) // 数字
			{
 				num += String.valueOf(random.nextInt(10));
			}
		}
		return str+num;
	}
	
	public static void main(String[] args) {
		System.out.println(genRandom(10)); 
	}

}
