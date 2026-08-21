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
import com.ruoyi.qixing.domain.PjProjectReportnumber;
import com.ruoyi.qixing.service.IPjProjectReportnumberService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目报告文号Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/reportnumber")
public class PjProjectReportnumberController extends BaseController
{
    @Autowired
    private IPjProjectReportnumberService pjProjectReportnumberService;

    /**
     * 查询项目报告文号列表
     */
    @PreAuthorize("@ss.hasPermi('system:reportnumber:list')")
    @GetMapping("/list")
    public TableDataInfo list(PjProjectReportnumber pjProjectReportnumber)
    {
        startPage();
        List<PjProjectReportnumber> list = pjProjectReportnumberService.selectPjProjectReportnumberList(pjProjectReportnumber);
        return getDataTable(list);
    }

    /**
     * 导出项目报告文号列表
     */
    @PreAuthorize("@ss.hasPermi('system:reportnumber:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, PjProjectReportnumber pjProjectReportnumber)
    {
        List<PjProjectReportnumber> list = pjProjectReportnumberService.selectPjProjectReportnumberList(pjProjectReportnumber);
        ExcelUtil<PjProjectReportnumber> util = new ExcelUtil<PjProjectReportnumber>(PjProjectReportnumber.class);
        util.exportExcel(response, list, "项目报告文号数据");
    }

    /**
     * 获取项目报告文号详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:reportnumber:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(pjProjectReportnumberService.selectPjProjectReportnumberById(id));
    }

    /**
     * 新增项目报告文号
     */
    @PreAuthorize("@ss.hasPermi('system:reportnumber:add')")
    @PostMapping
    public AjaxResult add(@RequestBody PjProjectReportnumber pjProjectReportnumber)
    {
        return toAjax(pjProjectReportnumberService.insertPjProjectReportnumber(pjProjectReportnumber));
    }

    /**
     * 修改项目报告文号
     */
    @PreAuthorize("@ss.hasPermi('system:reportnumber:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody PjProjectReportnumber pjProjectReportnumber)
    {
        return toAjax(pjProjectReportnumberService.updatePjProjectReportnumber(pjProjectReportnumber));
    }

    /**
     * 删除项目报告文号
     */
    @PreAuthorize("@ss.hasPermi('system:reportnumber:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(pjProjectReportnumberService.deletePjProjectReportnumberByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<PjProjectReportnumber> list = pjProjectReportnumberService.selectPjProjectReportnumberList(new PjProjectReportnumber());
        return success(list);
    }
}
