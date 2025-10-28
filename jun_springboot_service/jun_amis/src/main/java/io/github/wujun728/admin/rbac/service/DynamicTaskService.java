package io.github.wujun728.admin.rbac.service;

import java.util.Date;
import java.util.Map;

/***
 * 动态定时任务
 */
public interface DynamicTaskService {
    void start();
    void stop();
    Long save(String name,Long refId, Date planTime, Map<String,Object> params,String api,String javaService);
    void stop(Long taskId);
}
