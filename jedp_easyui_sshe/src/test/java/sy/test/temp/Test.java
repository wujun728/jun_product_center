package sy.test.temp;

import java.text.ParseException;

import org.apache.commons.lang3.time.DateUtils;

public class Test {

	public static void main(String[] args) {
		String name = "QUERY_t#id_S_EQ";
		String value = "2013-10-11 11:10";
		if (name.startsWith("QUERY_")) {// 如果有需要过滤的字段
			String columnName = name.substring(name.indexOf("_") + 1, name.indexOf("_", 6)).replaceAll("#", ".");// 字段名
			String valueType = name.substring(name.indexOf("_", 6) + 1, name.lastIndexOf("_"));// 字段类型
			String operator = name.substring(name.lastIndexOf("_") + 1);// 操作符
			System.out.println(columnName);
			System.out.println(valueType);
			System.out.println(operator);
			try {
				System.out.println(DateUtils.parseDate(value, new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm" }));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
}
