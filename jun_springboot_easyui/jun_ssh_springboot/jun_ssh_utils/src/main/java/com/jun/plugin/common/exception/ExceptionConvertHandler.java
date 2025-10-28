package com.jun.plugin.common.exception;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 多个ExceptionConverter，同类型Exception，跟顺序有关，先匹配先返回
 * 
 * @author klguang[klguang@foxmail.com]
 * @date 2018年11月11日
 */
public class ExceptionConvertHandler {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionConvertHandler.class);
	private List<ExceptionConverter> exceptionConverters;

	public ExceptionConvertHandler(List<ExceptionConverter> exceptionConverters) {
		super();
		this.exceptionConverters = exceptionConverters;
	}

	public AppException executeExceptionConvert(Exception ex) {

		if (ex instanceof AppException) {
			return (AppException) ex;
		}

		for (ExceptionConverter exceptionConverter : exceptionConverters) {
			AppException appException = exceptionConverter.convertException(ex);
			if (null != appException) {
				logger.info("异常转换器: {}",exceptionConverter.getClass().getName());
				logger.info("异常转换: {}->{}", ex.getClass().getName(), AppException.class.getName());
				return appException;
			}
		}
		return null;
	}
}
