package org.myframework.support.taskclient;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.beanutils.MethodUtils;
import org.myframework.commons.util.ClassUtils;
import org.myframework.commons.util.ExceptionUtils;
import org.myframework.commons.util.StringUtils;
import org.myframework.support.spring.SpringUtils;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class TaskRunShell implements Runnable {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	final TaskInfo jobInfo;

	public TaskInfo getJobInfo() {
		return jobInfo;
	}

	public TaskRunShell(TaskInfo jobInfo) {
		super();
		this.jobInfo = jobInfo;
	}

	@Override
	public void run() {
		try {
			execute(jobInfo);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	
	protected void execute(TaskInfo jobInfo) throws Exception {
		//
		String clzName = jobInfo.getBeanClass();
		String methodName = jobInfo.getMethodName();
		String springBeanId = jobInfo.getSpringBeanId();
		// 判断是否存在方法，不存在直接返回
		Class objClz = StringUtils.isNullOrBlank(springBeanId)
				? Class.forName(clzName)
				: SpringUtils.getBean(springBeanId).getClass();
		if (!ClassUtils.hasAtLeastOneMethodWithName(objClz, methodName)) {
			logger.error("jobInfo 对应的类方法不存在 :" + jobInfo);
			return;
		}
		/*
		 * 根据jobInfo中提供的相关信息执行对应的类方法
		 */
		Method method = null;
		Object param = null;
		Class[] argClzs = new Class[] { null, Map.class,
				JobExecutionContext.class, TaskInfo.class };

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
				param = JSONObject.parseObject(jobInfo.getJobData());
			} else if (TaskInfo.class.equals(argClz)) {
				param = jobInfo;
			} else {
				param = null;
			}

			if (method != null) {
				logger.info("execute method :" + method);
				break;
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
