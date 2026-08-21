package com.ruoyi.schedule.task;

import com.ruoyi.schedule.service.IScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 日程提醒
 * @Author wocurr.com
 */
@Slf4j
@Component("scheduleRemindTask")
public class ScheduleRemindTask {
    @Autowired
    private IScheduleService scheduleService;

    /**
     * 日程到时提醒
     * 每分钟处理一次
     */
    public void remind() {
        try {
            log.info("## 定时任务-日程提醒：开始。。。");
            scheduleService.remind();
            log.info("## 定时任务-日程提醒：完成！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 重复日程处理
     */
    public void repeat() {
        try {
            log.info("## 定时任务-重复日程：开始。。。");
            scheduleService.repeat();
            log.info("## 定时任务-重复日程：完成！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
