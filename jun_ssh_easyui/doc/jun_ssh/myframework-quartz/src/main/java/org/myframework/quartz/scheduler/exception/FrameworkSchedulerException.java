package org.myframework.quartz.scheduler.exception;  

import org.myframework.commons.GeneralRuntimeException;

/**
 * 定时任务异常
 */
public class FrameworkSchedulerException extends GeneralRuntimeException{
	public FrameworkSchedulerException(String message) {
		super(message);
	}

	public FrameworkSchedulerException(String message, Throwable cause) {
		super(message, cause);
	}

	public FrameworkSchedulerException(Throwable cause) {
		super(cause);
	}
}
  
