package sy.test.org.apache.commons.lang3.time;

import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class TestDateUtils {

	public static void main(String[] args) {
		Date day1 = new Date();
		/*
		 * 由于Aache的DateUtils和DateFormatUtils并没有Joda强大, 所以在这里只作简单的示例
		 */

		// 增加一天
		System.out.println(DateUtils.addDays(day1, 1));

		// 减少一年
		System.out.println(DateUtils.addYears(day1, -1));

		// 格式化时间,第三参数为国际化,表示按美国时间显示
		System.out.println(DateFormatUtils.format(day1, "yyyy-MM-dd", Locale.UK));
	}

}
