package org.myframework.dao.exception;


/**
 * SqlConfigFile 加载失败异常
 * <ol>
 * <li>{@link  }</li>
 *
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年1月27日
 *
 */
public class SqlConfigFileLoadException extends  RuntimeException{
	/**
	 *
	 */
	private static final long serialVersionUID = -739622120750077157L;

	public SqlConfigFileLoadException(String msg) {
		super(msg);
	}



	public SqlConfigFileLoadException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
