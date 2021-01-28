package org.myframework.web.bind;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.myframework.commons.util.ExceptionUtils;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * REST服务全局异常处理:当rest请求出现异常时，以JSON格式输出异常的相关信息。
 * <ol>
 * <li>{@link ControllerAdvice }</li>
 *
 * </ol>
 *
 * @author Wujun
 * @since 1.0
 */
@Order(100)
@ControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(GlobalExceptionHandler.class);

	@ExceptionHandler
	public @ResponseBody ServiceResult handleSQLException(SQLException ex) {
		return exceptionToServiceResult(ex);
	}

 	@ExceptionHandler
	public @ResponseBody ServiceResult handleException(Exception ex) {
		return exceptionToServiceResult(ex);
	}

	@ExceptionHandler
	public @ResponseBody ServiceResult handleRuntimeException(
			RuntimeException ex) {
		return exceptionToServiceResult(ex);
	}

	protected ServiceResult exceptionToServiceResult(Exception ex){
		logger.error(ExceptionUtils.getStackTrace(ex));
		ServiceResult serviceResult = new ServiceResult();
		serviceResult.setSuccess(false);
		serviceResult.setErrorType(ex.getClass().getSimpleName());
		serviceResult.setErrorMessage(ex.getMessage());
		return serviceResult;
	}

}
