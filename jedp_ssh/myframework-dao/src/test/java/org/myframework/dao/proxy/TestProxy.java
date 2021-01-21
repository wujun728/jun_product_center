package org.myframework.dao.proxy;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class TestProxy {
	@Before
	public void init() {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles",
				"true");
	}

	@Test
	public void testJdkProxy() {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles",
				"true");
		BookFacadeProxy proxy = new BookFacadeProxy();
		BookFacade bookProxy = (BookFacade) proxy.bind(new BookFacadeImpl());
		bookProxy.addBook();
	}

	@Test
	public void testJdkProxy1() {
		ProxyFactory proxyFactory = new ProxyFactory( new Class[] { BookFacade.class });
		proxyFactory.setTarget(new BookFacadeImpl());
		// factory.setTargetClass(BookFacade.class);
		proxyFactory.setOpaque(true);
		// 添加方法前置通知
		proxyFactory.addAdvice(new MethodBeforeAdvice() {
			@Override
			public void before(Method method, Object[] args, Object target)
					throws Throwable {
				System.out.println("1111111111在方法调用之前拦截");
			}
		});
		// 可以添加多个方法前置或者后置通知
		proxyFactory.addAdvice(new MethodBeforeAdvice() {

			@Override
			public void before(Method method, Object[] args, Object target)
					throws Throwable {
				System.out.println("22222222在方法调用之前拦截");
			}
		});
		// 可以添加多个方法前置或者后置通知
		proxyFactory.addAdvice(new AfterReturningAdvice() {

			@Override
			public void afterReturning(Object returnValue, Method method,
					Object[] args, Object target) throws Throwable {
				System.out.println("方法完成之后调用的方法11111");

			}
		});

		// 可以添加多个方法前置或者后置通知
		proxyFactory.addAdvice(new AfterReturningAdvice() {

			@Override
			public void afterReturning(Object returnValue, Method method,
					Object[] args, Object target) throws Throwable {
				System.out.println("方法完成之后调用的方法22222");

			}
		});

		proxyFactory.addAdvice(new AfterReturningAdvice() {

			@Override
			public void afterReturning(Object returnValue, Method method,
					Object[] args, Object target) throws Throwable {
				System.out.println("方法完成之后调用的方法22222");

			}
		});
		// 对于环绕通知只能添加一个,多添加也是没有用的，spring会选第一个advice，请看结果

		proxyFactory.addAdvice(new MethodInterceptor() {

			public Object invoke(MethodInvocation invocation) throws Throwable {
				System.out.println("1111111环绕通知");
				Object[] params = invocation.getArguments();
				Method method = invocation.getMethod();
				Object target = invocation.getThis();
				Object bytes = method.invoke(target, params);
			 
			 
				return null;
			}

		});

		// 对于环绕通知只能添加一个,多添加也是没有用的，spring会选第一个advice，请看结果
		proxyFactory.addAdvice(new MethodInterceptor() {

			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				System.out.println("22222环绕通知");
				Object[] params = invocation.getArguments();
				Method method = invocation.getMethod();
				Object target = invocation.getThis();
				Object bytes = method.invoke(target, params);
				return null;
			}
		});

		BookFacade bookProxy = (BookFacade) proxyFactory.getProxy();
		bookProxy.addBook();
	}

	@Test
	public void testJdkProxy2() {

	}

}
