package com.shuogesha.async.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.shuogesha.platform.entity.SystemLog;
import com.shuogesha.platform.service.SystemLogService;
/**
 * 测试异步执行日志的保存任务
 * @author zhaohaiyuan
 *
 */
@Service
public class AsyncLogTaskService {
	@Autowired
	private SystemLogService systemLogService;
	
    @Async
    public void executeAsyncTask(SystemLog bean) {
    	systemLogService.save(bean);
     }
}
