package io.github.wujun728.admin.util;

import io.github.wujun728.admin.common.log.service.LogService;
import io.github.wujun728.admin.common.service.SysFileService;
import io.github.wujun728.admin.rbac.service.DynamicTaskService;
import io.github.wujun728.admin.rbac.service.TimerTaskService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

/**
 * @Author: Baron
 * @Description:
 * @Date: Created in 2019/3/2317:16
 */
@Component
@Log4j2
public class ApplicationEventListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent){
            log.info("环境初始化！！！");
        } else if (event instanceof ApplicationPreparedEvent){
            log.info("初始化完成！！！");
        } else if (event instanceof ContextRefreshedEvent) {
            log.info("应用刷新！！");

            //ThreadPoolTaskScheduler scheduler = (ThreadPoolTaskScheduler) SpringContextUtil.getBean("taskScheduler");
           // scheduler.shutdown();
           // log.info("停止定时任务");
        } else if (event instanceof ApplicationReadyEvent) {
            log.info("项目启动完成！！");
            log.info("开始初始化定时任务111 ");
            //TODO wujun
            /*TimerTaskService taskService = SpringUtil.getBean(TimerTaskService.class);
            taskService.start();
            log.info("结束初始化定时任务");

            log.info("开始初始化动态任务");
            DynamicTaskService dynamicTaskService = SpringUtil.getBean(DynamicTaskService.class);
            dynamicTaskService.start();
            log.info("结束初始化动态任务");

            LogService logService = SpringUtil.getBean(LogService.class);
            logService.startLog();
            log.info("启动在线日志");*/

        } else if (event instanceof ContextStartedEvent) {
            log.info("应用启动！！");
        } else if (event instanceof ContextStoppedEvent) {
            log.info("项目停止！！");
        } else if (event instanceof ContextClosedEvent) {
            log.info("应用关闭！！");
            TimerTaskService taskService = SpringUtil.getBean(TimerTaskService.class);
            taskService.stop();
            log.info("关闭定时任务");

            SysFileService sysFileService = SpringUtil.getBean(SysFileService.class);
            sysFileService.shutdown();
            log.info("关闭文件传输客户端");
        }
    }

}
