package org.myframework.quartz.scheduler.rest;

import javax.annotation.Resource;

import org.myframework.quartz.scheduler.entities.JobInfo;
import org.myframework.quartz.scheduler.exception.FrameworkSchedulerException;
import org.myframework.quartz.scheduler.service.QuartzService;
import org.myframework.quartz.scheduler.service.SchedulerService;
import org.myframework.support.base.ActionLog;
import org.myframework.support.i18n.II18nService;
import org.myframework.web.bind.ServiceResult;
import org.quartz.SchedulerException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * <ol>DAO的操作没有封装到service层的原因：有时需要动态创建JOB，这些JOB运行完成后就删除，所以不需要保存数据
 * <li>{@link  }</li>
 * 
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年12月24日
 *
 */
@RestController
@RequestMapping("/scheduler")
public class SchedulerRest    {
	
	@Resource(name="i18nService")
	protected II18nService i18nService;
	
	@Resource(name = "schedulerService")
	private SchedulerService schedulerService;
	
	@RequestMapping(value = "/view.html")
	@ActionLog(content = "任务引擎首页", description = "")
	public ModelAndView view(ModelMap model){
		return new ModelAndView("forward:/hollybeacon/business/scheduler/page/job.jsp");
	}
	
	/**
	 * 查找Job
	 *
	 * @return ServiceResult
	 */
	@RequestMapping(value = "/getJob")
	@ActionLog(content = "查找Job", description = "")
	public ServiceResult getJob(String jobGroupName,String jobName) {
		ServiceResult serviceResult = new ServiceResult();
		Boolean success = true;
		try {
			JobInfo jobInfo = schedulerService.findJobFromDb(jobName , jobGroupName );
			serviceResult.setContent(jobInfo);
		} catch (Exception e) {
			throw new FrameworkSchedulerException(e.getMessage());
		}
		serviceResult.setSuccess(success);
		return serviceResult;
	}
	
	
	
	/**
	 * 添加定时任务，如果任务已存在，则修改执行规则
	 *
	 * @return ServiceResult
	 * @throws Exception 
	 */
	@ActionLog(content = "创建任务", description = "")
	@RequestMapping(value = "/createJobAndTrigger")
	public ServiceResult createJobAndTrigger(JobInfo jobInfo) throws Exception {
		ServiceResult serviceResult = new ServiceResult();
		//如果任务是需要执行的，则添加到调度服务器中执行
		if(JobInfo.STATUS_RUNNING.equals(jobInfo.getJobStatus()))
			schedulerService.createJobAndTrigger(jobInfo);
		serviceResult.setSuccess(true);
		return serviceResult;
	}	
	
	
	/**
	 * @return ServiceResult
	 */
	@ActionLog(content = "更新定时任务", description = "")
	@RequestMapping(value = "/updateJobAndTrigger")
	public ServiceResult updateJob(JobInfo jobInfo) throws Exception{
		ServiceResult serviceResult = new ServiceResult();
		schedulerService.updateJob(jobInfo);
		serviceResult.setSuccess(true);
		return serviceResult;
	}	
	
	/**
	 * 启动或者移除定时任务
	 * @return ServiceResult
	 * @throws Exception 
	 */
	@ActionLog(content = "启动或者移除定时任务", description = "")
	@RequestMapping(value = "/changeJobStatus")
	public ServiceResult changeJobStatus(JobInfo jobInfo) throws Exception {
		ServiceResult serviceResult = new ServiceResult();
		schedulerService.changeJobStatus(jobInfo);
		serviceResult.setSuccess(true);
		return serviceResult;
	}
	
 
	
	//--------------------------------------------------
	//对quartz运行状态的操作
	//---------------------------------------------------
	@Resource(name = "quartzService")
	private QuartzService quartzService;
	
	
	/**
	 * 动态创建并启动定时任务
	 * @return ServiceResult
	 * @throws Exception 
	 */
	@RequestMapping(value = "/dynamicCreateJob")
	@ActionLog(content = "动态创建并启动定时任务", description = "")
	public ServiceResult dynamicCreateJobAndTrigger(JobInfo jobInfo) throws Exception {
		ServiceResult serviceResult = new ServiceResult();
		quartzService.createJobAndTrigger(jobInfo); 
		serviceResult.setSuccess(true);
		return serviceResult;
	}
	
	/**
	 * 删除动态任务
	 * @return ServiceResult
	 * @throws Exception 
	 */
	@RequestMapping(value = "/deleteJob")
	@ActionLog(content = "删除动态任务", description = "")
	public ServiceResult deleteJob(JobInfo jobInfo) throws Exception {
		ServiceResult serviceResult = new ServiceResult();
		quartzService.deleteJob(jobInfo); 
		serviceResult.setSuccess(true);
		return serviceResult;
	}
	
	
	/**
	 * 立即执行
	 *
	 * @return ServiceResult
	 * @throws SchedulerException 
	 */
	@RequestMapping(value = "/triggerJob")
	@ActionLog(content = "立即执行定时任务", description = "")
	public ServiceResult triggerJob(JobInfo jobInfo) throws SchedulerException {
		ServiceResult serviceResult = new ServiceResult();
		quartzService.triggerJob(jobInfo);
		serviceResult.setSuccess(true);
		return serviceResult;
	}
	
	/**
	 * 重新启动定时任务
	 *
	 * @return ServiceResult
	 */
	@ActionLog(content = "重新启动定时任务", description = "")
	@RequestMapping(value = "/resumeJob")
	public ServiceResult resumeJob(JobInfo jobInfo) {
		ServiceResult serviceResult = new ServiceResult();
		Boolean success = true;
		try {
			quartzService.resumeJob(jobInfo);
		} catch (Exception e) {
			throw new FrameworkSchedulerException(e.getMessage());
		}
		serviceResult.setSuccess(success);
		return serviceResult;
	}
	
	/**
	 * 暂停定时任务
	 *
	 * @return ServiceResult
	 */
	@ActionLog(content = "暂停定时任务", description = "")
	@RequestMapping(value = "/pauseJob")
	public ServiceResult pauseJob(JobInfo jobInfo) {
		ServiceResult serviceResult = new ServiceResult();
		Boolean success = true;
		try {
			quartzService.pauseJob(jobInfo);
		} catch (Exception e) {
			throw new FrameworkSchedulerException(e.getMessage());
		}
		serviceResult.setSuccess(success);
		return serviceResult;
	}

}
