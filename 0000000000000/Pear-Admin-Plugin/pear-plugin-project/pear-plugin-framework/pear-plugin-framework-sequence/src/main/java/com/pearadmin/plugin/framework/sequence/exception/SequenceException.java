package com.pearadmin.plugin.framework.sequence.exception;

/**
 * Sequence Exception 异常类 -- [就眠仪式]
 * */
public class SequenceException extends Exception{

    /**
     * 构 造 方 法
     * */
    public SequenceException(){
        super();
    }

    /**
     * 构 造 方 法
     * */
    public SequenceException(String message){
        super(message);
    }

    /**
     * 构 造 方 法
     * */
    public SequenceException(String message, Throwable cause){
        super(message,cause);
    }

    /**
     * 构 造 方 法
     * */
    public SequenceException(Throwable cause){
        super(cause);
    }

}
