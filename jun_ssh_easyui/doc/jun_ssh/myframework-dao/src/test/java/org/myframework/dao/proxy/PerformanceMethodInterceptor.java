package org.myframework.dao.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;


public class PerformanceMethodInterceptor implements MethodInterceptor {

	public PerformanceMethodInterceptor() {
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		try {
			stopWatch.start();
			return invocation.proceed();
		} finally {
			stopWatch.stop();
			System.out.println(stopWatch);
		}
	}

	 

}
