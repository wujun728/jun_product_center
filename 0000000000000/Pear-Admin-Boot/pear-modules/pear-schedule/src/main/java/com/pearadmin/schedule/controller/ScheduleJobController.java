package com.pearadmin.schedule.controller;

import com.github.pagehelper.PageInfo;
import com.pearadmin.common.constant.ControllerConstant;
import com.pearadmin.common.tools.sequence.SequenceUtil;
import com.pearadmin.common.web.base.BaseController;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.common.web.domain.response.Result;
import com.pearadmin.common.web.domain.response.module.ResultTable;
import com.pearadmin.schedule.domain.ScheduleJob;
import com.pearadmin.schedule.service.IScheduleJobService;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * Describe: 定时任务控制器
 * Author: 就眠仪式
 * createTime: 2019/10/23
 * */
@RestController
@Api(tags = {"定时任务"})
@RequestMapping(ControllerConstant.API_SCHEDULE_PREFIX + "job")
public class ScheduleJobController extends BaseController {

    /**
     * Describe: 定时任务服务
     * */
    @Resource
    private IScheduleJobService scheduleJobService;

    /**
     * Describe: 获取定时任务列表视图
     * Param ModelAndView
     * Return 定时任务列表视图
     * */
    @GetMapping("main")
    @PreAuthorize("hasPermission('/schdule/job/main','sch:job:main')")
    public ModelAndView main(){
        return jumpPage("schedule/job/main");
    }

    /**
     * Describe: 获取定时任务列表数据
     * Param PageDomain
     * Return 定时任务列表数据
     * */
    @GetMapping("data")
    @PreAuthorize("hasPermission('/schdule/job/data','sch:job:data')")
    public ResultTable data(PageDomain pageDomain, ScheduleJob param){
       PageInfo<ScheduleJob> pageInfo =  scheduleJobService.page(param,pageDomain);
       return pageTable(pageInfo.getList(),pageInfo.getTotal());
    }

    /**
     * Describe: 获取定时任务新增视图
     * Param ModelAndView
     * Return ModelAndView
     * */
    @GetMapping("add")
    @PreAuthorize("hasPermission('/schdule/job/add','sch:job:add')")
    public ModelAndView add(){
        return jumpPage("schedule/job/add");
    }

    /**
     * Describe: 获取定时任务修改视图
     * Param ModelAndView
     * Return ModelAndView
     * */
    @GetMapping("edit")
    @PreAuthorize("hasPermission('/schdule/job/edit','sch:job:edit')")
    public ModelAndView edit(Model model, String jobId){
        model.addAttribute("scheduleJob",scheduleJobService.getById(jobId));
        return jumpPage("schedule/job/edit");
    }

    /**
     * Describe: 保存定时任务数据
     * Param ScheduleJob
     * Return Result
     * */
    @RequestMapping("/save")
    @PreAuthorize("hasPermission('/schdule/job/add','sch:job:add')")
    public Result save (@RequestBody ScheduleJob scheduleJob){
        scheduleJob.setJobId(SequenceUtil.makeStringId());
        scheduleJob.setCreateTime(LocalDateTime.now());
        Boolean result = scheduleJobService.save(scheduleJob);
        return decide(result);
    }

    /**
     * Describe: 执行一次定时任务
     * Param ScheduleJob
     * Return Result 执行结果
     * */
    @RequestMapping("/run")
    @PreAuthorize("hasPermission('/schdule/job/run','sch:job:run')")
    public Result run (String jobId){
        scheduleJobService.run(jobId);
        return success("运行成功") ;
    }

    /**
     * Describe: 更新定时任务数据
     * Param ScheduleJob
     * Return Result
     * */
    @RequestMapping("/update")
    @PreAuthorize("hasPermission('/schdule/job/edit','sch:job:edit')")
    public Result update (@RequestBody ScheduleJob scheduleJob){
        Boolean result = scheduleJobService.update(scheduleJob) ;
        return decide(result);
    }

    /**
     * Describe: 停止定时任务
     * Param jobId
     * Return Result 执行结果
     * */
    @PutMapping("/pause")
    @PreAuthorize("hasPermission('/schdule/job/pause','sch:job:pause')")
    public Result pauseJob (String jobId){
        Boolean result = scheduleJobService.pause(jobId);
        return decide(
                result,
                "停止成功",
                "停止失败"
        );
    }

    /**
     * Describe: 恢复定时任务
     * Param: jobId
     * Return: 恢复定时任务
     * */
    @RequestMapping("/resume")
    @PreAuthorize("hasPermission('/schdule/job/resume','sch:job:resume')")
    public Result resumeJob (String jobId){
        Boolean result = scheduleJobService.resume(jobId);
        return decide(result,"恢复成功","恢复失败");
    }

    /**
     * Describe: 删除定时任务
     * Param: jobId
     * Return Result
     * */
    @RequestMapping("/remove/{id}")
    @PreAuthorize("hasPermission('/schdule/job/remove','sch:job:remove')")
    public Result deleteJob (@PathVariable("id") String jobId){
        Boolean result = scheduleJobService.delete(jobId);
        return decide(result,"删除成功","删除失败");
    }
}
