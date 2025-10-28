package com.jun.plugin.common.exception;



/**
 * 错误码，参考新浪微博 open api
 * 
 * <pre>
http://open.weibo.com/wiki/Error_code
https://blog.csdn.net/huangwenyi1010/article/details/51581906
 * </pre>
 * 
 * <pre>
 错误码格式说明（示例：200001），1为系统级错误，2为业务逻辑错误
--------------------------------------------------------------------
服务级错误（1为系统级错误）	服务模块代码(即业务模块标识)		具体错误代码
        2                            02	                    001
--------------------------------------------------------------------
 * </pre>
 * 
 * @author klguang
 *
 */

public enum ErrorCodeEnum implements IErrorCode {
	
	
	UNKNOWN_ERROR			(500, -1L, "未知错误！"),
	
	UNAUTHENTICATED			(403, 201001L, "登录认证失效，请重新登录!"),
	UNAUTHORIZED			(401, 201002L, "权限不足！"),
	
	ENTITY_HAS_RELATED_DATA	(409, 200005L, "已经有关联数据，无法删除！"),
	DATA_EXISTED			(409, 200004L,"重复的数据！"),

	DATA_NOTEXIST			(404, 200003L,"数据不存在！"),	
	
	DATA_INTEGRITY_ERROR	(400, 200002L,"数据不合法或重复的字段值！"),
	BADPARAM				(400, 200001L,"错误的请求参数错误！");
	
	
	Long code;
	int httpStatus;
	String messageFormat;

	private ErrorCodeEnum(int httpStatus, Long code, String messageFormat) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.messageFormat = messageFormat;
	}

	@Override
	public long getCode() {
		// TODO Auto-generated method stub
		return this.code;
	}

	@Override
	public String getMessageFormat() {
		// TODO Auto-generated method stub
		return this.messageFormat;
	}

	@Override
	public int getHttpStatus() {
		// TODO Auto-generated method stub
		return this.httpStatus;
	}
}
