package sy.test.org.apache.commons.lang3.math;

import org.apache.commons.lang3.math.NumberUtils;

public class TestNumberUtils {

	public static void main(String[] args) {
		String str = "12.7";

		// 判断字符串是否为整数
		System.out.println(NumberUtils.isDigits(str));

		// 判断字符串是否为数字
		System.out.println(NumberUtils.isNumber(str));

		// 获取参数中最大的值,支持传入数组
		System.out.println(NumberUtils.max(10, 20, 30));

		// 获取参数中最小的值,支持传入数组
		System.out.println(NumberUtils.min(10, 20, 30));

		// 将字符串转换为int类型,支持float,long,short等数值类型
		System.out.println(NumberUtils.toInt("12"));

		// 通过字符串创建BigDecimal类型 ,支持int,float,long等数值
		System.out.println(NumberUtils.createBigDecimal(str));

	}

}
