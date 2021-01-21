package com.sz.message.retun;

import java.util.List;

import com.sz.message.vo.JameMessage;

/**
 * 玩家获得的消息数
 * 
 * 
 * <b>项目名称: CommonVO </b><br/>
 * <b>类描述: </b><br/>
 * <b>创 建 人: </b> zhouxj <br/>
 * <b>创建时间: </b> 2013-6-21 下午3:13:57 <br/>
 * <b>修 改 人: </b><br/>
 * <b>修改时间: </b><br/>
 * <b>修改备注: </b><br/>
 * <b>JDK 版本: </b> JDK1.6</br/>
 * 
 * @version 1.0.0<br/>
 */
public class UserPresenceReturnMessage extends ReturnMessage {

	/**
	 * 玩家ID
	 */
	private int userId;
	/**
	 * 未读的消息数
	 */
	private int unreadMsgCount;

	private List<JameMessage> chatMessage;

	public UserPresenceReturnMessage() {
		super();
		this.unreadMsgCount = 0;
	}

	public UserPresenceReturnMessage(String state, ERRCODE errCode,
			String message, String time) {
		super(state, errCode, message, time);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * 获得默认的返回消息
	 * 
	 * @return
	 */
	public static UserPresenceReturnMessage getDefaultUserRegReturnMessage() {
		return new UserPresenceReturnMessage(STATE.STATE_FAIL.ordinal() + "",
				ERRCODE.ERRCODE_OP_ERR, "操作失败!", System.currentTimeMillis()
						+ "");
	}

	/**
	 * 操作成功
	 * 
	 * @return
	 */
	public static UserPresenceReturnMessage getSuccUserRegReturnMessage() {
		return new UserPresenceReturnMessage(STATE.STATE_SUCC.ordinal() + "",
				ERRCODE.ERRCODE_NONE, "操作成功!", System.currentTimeMillis() + "");
	}

	/**
	 * 操作成功
	 * 
	 * @return
	 */
	public static UserPresenceReturnMessage getSuccUserRegReturnMessage(
			String message) {
		return new UserPresenceReturnMessage(STATE.STATE_SUCC.ordinal() + "",
				ERRCODE.ERRCODE_NONE, "操作成功!" + message,
				System.currentTimeMillis() + "");
	}

	/**
	 * 玩家的数据异常
	 * 
	 * @return
	 */
	public static UserPresenceReturnMessage getInvalidDataUserRegReturnMessage() {
		return new UserPresenceReturnMessage(STATE.STATE_FAIL.ordinal() + "",
				ERRCODE.ERRCODE_OP_ERR, "操作失败!", System.currentTimeMillis()
						+ "");
	}

	/**
	 * 玩家的数据异常
	 * 
	 * @return
	 */
	public static UserPresenceReturnMessage getInvalidDataUserRegReturnMessage(
			String message) {
		return new UserPresenceReturnMessage(STATE.STATE_FAIL.ordinal() + "",
				ERRCODE.ERRCODE_OP_ERR, "操作失败!原因:" + message,
				System.currentTimeMillis() + "");
	}

	/**
	 * 用户已存在异常
	 * 
	 * @return
	 */
	public static UserPresenceReturnMessage getExistUserRegReturnMessage() {
		return new UserPresenceReturnMessage(STATE.STATE_FAIL.ordinal() + "",
				ERRCODE.ERRCODE_DB_ERR_USER_EXIST, "用户已经存在!",
				System.currentTimeMillis() + "");
	}

	/**
	 * 操作异常
	 */
	public static UserPresenceReturnMessage getOpErrorUserRegReturnMessage() {
		return new UserPresenceReturnMessage(STATE.STATE_FAIL.ordinal() + "",
				ERRCODE.ERRCODE_DB_ERR, "保存数据异常!", System.currentTimeMillis()
						+ "");
	}

	/**
	 * 插入重复数据
	 */
	public static UserPresenceReturnMessage getOpErrorUserRegReturnMessage(
			String message) {
		return new UserPresenceReturnMessage(STATE.STATE_FAIL.ordinal() + "",
				ERRCODE.ERRCODE_DB_ERR_REPEAT, "保存数据异常!" + message,
				System.currentTimeMillis() + "");
	}

	/**
	 * 玩家的数据异常
	 * 
	 * @return
	 */
	public static UserPresenceReturnMessage getInvalidDataUserRegReturnMessageType() {
		return new UserPresenceReturnMessage(STATE.STATE_FAIL.ordinal() + "",
				ERRCODE.ERRCODE_NORMAL_ERR_TYPE, "操作失败!",
				System.currentTimeMillis() + "");
	}

	@Override
	public String toString() {
		return "UserRegReturnMessage [userId=" + userId + ", getTime()="
				+ getTime() + ", getState()=" + getState() + ", getMessage()="
				+ getMessage() + ", getName()=" + getName() + "]";
	}

	public void setName(String name) {

	}

	public String getName() {
		return "返回消息-注册用户";
	}

	/**
	 * @return the unreadMsgCount
	 */
	public int getUnreadMsgCount() {
		return unreadMsgCount;
	}

	/**
	 * @param unreadMsgCount
	 *            the unreadMsgCount to set
	 */
	public void setUnreadMsgCount(int unreadMsgCount) {
		this.unreadMsgCount = unreadMsgCount;
	}

	/**
	 * @param chatMessage
	 *            the chatMessage to set
	 */
	public void setChatMessage(List<JameMessage> chatMessage) {
		this.chatMessage = chatMessage;
	}

	/**
	 * @return the chatMessage
	 */
	public List<JameMessage> getChatMessage() {
		return chatMessage;
	}
}
