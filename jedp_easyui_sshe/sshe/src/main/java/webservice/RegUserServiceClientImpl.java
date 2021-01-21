package webservice;

import java.net.MalformedURLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;
import org.springframework.stereotype.Service;

import sy.util.base.ConfigUtil;

import com.mypack.ClientHeaderHandler;
import com.sz.message.request.UserPresenceRequestMessage;
import com.sz.message.request.UserRegRequestMessage;
import com.sz.message.retun.ReturnMessage;
import com.sz.message.retun.UserPresenceReturnMessage;
import com.sz.message.retun.UserRegReturnMessage;
import com.sz.message.vo.JameMessage;

@Service
public class RegUserServiceClientImpl implements RegUserServiceClient {

	private static final Logger logger = Logger
			.getLogger(RegUserServiceClientImpl.class);

	@Override
	public boolean regUserToServer(UserRegRequestMessage userRegRequestMessage) {
		org.codehaus.xfire.service.Service serviceModel = new ObjectServiceFactory()
				.create(RegUser.class);
		XFire xfire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory = new XFireProxyFactory(xfire);

		String serviceUrl = ConfigUtil.get("sshe_chat_webroot")
				+ "service/RegUser";
		RegUser service = null;
		try {
			service = (RegUser) factory.create(serviceModel, serviceUrl);
			// 忽略http连接的超时时间,0为不设置超时时间，》=1为超时毫秒数
			Client client = Client.getInstance(service);
			client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT, "0");
			// 发送授权信息
			ClientHeaderHandler wsOut = new ClientHeaderHandler();
			client.addOutHandler(wsOut);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		// UserRegRequestMessage userRegRequestMessage = new
		// UserRegRequestMessage();
		//
		// userRegRequestMessage.setTime(System.currentTimeMillis() + "");
		// userRegRequestMessage.setUserId(2);
		// UserInfoData userInfoData = new UserInfoData();
		// userInfoData.setAccounts("test002");
		// userInfoData.setAddress("京122");
		// userInfoData.setLogonPass("test002");
		// userInfoData.setFaceID("3");
		// userInfoData.setRealName("2");
		// userInfoData.setPostalCard("42");
		// userInfoData.setUserName("周2");
		// userRegRequestMessage.setUserInfoData(userInfoData);

		UserRegReturnMessage userRegReturnMessage = service.RegUserToServer(
				userRegRequestMessage.getUserId(), userRegRequestMessage);

		logger.info("接收到的注册用户的返回消息:" + userRegRequestMessage);

		if (userRegReturnMessage != null) {
			if (userRegReturnMessage.getState().equalsIgnoreCase(
					ReturnMessage.STATE.STATE_SUCC.ordinal() + "")) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	@Override
	public boolean updUserPwdToServer(
			UserRegRequestMessage userRegRequestMessage) {
		org.codehaus.xfire.service.Service serviceModel = new ObjectServiceFactory()
				.create(RegUser.class);
		XFire xfire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory = new XFireProxyFactory(xfire);

		String serviceUrl = ConfigUtil.get("sshe_chat_webroot")
				+ "service/RegUser";
		RegUser service = null;
		try {
			service = (RegUser) factory.create(serviceModel, serviceUrl);
			// 忽略http连接的超时时间,0为不设置超时时间，》=1为超时毫秒数
			Client client = Client.getInstance(service);
			client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT, "0");
			// 发送授权信息
			ClientHeaderHandler wsOut = new ClientHeaderHandler();
			client.addOutHandler(wsOut);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		UserRegReturnMessage userRegReturnMessage = service.UpdUserPwdToServer(
				userRegRequestMessage.getUserId(), userRegRequestMessage);

		logger.info("接收到的注册用户的返回消息:" + userRegReturnMessage);

		if (userRegReturnMessage != null) {
			if (userRegReturnMessage.getState().equalsIgnoreCase(
					ReturnMessage.STATE.STATE_SUCC.ordinal() + "")) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	@Override
	public boolean loginUserToServer(UserRegRequestMessage userRegRequestMessage) {
		org.codehaus.xfire.service.Service serviceModel = new ObjectServiceFactory()
				.create(RegUser.class);
		XFire xfire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory = new XFireProxyFactory(xfire);

		String serviceUrl = ConfigUtil.get("sshe_chat_webroot")
				+ "service/RegUser";
		RegUser service = null;
		try {
			service = (RegUser) factory.create(serviceModel, serviceUrl);
			// 忽略http连接的超时时间,0为不设置超时时间，》=1为超时毫秒数
			Client client = Client.getInstance(service);
			client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT, "0");
			// 发送授权信息
			ClientHeaderHandler wsOut = new ClientHeaderHandler();
			client.addOutHandler(wsOut);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		UserRegReturnMessage userRegReturnMessage = service.loginUserToServer(
				userRegRequestMessage.getUserId(), userRegRequestMessage);

		logger.info("接收到聊天服务器用户登陆的返回消息:" + userRegRequestMessage);

		if (userRegReturnMessage != null) {
			if (userRegReturnMessage.getState().equalsIgnoreCase(
					ReturnMessage.STATE.STATE_SUCC.ordinal() + "")) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	@Override
	public List<JameMessage> getChatMessage(
			UserPresenceRequestMessage userPresenceRequestMessage) {
		org.codehaus.xfire.service.Service serviceModel = new ObjectServiceFactory()
				.create(RegUser.class);
		XFire xfire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory = new XFireProxyFactory(xfire);

		String serviceUrl = ConfigUtil.get("sshe_chat_webroot")
				+ "service/RegUser";
		RegUser service = null;
		try {
			service = (RegUser) factory.create(serviceModel, serviceUrl);
			// 忽略http连接的超时时间,0为不设置超时时间，》=1为超时毫秒数
			Client client = Client.getInstance(service);
			client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT, "0");
			// 发送授权信息
			ClientHeaderHandler wsOut = new ClientHeaderHandler();
			client.addOutHandler(wsOut);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		UserPresenceReturnMessage userPresenceReturnMessage = service
				.getUserPresenceMsgCount(
						userPresenceRequestMessage.getUserId(),
						userPresenceRequestMessage);

		logger.info("接收到的注册用户的返回消息:" + userPresenceReturnMessage);

		if (userPresenceReturnMessage != null) {
			if (userPresenceReturnMessage.getState().equalsIgnoreCase(
					ReturnMessage.STATE.STATE_SUCC.ordinal() + "")) {
				return userPresenceReturnMessage.getChatMessage();
			}
		} else {
			return null;
		}
		return null;
	}
}
