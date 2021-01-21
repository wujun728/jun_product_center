package webservice;

/**
 * HelloWorld的实现类.
 */
public class HelloWorldImpl implements HelloWorld {
	public String sayHelloWorld(String name) {
		String helloWorld = "hello,GG    " + name;
		return helloWorld;
	}
}