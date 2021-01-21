package org.myframework.dao.proxy;

import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class TestorFetureIntroduceInterceptor
		extends DelegatingIntroductionInterceptor implements ITest {

	public TestorFetureIntroduceInterceptor() {
		// TODO Auto-generated constructor stub
	}

	public TestorFetureIntroduceInterceptor(Object delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("TestorFetureIntroduceInterceptor");
	}

}
