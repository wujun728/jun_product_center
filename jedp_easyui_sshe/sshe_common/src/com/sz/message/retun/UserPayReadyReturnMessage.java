package com.sz.message.retun;


public class UserPayReadyReturnMessage extends ReturnMessage {

	/**
	 * ���ID
	 */
	private int userId;

	public UserPayReadyReturnMessage() {
		super();
	}

	public UserPayReadyReturnMessage(String state, ERRCODE errCode,
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
	 * ���Ĭ�ϵķ�����Ϣ
	 * 
	 * @return
	 */
	public static UserPayReadyReturnMessage getDefaultUserRegReturnMessage() {
		return new UserPayReadyReturnMessage(STATE.STATE_FAIL.ordinal() + "", ERRCODE.ERRCODE_OP_ERR,
				"����ʧ��!", System.currentTimeMillis() + "");
	}

	/**
	 * 
	 * �����ɹ�
	 * 
	 * @return
	 */
	public static UserPayReadyReturnMessage getSuccUserRegReturnMessage() {
		return new UserPayReadyReturnMessage(STATE.STATE_SUCC.ordinal() + "", ERRCODE.ERRCODE_NONE,
				"�����ɹ�!", System.currentTimeMillis() + "");
	}

	/**
	 * �����ɹ�
	 * 
	 * @return
	 */
	public static UserPayReadyReturnMessage getSuccUserRegReturnMessage(
			String message) {
		return new UserPayReadyReturnMessage(STATE.STATE_SUCC.ordinal() + "", ERRCODE.ERRCODE_NONE,
				"�����ɹ�!" + message, System.currentTimeMillis() + "");
	}

	/**
	 * ��ҵ�����쳣
	 * 
	 * @return
	 */
	public static UserPayReadyReturnMessage getInvalidDataUserRegReturnMessage() {
		return new UserPayReadyReturnMessage(STATE.STATE_FAIL.ordinal() + "", ERRCODE.ERRCODE_NONE,
				"����ʧ��!", System.currentTimeMillis() + "");
	}

	/**
	 * ��ҵ�����쳣
	 * 
	 * @return
	 */
	public static UserPayReadyReturnMessage getInvalidDataUserRegReturnMessage(
			String message) {
		return new UserPayReadyReturnMessage(STATE.STATE_FAIL.ordinal() + "", ERRCODE.ERRCODE_DB_ERR,
				"����ʧ��!ԭ��:" + message, System.currentTimeMillis() + "");
	}

	/**
	 * �û��Ѵ����쳣
	 * 
	 * @return
	 */
	public static UserPayReadyReturnMessage getExistUserRegReturnMessage() {
		return new UserPayReadyReturnMessage(STATE.STATE_FAIL.ordinal() + "", ERRCODE.ERRCODE_DB_ERR_USER_EXIST,
				"�û��Ѿ�����!", System.currentTimeMillis() + "");
	}

	/**
	 * �����쳣
	 */
	public static UserPayReadyReturnMessage getOpErrorUserRegReturnMessage() {
		return new UserPayReadyReturnMessage(STATE.STATE_FAIL.ordinal() + "",ERRCODE.ERRCODE_DB_ERR,
				"��������쳣!", System.currentTimeMillis() + "");
	}
	
	/**
	 * �����ظ����
	 */
	public static UserPayReadyReturnMessage getOpErrorUserPayReadyReturnMessage(String message) {
		return new UserPayReadyReturnMessage(STATE.STATE_FAIL.ordinal() + "",ERRCODE.ERRCODE_DB_ERR_REPEAT,
				"��������쳣!"+message, System.currentTimeMillis() + "");
	}
	/**
	 * ��ҵ�����쳣
	 * 
	 * @return
	 */
	public static UserPayReadyReturnMessage getInvalidDataUserPayReadyReturnMessageType() {
		return new UserPayReadyReturnMessage(STATE.STATE_FAIL.ordinal() + "",
				ERRCODE.ERRCODE_NORMAL_ERR_TYPE, "����ʧ��!", System.currentTimeMillis()
						+ "");
	}
	
	@Override
	public String toString() {
		return "UserRegReturnMessage [userId=" + userId + ", getTime()="
				+ getTime() + ", getState()=" + getState() + ", getMessage()="
				+ getMessage() + ", getName()=" + getName() + "]";
	}

}
