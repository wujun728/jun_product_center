package com.ruoyi.schedule.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.schedule.domain.Schedule;
import com.ruoyi.schedule.domain.ScheduleDTO;
import com.ruoyi.schedule.module.ScheduleParam;
import com.ruoyi.schedule.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 日程Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/schedule/schedule")
public class ScheduleController extends BaseController {
    @Autowired
    private IScheduleService scheduleService;

    /**
     * 查询日程列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Schedule schedule) {
        startPage();
        List<Schedule> list = scheduleService.listSchedule(schedule);
        return getDataTable(list);
    }

    /**
     * 查询月度日程列表
     * @param schedule
     * @return
     */
    @GetMapping("/month")
    public AjaxResult getMonthData(Schedule schedule) {
        return success(scheduleService.getScheduleMonthMap(schedule));
    }

    /**
     * 获取当天日程
     * @param schedule
     * @return
     */
    @GetMapping("/day")
    public TableDataInfo getDayData(Schedule schedule) {
        startPage();
        List<ScheduleDTO> list = scheduleService.getScheduleDayList(schedule);
        return getDataTable(list);
    }

    /**
     * 获取包含日程的日期列表
     * @param schedule
     * @return
     */
    @GetMapping("/contain/date")
    public AjaxResult getDateScheduleList(Schedule schedule) {
        return success(scheduleService.getMonthScheduleList(schedule));
    }

    /**
     * 获取日程详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(scheduleService.getScheduleInfoResult(id));
    }

    /**
     * 新增日程
     */
    @Log(title = "日程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult saveOrUpdate(@RequestBody ScheduleParam schedule) {
        return AjaxResult.success("操作成功", scheduleService.saveOrUpdateSchedule(schedule));
    }

    /**
     * 删除日程
     */
    @Log(title = "日程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable String id) {
        return toAjax(scheduleService.remove(id));
    }
}
