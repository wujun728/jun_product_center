package com.jun.plugin.common.model;

/**
 * 用于返回json数据，JsonData.success(Object data);
 * 
 * @author klguang
 * 
 */
public class JsonData {
	private String message;
	private Object data;

	/** 类型 success 或者 error */
	private Type type;

	/** 错误码，正确是0 */
	private Long code;

	public JsonData() {
		super();
	}

	/**
	 * 失败
	 * 
	 * @param code
	 * @param message
	 * @return
	 */

	public static JsonData error(Long code, String message) {
		JsonData jsonData = new JsonData();
		jsonData.setType(Type.error);
		jsonData.setCode(code);
		jsonData.setMessage(message);
		return jsonData;
	}

	/**
	 * 成功
	 * 
	 * @param data
	 * @return
	 */
	public static JsonData success(Object data) {
		JsonData jsonData = new JsonData();
		jsonData.setCode(0L);
		jsonData.setType(Type.success);
		jsonData.setData(data);
		return jsonData;
	}

	public static JsonData success() {
		JsonData jsonData = new JsonData();
		jsonData.setCode(0L);
		jsonData.setType(Type.success);
		return jsonData;
	}

	public Long getCode() {
		return code;
	}

	public JsonData setCode(Long code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public JsonData setMessage(String message) {
		this.message = message;
		return this;
	}

	public Object getData() {
		return data;
	}

	public JsonData setData(Object data) {
		this.data = data;
		return this;
	}

	public Type getType() {
		return type;
	}

	public JsonData setType(Type type) {
		this.type = type;
		return this;
	}

	/**
	 * 类型
	 */
	public enum Type {

		/** 成功 */
		success,

		/** 错误 */
		error,
	}
}
