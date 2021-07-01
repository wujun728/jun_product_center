package com.du.lin.log;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LogManager {
	  //日志记录操作延时
    private final int OPERATE_DELAY_TIME = 10;

    private static LogManager logManager = new LogManager();
    
    public static LogManager getInstance(){
    	return logManager;
    }
    
    //异步操作记录日志的线程池
    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);

    public void saveLog(TimerTask task){
    	executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }
}
