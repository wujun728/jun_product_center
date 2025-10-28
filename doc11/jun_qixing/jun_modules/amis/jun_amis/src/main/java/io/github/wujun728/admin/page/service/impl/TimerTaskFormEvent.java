package io.github.wujun728.admin.page.service.impl;

import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.page.data.Form;
import io.github.wujun728.admin.page.service.FormEvent;
import io.github.wujun728.admin.rbac.service.TimerTaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/***
 * 定时任务表单事件
 */
@Service("timerTaskFormEvent")
public class TimerTaskFormEvent implements FormEvent {

    @Resource
    private TimerTaskService timerTaskService;

    @Override
    public Result afterSave(Map<String, Object> obj, String tableName, Form form) {
        //更新定时任务
        Long id = (Long) obj.get("id");
        timerTaskService.updateTask(id);
        return null;
    }
}
