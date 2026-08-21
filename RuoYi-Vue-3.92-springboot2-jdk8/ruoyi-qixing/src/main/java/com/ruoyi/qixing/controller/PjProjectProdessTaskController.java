package com.ruoyi.qixing.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qixing.domain.PjProjectProdessTask;
import com.ruoyi.qixing.service.IPjProjectProdessTaskService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目进度与任务(WBS)Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/task")
public class PjProjectProdessTaskController extends BaseController
{
    @Autowired
    private IPjProjectProdessTaskService pjProjectProdessTaskService;

    /**
     * 查询项目进度与任务(WBS)列表
     */
    @PreAuthorize("@ss.hasPermi('system:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(PjProjectProdessTask pjProjectProdessTask)
    {
        startPage();
        List<PjProjectProdessTask> list = pjProjectProdessTaskService.selectPjProjectProdessTaskList(pjProjectProdessTask);
        return getDataTable(list);
    }

    /**
     * 导出项目进度与任务(WBS)列表
     */
    @PreAuthorize("@ss.hasPermi('system:task:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, PjProjectProdessTask pjProjectProdessTask)
    {
        List<PjProjectProdessTask> list = pjProjectProdessTaskService.selectPjProjectProdessTaskList(pjProjectProdessTask);
        ExcelUtil<PjProjectProdessTask> util = new ExcelUtil<PjProjectProdessTask>(PjProjectProdessTask.class);
        util.exportExcel(response, list, "项目进度与任务(WBS)数据");
    }

    /**
     * 获取项目进度与任务(WBS)详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:task:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(pjProjectProdessTaskService.selectPjProjectProdessTaskById(id));
    }

    /**
     * 新增项目进度与任务(WBS)
     */
    @PreAuthorize("@ss.hasPermi('system:task:add')")
    @PostMapping
    public AjaxResult add(@RequestBody PjProjectProdessTask pjProjectProdessTask)
    {
        return toAjax(pjProjectProdessTaskService.insertPjProjectProdessTask(pjProjectProdessTask));
    }

    /**
     * 修改项目进度与任务(WBS)
     */
    @PreAuthorize("@ss.hasPermi('system:task:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody PjProjectProdessTask pjProjectProdessTask)
    {
        return toAjax(pjProjectProdessTaskService.updatePjProjectProdessTask(pjProjectProdessTask));
    }

    /**
     * 删除项目进度与任务(WBS)
     */
    @PreAuthorize("@ss.hasPermi('system:task:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(pjProjectProdessTaskService.deletePjProjectProdessTaskByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<PjProjectProdessTask> list = pjProjectProdessTaskService.selectPjProjectProdessTaskList(new PjProjectProdessTask());
        return success(list);
    }
}
