package com.ruoyi.mq.exception;

/**
 * <p> 异步日志参数校验异常 </p>
 *
 * @Author wocurr.com
 */
public class AsyncLogParamCheckException extends RuntimeException{
    public AsyncLogParamCheckException(String message) {
        super(message);
    }
}
