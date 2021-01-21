package org.myframework.dao.proxy.springaop;

import org.junit.Test;
import org.myframework.dao.proxy.Develpoer;
import org.myframework.dao.proxy.IDev;
import org.myframework.dao.proxy.ITask;
import org.myframework.dao.proxy.ITest;
import org.myframework.dao.proxy.MockTask;
import org.myframework.dao.proxy.PerformanceMethodInterceptor;
import org.myframework.dao.proxy.TestorFetureIntroduceInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

/**
 * 
 * <ol>SPRING AOP的源码解读
 * <li>{@link  }</li>
 * 
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2016年6月3日
 *
 */
public class SpringAopTest {
	@Test
	public void springJdkAopTest1() {
		MockTask mockTask = new MockTask();
		ProxyFactory weaver = new ProxyFactory(mockTask);
// 		weaver.setInterfaces(new Class[] { ITask.class });
		//
		NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
		advisor.addMethodName("execute"); //pointcut
		advisor.setAdvice(new PerformanceMethodInterceptor());//advice
		
		//
		weaver.addAdvisor(advisor);
		
		//
		ITask proxyTask = (ITask)weaver.getProxy();
		proxyTask.execute();
		System.out.println(proxyTask.getClass());
	}

	@Test
	public void springCglibAopTest2() {
		ProxyFactory weaver = new ProxyFactory(new Develpoer());
//		weaver.setProxyTargetClass(true);
		weaver.setOptimize(true);

		//
		NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
		advisor.addMethodName("execute"); //pointcut
		advisor.setAdvice(new PerformanceMethodInterceptor());//advice
		
		//
		weaver.addAdvisor(advisor);
		
		//
		MockTask proxyTask = (MockTask)weaver.getProxy();
		proxyTask.execute();
		System.out.println(proxyTask.getClass());
	}
	
	@Test
	public void springIntroductionAdvisorAopTest3() {
		ProxyFactory weaver = new ProxyFactory( new Develpoer() );
//		weaver.setProxyTargetClass(true);
		weaver.setOptimize(true);
		weaver.setInterfaces(new Class[] { ITest.class   });
		//
		TestorFetureIntroduceInterceptor advice = new TestorFetureIntroduceInterceptor();
		//
		weaver.addAdvice(advice); 
		
		//
		Object proxyObj =  weaver.getProxy();
		((ITest)proxyObj).test();
		((IDev)proxyObj).dev();
//		System.out.println(proxyTask.getClass());
	}

}
