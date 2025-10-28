package io.github.wujun728.admin.rbac.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.cron.CronUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.rbac.constants.TimerTaskRecordStatus;
import io.github.wujun728.admin.rbac.data.DynamicTask;
import io.github.wujun728.admin.rbac.service.ApiService;
import io.github.wujun728.admin.rbac.service.DynamicTaskApi;
import io.github.wujun728.admin.rbac.service.DynamicTaskService;
import io.github.wujun728.admin.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("dynamicTaskService")
public class DynamicTaskServiceImpl implements DynamicTaskService {

    private Map<Long,String> taskIds = new HashMap<>();

    @Resource
    private JdbcService jdbcService;

    @Resource
    private ApiService apiService;

    @Override
    public void start() {
        List<DynamicTask> dynamicTasks = jdbcService.find("select * from Dynamic_Task where timer_Task_Status is null or timer_Task_Status = ? ", DynamicTask.class, TimerTaskRecordStatus.Running);
        int i=0;
        for(DynamicTask dynamicTask:dynamicTasks){

            if(dynamicTask.getPlanTime().getTime() < System.currentTimeMillis()){
                dynamicTask.setPlanTime(new Date(System.currentTimeMillis()+10*1000*(++i)));
            }
            jdbcService.saveOrUpdate(dynamicTask);

            //一次性定时任务
            //0 0 0 0 0 ? 2021
            //秒分时日月年
            String schedulingPattern = DateUtil.format(dynamicTask.getPlanTime(),"s m H d M")+" ? "+DateUtil.format(dynamicTask.getPlanTime(),"y");
            String taskId = CronUtil.schedule(schedulingPattern, new Runnable() {
                @Override
                public void run() {
                    DynamicTaskServiceImpl.this.run(dynamicTask);
                }
            });
            taskIds.put(dynamicTask.getId(),taskId);
        }
    }

    @Override
    public void stop() {
    }

    @Override
    public Long save(String name,Long refId, Date planTime, Map<String, Object> params, String api,String javaService) {
        DynamicTask task = new DynamicTask();
        task.setRefId(refId);
        task.setPlanTime(planTime);
        task.setApi(api);
        task.setName(name);
        task.setCreateTime(new Date());
        task.setTimerTaskStatus(null);
        task.setJavaService(javaService);
        if(StringUtils.isBlank(api) && StringUtils.isBlank(javaService)){
           throw new RuntimeException("magicApi接口和java接口必须有一个");
        }
        if(StringUtils.isNotBlank(api) && StringUtils.isNotBlank(javaService)){
            throw new RuntimeException("magicApi接口和java接口只能有一个");
        }
        if(params != null){
            task.setParams(JSONUtil.toJsonPrettyStr(params));
        }
        jdbcService.saveOrUpdate(task);

        log.info("启用任务:"+task.getName());

        //一次性定时任务
        //0 0 0 0 0 ? 2021
        //秒分时日月年
        String schedulingPattern = DateUtil.format(planTime,"s m H d M")+" ? "+DateUtil.format(planTime,"y");
        String taskId = CronUtil.schedule(schedulingPattern, new Runnable() {
            @Override
            public void run() {
                DynamicTaskServiceImpl.this.run(task);
            }
        });
        taskIds.put(task.getId(),taskId);

        return task.getId();
    }

    private void run(DynamicTask _task){
        final DynamicTask task = jdbcService.getById(DynamicTask.class,_task.getId());
        task.setStartTime(new Date());
        task.setTimerTaskStatus(TimerTaskRecordStatus.Running);
        jdbcService.saveOrUpdate(task);
        try{
            Map<String,Object> context = new HashMap<>();
            context.put("id",task.getRefId());
            if(StringUtils.isNotBlank(task.getParams())){
                JSONObject jsonObject = JSONUtil.parseObj(task.getParams());
                context.putAll(jsonObject);
            }
            jdbcService.transactionOption(()->{
                if(StringUtils.isNotBlank(task.getApi())){
                    Result<String> call = apiService.call(task.getApi(), context);
                    if(!call.isSuccess()){
                        throw new RuntimeException(call.getMsg());
                    }
                }else{
                    DynamicTaskApi api = SpringUtil.getBean(task.getJavaService());
                    String msg = api.execute(task);
                    if(msg != null){
                        throw new RuntimeException(msg);
                    }
                }
            });
            task.setTimerTaskStatus(TimerTaskRecordStatus.Success);
        }catch (Exception e){
            log.error("定时任务执行失败",e);
            task.setMsg(e.getMessage());
            task.setTimerTaskStatus(TimerTaskRecordStatus.Fail);
        }
        task.setEndTime(new Date());
        String taskId = taskIds.remove(task.getId());
        jdbcService.saveOrUpdate(task);
        CronUtil.remove(taskId);
    }

    @Override
    public void stop(Long id) {
        String taskId = taskIds.get(id);
        DynamicTask task = jdbcService.getById(DynamicTask.class, id);
        if(!(StringUtils.isBlank(task.getTimerTaskStatus())
                || TimerTaskRecordStatus.Running.equals(task.getTimerTaskStatus()))){
            return;
        }
        if(taskId != null){
            StaticLog.info("停用任务:"+task.getName());
            CronUtil.remove(taskId);
            task.setTimerTaskStatus(TimerTaskRecordStatus.Fail);
            task.setMsg("手动停止任务");
            taskIds.remove(task.getId());
            jdbcService.saveOrUpdate(task);
        }
    }
}
