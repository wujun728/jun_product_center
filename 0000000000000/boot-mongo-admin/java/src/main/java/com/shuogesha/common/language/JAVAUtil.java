package com.shuogesha.common.language;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JAVAUtil {
	/**
	 * 获取包名
	 * @param fileStr
	 * @return
	 */
	public static String getPackage(String fileStr) {
		String regex = "(?im)^\\s*package\\s+([^;]+);";
		Matcher m = Pattern.compile(regex).matcher(fileStr);
		if (m.find()) {
			return m.group(1).trim();
		}
		return "";
	}
	
	/**
	 * 获取公共的类名称
	 * @param fileStr
	 * @return
	 */
	public static String getPublicClass(String fileStr) {
		String regex = "(?m)^\\s*public\\s+class\\s+(\\w+)\\b";
		Matcher m = Pattern.compile(regex).matcher(fileStr);
		if (m.find()) {
			return m.group(1).trim();
		}
		regex = "class\\s+(\\w+)\\b";
		m = Pattern.compile(regex).matcher(fileStr);
 		if (m.find()) {
			return m.group(1).trim();
		}
		return "";
	}
}
