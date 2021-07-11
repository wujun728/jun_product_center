package me.wuwenbin.noteblogv5.exception;

/**
 * created by Wuwenbin on 2019-08-01 at 19:23
 *
 * @author wuwenbin
 */
public class AppSetException extends RuntimeException {
    public AppSetException(String message) {
        super(message);
    }

    public AppSetException(String message, Throwable cause) {
        super(message, cause);
    }
}
