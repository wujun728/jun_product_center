package com.sz.message.retun;

public class RechargeReturnMessage extends ReturnMessage {
	private int userId;

	/**
	 * 获得默认的返回消息
	 * 
	 * @return
	 */
	public static RechargeReturnMessage getDefaultUserRegReturnMessage() {
		return new RechargeReturnMessage(STATE.STATE_FAIL.ordinal() + "",
				ERRCODE.ERRCODE_OP_ERR, "操作失败!", System.currentTimeMillis()
						+ "");
	}
	
	/**
	 * 操作成功
	 * 
	 * @return
	 */
	public static RechargeReturnMessage getSuccUserRegReturnMessage() {
		return new RechargeReturnMessage(STATE.STATE_FAIL.ordinal() + "",
				ERRCODE.ERRCODE_NONE, "操作成功!", System.currentTimeMillis() + "");
	}

	/**
	 * 操作成功
	 * 
	 * @return
	 */
	public static RechargeReturnMessage getSuccUserRegReturnMessage(
			String message) {
		return new RechargeReturnMessage(STATE.STATE_FAIL.ordinal() + "",
				ERRCODE.ERRCODE_NONE, "操作成功!" + message,
				System.currentTimeMillis() + "");
	}

	/**
	 * 玩家的数据异常
	 * 
	 * @return
	 */
	public static RechargeReturnMessage getInvalidDataUserRegReturnMessage() {
		return new RechargeReturnMessage(STATE.STATE_FAIL.ordinal() + "",
				ERRCODE.ERRCODE_DB_ERR, "操作失败!", System.currentTimeMillis()
						+ "");
	}

	/**
	 * 玩家的数据异常
	 * 
	 * @return
	 */
	public static RechargeReturnMessage getInvalidDataUserRegReturnMessage(
			String message) {
		return new RechargeReturnMessage(STATE.STATE_FAIL.ordinal() + "",
				ERRCODE.ERRCODE_DB_ERR, "操作失败!原因:" + message,
				System.currentTimeMillis() + "");
	}

	public RechargeReturnMessage() {
		super();
	}

	public RechargeReturnMessage(String state, ERRCODE errCode, String message,
			String time) {
		super(state, errCode, message, time);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
