package com.sz.message.retun;



/**
 * 玩家注册消息的返回
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
public class UserRegReturnMessage extends ReturnMessage {

	/**
	 * 玩家ID
	 */
	private int userId;

	public UserRegReturnMessage() {
		super();
	}

	public UserRegReturnMessage(String state, ERRCODE errCode, String message,
			String time) {
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
	public static UserRegReturnMessage getDefaultUserRegReturnMessage() {
		return new UserRegReturnMessage(STATE.STATE_FAIL.ordinal() + "",
				ERRCODE.ERRCODE_OP_ERR, "操作失败!", System.currentTimeMillis()
						+ "");
	}

	/**
	 * 操作成功
	 * 
	 * @return
	 */
	public static UserRegReturnMessage getSuccUserRegReturnMessage() {
		return new UserRegReturnMessage(STATE.STATE_SUCC.ordinal() + "",
				ERRCODE.ERRCODE_NONE, "操作成功!", System.currentTimeMillis() + "");
	}

	/**
	 * 操作成功
	 * 
	 * @return
	 */
	public static UserRegReturnMessage getSuccUserRegReturnMessage(
			String message) {
		return new UserRegReturnMessage(STATE.STATE_SUCC.ordinal() + "",
				ERRCODE.ERRCODE_NONE, "操作成功!" + message,
				System.currentTimeMillis() + "");
	}

	/**
	 * 玩家的数据异常
	 * 
	 * @return
	 */
	public static UserRegReturnMessage getInvalidDataUserRegReturnMessage() {
		return new UserRegReturnMessage(STATE.STATE_FAIL.ordinal() + "",
				ERRCODE.ERRCODE_OP_ERR, "操作失败!", System.currentTimeMillis()
						+ "");
	}

	/**
	 * 玩家的数据异常
	 * 
	 * @return
	 */
	public static UserRegReturnMessage getInvalidDataUserRegReturnMessage(
			String message) {
		return new UserRegReturnMessage(STATE.STATE_FAIL.ordinal() + "",	ERRCODE.ERRCODE_OP_ERR, 
				"操作失败!原因:" + message, System.currentTimeMillis() + "");
	}
	
	/**
	 * 用户已存在异常
	 * 
	 * @return
	 */
	public static UserRegReturnMessage getExistUserRegReturnMessage() {
		return new UserRegReturnMessage(STATE.STATE_FAIL.ordinal() + "",  ERRCODE.ERRCODE_DB_ERR_USER_EXIST,
				"用户已经存在!", System.currentTimeMillis() + "");
	}

	/**
	 * 操作异常
	 */
	public static UserRegReturnMessage getOpErrorUserRegReturnMessage() {
		return new UserRegReturnMessage(STATE.STATE_FAIL.ordinal() + "",ERRCODE.ERRCODE_DB_ERR,
				"保存数据异常!", System.currentTimeMillis() + "");
	}
	/**
	 * 插入重复数据
	 */
	public static UserRegReturnMessage getOpErrorUserRegReturnMessage(String message) {
		return new UserRegReturnMessage(STATE.STATE_FAIL.ordinal() + "",ERRCODE.ERRCODE_DB_ERR_REPEAT,
				"保存数据异常!"+message, System.currentTimeMillis() + "");
	}
	
	/**
	 * 玩家的数据异常
	 * 
	 * @return
	 */
	public static UserRegReturnMessage getInvalidDataUserRegReturnMessageType() {
		return new UserRegReturnMessage(STATE.STATE_FAIL.ordinal() + "",
				ERRCODE.ERRCODE_NORMAL_ERR_TYPE, "操作失败!", System.currentTimeMillis()
						+ "");
	}

	@Override
	public String toString() {
		return "UserRegReturnMessage [userId=" + userId + ", getTime()="
				+ getTime() + ", getState()=" + getState() + ", getMessage()="
				+ getMessage() + ", getName()=" + getName() + "]";
	}
	public void setName(String name){
		
	}
	public String getName() {
		return "返回消息-注册用户";
	}
}
