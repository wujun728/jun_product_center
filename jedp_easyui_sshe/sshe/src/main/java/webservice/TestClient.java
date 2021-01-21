package webservice;

import java.net.MalformedURLException;

import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.security.wss4j.WSS4JOutHandler;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;
import org.codehaus.xfire.util.dom.DOMOutHandler;

import com.mypack.ClientHeaderHandler;

import webservice.core.PasswordHandler;

public class TestClient {
	public static void main(String[] args) {
		Service serviceModel = new ObjectServiceFactory()
				.create(HelloWorld.class);
		XFire xfire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory = new XFireProxyFactory(xfire);
		// String serviceUrl =
		// "http://localhost:8088/testWebServiceXfire/service/HelloWorld";
		String serviceUrl = "http://localhost:8081/sshe_chat/service/HelloWorld";
		// String serviceUrl = "http://localhost:8080/sshe/service/HelloWorld";
		HelloWorld service = null;
		try {
			service = (HelloWorld) factory.create(serviceModel, serviceUrl);

			// 忽略http连接的超时时间,0为不设置超时时间，》=1为超时毫秒数
			Client client = Client.getInstance(service);
			client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT, "0");
			// 发送授权信息

			// //WS-Security
			ClientHeaderHandler wsOut = new ClientHeaderHandler();
			// WSS4JOutHandler wsOut = new WSS4JOutHandler();
			// String actions = WSHandlerConstants.USERNAME_TOKEN;
			// wsOut.setProperty(WSHandlerConstants.ACTION, actions);// 动作
			// wsOut.setProperty(WSHandlerConstants.PASSWORD_TYPE,
			// WSConstants.PASSWORD_DIGEST);// 密码类型
			// wsOut.setProperty(WSHandlerConstants.USER, "server"); // 指定用户
			// wsOut.setProperty(WSHandlerConstants.PW_CALLBACK_CLASS,
			// PasswordHandler.class.getName());// 密码回调类
			// client.addOutHandler(new DOMOutHandler());
			client.addOutHandler(wsOut);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println(service.sayHelloWorld("Tom"));
	}

}