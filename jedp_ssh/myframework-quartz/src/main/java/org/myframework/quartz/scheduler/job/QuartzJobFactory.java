package org.myframework.quartz.scheduler.job;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.beanutils.MethodUtils;
import org.myframework.commons.util.ClassUtils;
import org.myframework.commons.util.ExceptionUtils;
import org.myframework.commons.util.StringUtils;
import org.myframework.quartz.scheduler.entities.JobInfo;
import org.myframework.support.spring.SpringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <ol>
 * 计划任务执行 无状态
 * <li>{@link }</li>
 * 
 * </ol>
 * 
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年12月14日
 *
 */
@Deprecated
public class QuartzJobFactory implements Job   {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		Map<String, Object> param = context.getMergedJobDataMap() ;
		// JOB配置信息
		JobInfo jobInfo = (JobInfo) param.get(JobInfo.class.getSimpleName());
		try {
			execute(jobInfo,context);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new JobExecutionException(ExceptionUtils.getStackTrace(e));
		}
	}

	/**
	 * 根据JOB配置信息
	 * @param jobInfo
	 * @param context
	 * @throws Exception
	 */
	protected void execute(JobInfo jobInfo, JobExecutionContext context)
			throws Exception {
		//
		String clzName = jobInfo.getBeanClass();
		String methodName = jobInfo.getMethodName();
		String springBeanId = jobInfo.getSpringBeanId();
		//判断是否存在方法，不存在直接返回
		Class objClz =  StringUtils.isNullOrBlank(springBeanId)? Class.forName(clzName):SpringUtils.getBean(springBeanId).getClass();
		if (!ClassUtils.hasAtLeastOneMethodWithName(objClz, methodName)) {
			logger.error("jobInfo 对应的类方法不存在 :" +jobInfo);
			return;
		}
		/*
		*根据jobInfo中提供的相关信息执行对应的类方法
		*/
		Method method = null;
		Object param = null;
		Class[] argClzs = new Class[] { null, Map.class,
				JobExecutionContext.class, JobInfo.class };
		
		
		for (Class argClz : argClzs) {
			// 1.查找方法
			if (argClz == null) {
				method = ClassUtils.getMethodIfAvailable(objClz, methodName);
			} else {
				method = ClassUtils.getMethodIfAvailable(objClz, methodName,
						argClz);
			}

			// 2.获取对应的执行参数
			if (Map.class.equals(argClz)) {
				param = context.getMergedJobDataMap();
			} else if (JobExecutionContext.class.equals(argClz)) {
				param = context;
			} else if (JobInfo.class.equals(argClz)) {
				param = jobInfo;
			} else {
				param = null;
			}
			
			if (method != null) {
				logger.info("execute method :" + method);
				break ;
			}
		}
		
		// 3.方法存在则执行
		if (method != null) {
			logger.debug("execute method :" + method);
			logger.debug("execute method param:" + param);
			Object object = StringUtils.isNullOrBlank(springBeanId)
					? objClz.newInstance() : SpringUtils.getBean(springBeanId);
					MethodUtils.invokeMethod(object, methodName, param);
		} 
		
	}

}