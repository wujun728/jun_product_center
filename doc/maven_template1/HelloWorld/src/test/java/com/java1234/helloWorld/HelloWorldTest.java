package com.java1234.helloWorld;
import org.junit.Test;

public class HelloWorldTest{

	@Test
	public void testSayHello(){
		HelloWorld helloWorld=new HelloWorld();
		String result=helloWorld.sayHello();
		System.out.println(result);
	}
}