package sy.test.org.apache.commons.lang3;

import org.apache.commons.lang3.StringUtils;

public class TestStringUtils {

	public static void main(String[] args) {
		
		System.out.println(StringUtils.trim(null));

		String str = "Hello World";

		// isEmpty和isBlank的区别在于isEmpty不会忽略空格,
		// " "<--对于这样的字符串isEmpty会认为不是空,
		// 而isBlank会认为是空,isBlank更常用
		System.out.println(StringUtils.isEmpty(str));
		System.out.println(StringUtils.isNotEmpty(str));
		System.out.println(StringUtils.isBlank(str));
		System.out.println(StringUtils.isNotBlank(str));

		// strip --> 去除两端的aa
		// stripStart --> 去除开始位置的Hell
		// stripEnd --> 去除结尾位置的orld
		System.out.println(StringUtils.strip(str, "aa"));
		System.out.println(StringUtils.stripStart(str, "Hell"));
		System.out.println(StringUtils.stripEnd(str, "orld"));

		// 返回字符串在第2次出现o的位置
		System.out.println(StringUtils.ordinalIndexOf(str, "o", 2));

		// 获取str 开始为Hello结尾为orld中间的字符串
		// 注意此方法返回字符串 -->substringBetween
		// 注意此方法返回字符串数组(多了个s) --> substringsBetween
		System.out.println(StringUtils.substringBetween(str, "Hell", "orld"));
		System.out.println(StringUtils.substringsBetween(str, "Hell", "orld"));

		// 重复字符串,第二种重载形式为在重复中用hahah拼接
		System.out.println(StringUtils.repeat("str ", 3));
		System.out.println(StringUtils.repeat("str", "HHH", 2));

		// 统计参数2在字符串中出现的次数
		System.out.println(StringUtils.countMatches(str, "l"));

		// 判断字符串是否全小写或大写
		System.out.println(StringUtils.isAllLowerCase(str));
		System.out.println(StringUtils.isAllUpperCase(str));

		// isAlpha --> 全部由字母组成返回true
		// isNumeric -->全部由数字组成返回true
		// isAlphanumeric -->全部由字母或数字组成返回true
		// isAlphaSpace -->全部由字母或空格组成返回true
		// isWhitespace -->全部由空格组成返回true
		System.out.println(StringUtils.isAlpha(str));
		System.out.println(StringUtils.isNumeric(str));
		System.out.println(StringUtils.isAlphanumeric(str));
		System.out.println(StringUtils.isAlphaSpace(str));
		System.out.println(StringUtils.isWhitespace(str));

		// 缩进字符串,第二参数是最少长度为 4,要包含...
		// 现在Hello World输出为H...
		System.out.println(StringUtils.abbreviate(str, 4));

		// 首字母大写或小写
		System.out.println(StringUtils.capitalize(str));
		System.out.println(StringUtils.uncapitalize(str));

		// 将字符串数组转变为一个字符串,通过","拼接,支持传入iterator和collection
		System.out.println(StringUtils.join(new String[] { "Hello", "World" }, ","));

	}
}
