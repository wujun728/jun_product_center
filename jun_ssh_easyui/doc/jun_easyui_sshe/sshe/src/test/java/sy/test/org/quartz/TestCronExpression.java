package sy.test.org.quartz;

import java.text.ParseException;
import java.util.Date;

public class TestCronExpression {

	public static void main(String[] args) {
		try {
			CronExpressionEx exp = new CronExpressionEx("* 1-30 13 * * ? ");

			System.out.println("cron: " + exp.toString());
			// System.out.println("验证cron是否正确: " + CronExpressionEx.isValidExpression(exp.getCronExpression()));

			// System.out.println("getSecondsField: " + exp.getSecondsField());
			// System.out.println("getSecondsExp: " + exp.getSecondsExp());
			// System.out.println("getMinutesField: " + exp.getMinutesField());
			// System.out.println("getMinutesExp: " + exp.getMinutesExp());
			// System.out.println("getHoursField: " + exp.getHoursField());
			// System.out.println("getHoursExp: " + exp.getHoursExp());
			// System.out.println("getDaysOfMonthField: " + exp.getDaysOfMonthField());
			// System.out.println("getDaysOfMonthExp: " + exp.getDaysOfMonthExp());
			// System.out.println("getMonthsField: " + exp.getMonthsField());
			// System.out.println("getMonthsExp: " + exp.getMonthsExp());
			// System.out.println("getDaysOfWeekField: " + exp.getDaysOfWeekField());
			// System.out.println("getDaysOfWeekExp: " + exp.getDaysOfWeekExp());

			System.out.println(exp.getNextValidTimeAfter(new Date()));// 获得下一个触发时间
			System.out.println(exp.isSatisfiedBy(new Date()));// 获得给定时间是否符合cron

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
