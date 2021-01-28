package sy.test.temp;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class Test {

	public static void main(String[] args) {
		String name = "QUERY_t#id_S_EQ";
		String value = "2013-10-11 11:10";

		String[] filterParams = StringUtils.split(name, "_");
		System.out.println(filterParams.length);
		String columnName = filterParams[1];
		String valueType = filterParams[2];
		String operator = filterParams[3];
		String placeholder = UUID.randomUUID().toString().replace("-", "");// 生成一个随机的参数名称
		System.out.println(columnName);
		System.out.println(valueType);
		System.out.println(operator);
		System.out.println(placeholder);
		System.out.println("#####################");
		
		if (name.startsWith("QUERY_")) {// 如果有需要过滤的字段
			columnName = name.substring(name.indexOf("_") + 1, name.indexOf("_", 6)).replaceAll("#", ".");// 字段名
			valueType = name.substring(name.indexOf("_", 6) + 1, name.lastIndexOf("_"));// 字段类型
			operator = name.substring(name.lastIndexOf("_") + 1);// 操作符
			placeholder = UUID.randomUUID().toString().replace("-", "");// 生成一个随机的参数名称

			System.out.println(columnName);
			System.out.println(valueType);
			System.out.println(operator);
			System.out.println(placeholder);
		}
	}
}
