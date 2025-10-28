package com.jun.plugin.common.exception;

import com.jun.plugin.common.exception.IErrorCode;

/**
 * 错误码，参考新浪微博 open api
 * 
 * <pre>
https://blog.csdn.net/huangwenyi1010/article/details/51581906
 * </pre>
 * 
 * <pre>
 错误码格式说明（示例：202001），1为系统级错误，2为业务逻辑错误
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
	
	DEMO_NOT_UPDATE	(202000L,"演示模式，无法更新数据!"),
	UNKNOWN_ERROR(-1L,"未知错误！"),
	ENTITY_HAS_RELATED_DATA(202001L, "已经有关联数据，无法删除！"),
	DATA_EXISTED(202002L,"重复的数据！"),
	DATA_INTEGRITY_ERROR(202001L,"数据不合法或重复的字段值！"),
	DATA_NOTEXIST(202003L,"数据不存在！"),
	FILE_PATH_NOTEXIST(202004L,"文件路径不存在！"),
	
	
	FILE_DOWNLOAD_FAILD				(204003L,"下载失败"),
	FILE_UPLOAD_FAILD				(204002L,"上传失败"),
	NEED_RE_UPLOAD					(204004L,"请重新上传模板文件"),
	INVALID_DIR						(204005L,"不合法的目录路径，两头应都有'/'"),
	
	DB_META_CRAWLER_WORONG			(205001L,"获取数据库元数据出错！"),
	BADPARAM(203001L,"错误的请求参数错误！");
	
	
	
	
	
	Long code;
	String messageFormat;

	private ErrorCodeEnum(Long code, String messageFormat) {
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
		return 0;
	}

}
