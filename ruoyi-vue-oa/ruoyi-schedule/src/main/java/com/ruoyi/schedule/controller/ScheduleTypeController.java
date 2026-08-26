package com.ruoyi.schedule.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.schedule.domain.ScheduleType;
import com.ruoyi.schedule.service.IScheduleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 日程分类Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/schedule/type")
public class ScheduleTypeController extends BaseController {
    @Autowired
    private IScheduleTypeService scheduleTypeService;

    /**
     * 查询日程分类列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ScheduleType scheduleType) {
        startPage();
        List<ScheduleType> list = scheduleTypeService.listScheduleType(scheduleType);
        return getDataTable(list);
    }

    /**
     * 获取所有日程分类列表
     * @return
     */
    @GetMapping("/person/all")
    public AjaxResult all() {
        return success(scheduleTypeService.listPersonAll());
    }

    /**
     * 获取日程分类详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(scheduleTypeService.getScheduleTypeById(id));
    }

    /**
     * 新增日程分类
     */
    @Log(title = "日程分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ScheduleType scheduleType) {
        return toAjax(scheduleTypeService.saveScheduleType(scheduleType));
    }

    /**
     * 修改日程分类
     */
    @Log(title = "日程分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ScheduleType scheduleType) {
        return toAjax(scheduleTypeService.updateScheduleType(scheduleType));
    }

    /**
     * 删除日程分类
     */
    @Log(title = "日程分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(scheduleTypeService.deleteScheduleTypeByIds(ids));
    }
}
