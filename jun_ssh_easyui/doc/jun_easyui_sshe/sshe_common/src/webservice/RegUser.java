package webservice;

import com.sz.message.request.UserPresenceRequestMessage;
import com.sz.message.request.UserRegRequestMessage;
import com.sz.message.retun.UserPresenceReturnMessage;
import com.sz.message.retun.UserRegReturnMessage;

public interface RegUser {

	/**
	 * 用户注册接口
	 * 
	 * @param UserId
	 * @param userRegRequestMessage
	 * @return
	 */
	public UserRegReturnMessage RegUserToServer(String UserId,
			UserRegRequestMessage userRegRequestMessage);

	/**
	 * 修改用户的密码
	 * 
	 * @param UserId
	 * @param userRegRequestMessage
	 * @return
	 */
	public UserRegReturnMessage UpdUserPwdToServer(String UserId,
			UserRegRequestMessage userRegRequestMessage);
	
	/**
	 * 接口登陆
	 * 
	 * @param userId
	 * @param userRegRequestMessage
	 * @return
	 */
	public UserRegReturnMessage loginUserToServer(String userId,
			UserRegRequestMessage userRegRequestMessage);
	
	/**
	 * 获得未读的消息数
	 */
	public UserPresenceReturnMessage getUserPresenceMsgCount(String userId,
			UserPresenceRequestMessage userPresenceRequestMessage);

}
