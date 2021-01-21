package webservice.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSConstants;
import org.apache.ws.security.WSPasswordCallback;
import org.apache.ws.security.WSSecurityException;

public class PasswordHandler implements CallbackHandler {

	@SuppressWarnings("unchecked")
	private Map passwords = new HashMap();

	@SuppressWarnings("unchecked")
	public PasswordHandler() {
		passwords.put("server", "serverpass");// 服务器端记录的用户名和密码，可以有多个
	}
	
	@Override
	public void handle(Callback[] callbacks) throws IOException, // 回调接口方法
			UnsupportedCallbackException {
		System.out.println("Handling  Password!");
		WSPasswordCallback callback = (WSPasswordCallback) callbacks[0];// 获取回调对象
		String id = callback.getIdentifier();// 获取用户名
		System.out.println("id:" + id + "  ,password:"
				+ (String) passwords.get(id));

		String validPw = (String) passwords.get(id);
		// ②-3:获取用户对应的正确密码
		// ②-4:如果是明文密码直接进行判断
		if (WSConstants.PASSWORD_TEXT.equals(callback.getType())) {
			String pw = callback.getPassword();
			if (pw == null || !pw.equalsIgnoreCase(validPw)) {
				try {
					throw new WSSecurityException("password  not  match");
				} catch (WSSecurityException e) {
					e.printStackTrace();
				}
			}
		} else {
			callback.setPassword((String) passwords.get(id));// 如果是密码摘要，向回调设置正确的密码（明文密码）
		}
	}

}
