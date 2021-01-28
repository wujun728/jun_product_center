package com.sz.message.retun;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.sz.message.IReturnMessage;

public class ReturnMessage implements IReturnMessage {
	/**
	 * 状态
	 */
	public enum STATE {
		/**
		 * 失败
		 */
		STATE_FAIL,
		/**
		 * 成功
		 */
		STATE_SUCC,
	}

	/**
	 * 错误编码
	 */
	public enum ERRCODE {
		//无效的编码
		ERRCODE_NONE(0),
		// 参数错误
		ERRCODE_PARAM_ERR(100),
		// 一般错误
		ERRCODE_NORMAL_ERR(200),
		//类型强制转换异常
		ERRCODE_NORMAL_ERR_TYPE(201),
		// 数据库操作错误
		ERRCODE_DB_ERR(300),
		ERRCODE_DB_ERR_REPEAT(301),
		//用户已经存在
		ERRCODE_DB_ERR_USER_EXIST(301),
		// 逻辑错误
		ERRCODE_LOGIC_ERR(400),
		// 未知错误
		ERRCODE_UNKNOWN_ERR(500),
		//操作失败
		ERRCODE_OP_ERR(600);
		
		private int value;

		ERRCODE(int v) {
			this.value = v;
		}

		public int getErrCode() {
			return this.value;
		}
	}
	
	/**
	 * 0 失败， 1 成功 ，
	 */
	private String state;
	// 错误编码
	private ERRCODE errCode;
	private String message;
	private String time;

	public String getTime() {
		return "" + this.time;
	}
	
	public ReturnMessage(String state, ERRCODE errCode , String message, String time) {
		super();
		this.state = state;
		this.errCode = errCode;
		this.message = message;
		this.time = time;
	}

	public ReturnMessage() {
		super();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTime(String time) {
		this.time = time;
	}

//	@JsonIgnore
//	public boolean isValid() {
//		return true;
//	}

	@JsonIgnore
	public String getName() {
		return "返回消息-基础结构";
	}
	
	public ERRCODE getErrCode() {
		return errCode;
	}
	
	public void setErrCode(ERRCODE errCode) {
		this.errCode = errCode;
	}
	
}
