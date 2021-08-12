package com.shuogesha.scheduler.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.shuogesha.cms.entity.OrderSetting;
import com.shuogesha.cms.service.OrderService;
import com.shuogesha.cms.service.OrderSettingService;
import com.shuogesha.common.util.UtilDate;
import com.shuogesha.platform.entity.JobLog;
import com.shuogesha.platform.entity.ScheduleJob;
import com.shuogesha.platform.service.JobLogService;
 

//Durability，持久性；如果Job是非持久性的，一旦没有Trigger与其相关联，它就会从Scheduler中被删除。也就是说Job的生命周期和其Trigger是关联的。
//RequestsRecovery，如果为true，那么在Scheduler异常中止或者系统异常关闭后，当Scheduler重启后，Job会被重新执行。

//下面两种可以同时使用
@DisallowConcurrentExecution //同一时间将只有一个Job实例被执行。
@PersistJobDataAfterExecution //在Job被执行结束后，将会更新JobDataMap，这样下次Job执行后就会使用新的值而不是初始值。 
public class OrderCancelJob implements Job {
	
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
    	  System.out.println("任务成功运行："+UtilDate.getNow());
          ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
    	  JobLog bean = new JobLog(scheduleJob.getName(), " 开始", UtilDate.getNow(), "0");
    	  jobLogService.save(bean); 
    	  int num = 0,count = 0;
    	  	try {
    	  		OrderSetting orderSetting =orderSettingService.findOne();
    			if(orderSetting!=null&&orderSetting.getPayTimeout()>0) { 
	    	  	  orderService.cancelAllScan(orderSetting.getPayTimeout());
		          num = 1;
    			}
	 	     } catch (Exception e) {
	 	    	 num =-1;
	 	  	 }
          System.out.println("任务名称 = [" + scheduleJob.getName() + "]"+UtilDate.getNow());
          bean = new JobLog(scheduleJob.getName(), count+"个结束", UtilDate.getNow(), String.valueOf(num));
    	  jobLogService.save(bean);
    } 
    
    @Autowired
	public JobLogService jobLogService; 
    @Autowired
	private  OrderSettingService orderSettingService;
    @Autowired
	public OrderService orderService; 
}
