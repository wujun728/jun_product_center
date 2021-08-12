package com.pearadmin.schedule.task;

import com.pearadmin.schedule.handler.base.BaseTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component("commonTask")
public class CommonTaskImpl implements BaseTaskService {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;

    /**
     * 任务实现
     * */
    @Override
    public void run(String params) {
        log.info("Params === >> " + params);
        log.info("当前时间::::" + FORMAT.format(new Date()));
        System.out.println("执行成功");
    }
}
