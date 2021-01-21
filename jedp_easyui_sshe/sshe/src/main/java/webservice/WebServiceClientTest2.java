package webservice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * HelloWorld的webservice的测试类.
 */
public class WebServiceClientTest2 {
	HelloWorld helloWorld = null;

	public static void main(String[] args) {
		WebServiceClientTest2 test = new WebServiceClientTest2();
		try {
			test.testClient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testClient() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("client.xml");
		helloWorld = (HelloWorld) ctx.getBean("testWebService");
		System.out.println(helloWorld.sayHelloWorld("阿蜜果"));
	}
}