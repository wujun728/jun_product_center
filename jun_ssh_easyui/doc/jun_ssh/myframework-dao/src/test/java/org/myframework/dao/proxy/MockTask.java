package org.myframework.dao.proxy;

public class MockTask implements ITask {

	public MockTask() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		System.out.println("do something");
	}

}
