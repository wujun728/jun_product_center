package org.myframework.dao.exception;


/**
 * XML文档解析异常
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
public class XMLDocException extends RuntimeException {
	  private Exception exception;

	  /**
	   * Creates a new XMLDocumentException wrapping another exception, and with a detail message.
	   * @param message the detail message.
	   * @param exception the wrapped exception.
	   */
	  public XMLDocException(String message, Exception exception) {
	    super(message);
	    this.exception = exception;
	  }

	  /**
	   * Creates a XMLDocumentException with the specified detail message.
	   * @param message the detail message.
	   */
	  public XMLDocException(String message) {
	    this(message, null);
	  }

	  /**
	   * Creates a new XMLDocumentException wrapping another exception, and with no detail message.
	   * @param exception the wrapped exception.
	   */
	  public XMLDocException(Exception exception) {
	    this(null, exception);
	  }

	  /**
	   * Gets the wrapped exception.
	   *
	   * @return the wrapped exception.
	   */
	  public Exception getException() {
	    return exception;
	  }

	  /**
	   * Retrieves (recursively) the root cause exception.
	   *
	   * @return the root cause exception.
	   */
	  public Exception getRootCause() {
	    if (exception instanceof XMLDocException) {
	      return ((XMLDocException) exception).getRootCause();
	    }
	    return exception == null ? this : exception;
	  }

	  public String toString() {
	    if (exception instanceof XMLDocException) {
	      return ((XMLDocException) exception).toString();
	    }
	    return exception == null ? super.toString() : exception.toString();
	  }
}
