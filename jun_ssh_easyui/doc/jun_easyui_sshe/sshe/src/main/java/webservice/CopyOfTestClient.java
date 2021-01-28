package webservice;

import java.net.MalformedURLException;

import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;

import webservice.RegUser;

import com.mypack.ClientHeaderHandler;
import com.sz.message.request.UserRegRequestMessage;
import com.sz.message.retun.UserRegReturnMessage;
import com.sz.message.vo.UserInfoData;

public class CopyOfTestClient {
	public static void main(String[] args) {
		Service serviceModel = new ObjectServiceFactory().create(RegUser.class);
		XFire xfire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory = new XFireProxyFactory(xfire);
		// String serviceUrl =
		// "http://localhost:8088/testWebServiceXfire/service/HelloWorld";
		String serviceUrl = "http://localhost:8081/sshe_chat/service/RegUser";
		// String serviceUrl = "http://localhost:8080/sshe/service/HelloWorld";
		RegUser service = null;
		try {
			service = (RegUser) factory.create(serviceModel, serviceUrl);

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
		UserRegRequestMessage userRegRequestMessage = new UserRegRequestMessage();

		userRegRequestMessage.setTime(System.currentTimeMillis() + "");
		userRegRequestMessage.setUserId("2");
		UserInfoData userInfoData = new UserInfoData();
		userInfoData.setAccounts("chat108");
		userInfoData.setAddress("京122");
		userInfoData.setLogonPass("chat108");
		userInfoData.setFaceID("3");
		userInfoData.setRealName("2");
		userInfoData.setPostalCard("42");
		userInfoData.setUserName("周2");
		userRegRequestMessage.setUserInfoData(userInfoData);
		userRegRequestMessage.setName("注册");
		
		UserRegReturnMessage userRegReturnMessage = service.RegUserToServer(
				userRegRequestMessage.getUserId(), userRegRequestMessage);
	}

}