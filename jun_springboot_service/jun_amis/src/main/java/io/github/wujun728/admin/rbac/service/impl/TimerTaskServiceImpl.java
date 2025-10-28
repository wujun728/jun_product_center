package io.github.wujun728.admin.rbac.service.impl;

import cn.hutool.cron.CronUtil;
import cn.hutool.cron.pattern.CronPattern;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.rbac.constants.DataStatus;
import io.github.wujun728.admin.rbac.constants.TimerTaskRecordStatus;
import io.github.wujun728.admin.rbac.data.TimerTask;
import io.github.wujun728.admin.rbac.data.TimerTaskRecord;
import io.github.wujun728.admin.rbac.service.ApiService;
import io.github.wujun728.admin.rbac.service.TimerTaskService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hyz
 * @date 2021/4/7 17:17
 */
@Log4j2
@Service("timerTaskService")
public class TimerTaskServiceImpl implements TimerTaskService {
    @Resource
    JdbcService jdbcService;

    @Resource
    ApiService apiService;

    private Map<Long,String> taskIds = new HashMap<>();
    @Override
    public void start() {
        CronUtil.setMatchSecond(true);
        CronUtil.start();
        List<TimerTask> tasks = jdbcService.find(TimerTask.class,"dataStatus", DataStatus.Valid);
        for(TimerTask task:tasks){
            start(task);
        }
    }

    @Override
    public void stop() {
        CronUtil.stop();
    }

    @Override
    public void start(final TimerTask timerTask) {
        if(taskIds.containsKey(timerTask.getId())){
            return;
        }
        log.info("启用任务:"+timerTask.getName());
        String taskId = CronUtil.schedule(timerTask.getSchedulingPattern(), new Runnable() {
            @Override
            public void run() {
                TimerTaskServiceImpl.this.run(timerTask,null);
            }
        });
        taskIds.put(timerTask.getId(),taskId);
    }

    @Override
    public void stop(TimerTask timerTask) {
        String taskId = taskIds.get(timerTask.getId());
        if(taskId != null){
            StaticLog.info("停用接口:"+timerTask.getName());
            CronUtil.remove(taskId);
            taskIds.remove(timerTask.getId());
        }
    }

    @Override
    public void run(TimerTask timerTask, TimerTaskRecord record) {
        log.info("开始执行定时任务:"+timerTask.getName());
        try{
            if(record == null){
                record = new TimerTaskRecord();
                record.setTimerTaskId(timerTask.getId());
                record.setParams(timerTask.getParams());
            }
            record.setStartTime(new Date());
            record.setTimerTaskRecordStatus(TimerTaskRecordStatus.Running);
            jdbcService.saveOrUpdate(record);

            Map<String,Object> context = new HashMap<>();
            if(StringUtils.isNotBlank(record.getParams())){
                JSONObject jsonObject = JSONUtil.parseObj(record.getParams());
                context.putAll(jsonObject);
            }
            jdbcService.transactionOption(()->{
                Result<String> call = apiService.call(timerTask.getApi(), context);
                if(!call.isSuccess()){
                    throw new RuntimeException(call.getMsg());
                }
            });
        }catch (Exception e){
            log.error("定时任务执行失败:"+timerTask.getName(),e);
            if(record != null){
                record.setTimerTaskRecordStatus(TimerTaskRecordStatus.Fail);
                record.setMsg(e.getMessage());
            }
        }
        if(record != null){
            record.setEndTime(new Date());
            if(TimerTaskRecordStatus.Running.equals(record.getTimerTaskRecordStatus())){
                record.setTimerTaskRecordStatus(TimerTaskRecordStatus.Success);
            }
            jdbcService.saveOrUpdate(record);
        }
        log.info("定时任务执行完毕:"+timerTask.getName());
    }

    @Override
    public void update(TimerTask timerTask) {
        if(DataStatus.Valid.equals(timerTask.getDataStatus())){
            start(timerTask);
            updateSchedulingPattern(timerTask);
        }else{
            stop(timerTask);
        }
    }

    @Override
    public void updateSchedulingPattern(TimerTask timerTask) {
        if(taskIds.containsKey(timerTask.getId())){
            StaticLog.info("更新任务计划:"+timerTask.getName());
            CronUtil.updatePattern(taskIds.get(timerTask.getId()),new CronPattern(timerTask.getSchedulingPattern()));
        }
    }

    @Override
    public void updateTask(Long id) {
        TimerTask timerTask = jdbcService.getById(TimerTask.class, id);
        this.update(timerTask);
    }
}
