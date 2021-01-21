package com.xiongyw.util;

import org.apache.commons.lang.StringUtils;

public class StringUtil {
	public static String trimComma(
			String para) {
		if (StringUtils
				.isNotBlank(para)) {
			if (para.endsWith(",")) {
				return para
						.substring(
								0,
								para.length() - 1);
			} else {
				return para;
			}
		} else {
			return "";
		}
	}
}
