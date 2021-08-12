package com.pearadmin.schedule.task;

import com.pearadmin.schedule.handler.base.BaseTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Describe: 示例任务
 * Author: 就免仪式
 * CreateTime: 2019/10/23
 * */
@Slf4j
@Component("exceptionTask")
public class ExceptionTaskImpl implements BaseTaskService {


    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;

    /**
     * 任务实现
     * */
    @Override
    public void run(String params) throws Exception{
        throw new Exception("发生异常");
    }
}
