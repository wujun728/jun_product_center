package org.myframework.quartz.scheduler.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

/**
 * 
 * <ol>若一个方法一次执行不完下次轮转时则等待该方法执行完后才执行下一次操作
 * <li>{@link  }</li>
 * 
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年12月14日
 *
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Deprecated
public class QuartzJobFactoryDisallowConcurrentExecution extends QuartzJobFactory {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		super.execute(context);
	}
}