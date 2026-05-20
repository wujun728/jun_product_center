package com.ruoyi.schedule.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.schedule.domain.ScheduleParts;
import com.ruoyi.schedule.service.ISchedulePartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 日程参与人Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/schedule/parts")
public class SchedulePartsController extends BaseController {
    @Autowired
    private ISchedulePartsService schedulePartsService;

    /**
     * 查询日程参与人列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ScheduleParts scheduleParts) {
        startPage();
        List<ScheduleParts> list = schedulePartsService.listScheduleParts(scheduleParts);
        return getDataTable(list);
    }

    /**
     * 获取日程参与人详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(schedulePartsService.getSchedulePartsById(id));
    }

    /**
     * 新增日程参与人
     */
    @Log(title = "日程参与人", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ScheduleParts scheduleParts) {
        return toAjax(schedulePartsService.saveScheduleParts(scheduleParts));
    }

    /**
     * 修改日程参与人
     */
    @Log(title = "日程参与人", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ScheduleParts scheduleParts) {
        return toAjax(schedulePartsService.updateScheduleParts(scheduleParts));
    }

    /**
     * 删除日程参与人
     */
    @Log(title = "日程参与人", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(schedulePartsService.deleteSchedulePartsByIds(ids));
    }
}
