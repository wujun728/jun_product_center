package com.platform.log4j;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

/**
 * 自定义日志文件追加类，使日志分级打印配置只判断是否相等，而不是使用上限<br>
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
public class GradeLogDailyRollingFileAppender extends DailyRollingFileAppender {
    @Override
    public boolean isAsSevereAsThreshold(Priority priority) {
        //只判断是否相等，而不判断优先级
        return this.getThreshold().equals(priority);
    }
}
