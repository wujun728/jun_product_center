package org.myframework.dao.exception;



/**
 * SQL 配置 sqlKey重名异常
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
public class SqlConfigDuplicateException extends  RuntimeException{
	/**
	 *
	 */
	private static final long serialVersionUID = -739622120750077157L;

	public SqlConfigDuplicateException(String msg) {
		super(msg);
	}



}
