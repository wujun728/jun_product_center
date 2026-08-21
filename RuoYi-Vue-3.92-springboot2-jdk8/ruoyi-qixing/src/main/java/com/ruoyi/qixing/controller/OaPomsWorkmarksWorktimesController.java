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
import com.ruoyi.qixing.domain.OaPomsWorkmarksWorktimes;
import com.ruoyi.qixing.service.IOaPomsWorkmarksWorktimesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考勤记录Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/worktimes")
public class OaPomsWorkmarksWorktimesController extends BaseController
{
    @Autowired
    private IOaPomsWorkmarksWorktimesService oaPomsWorkmarksWorktimesService;

    /**
     * 查询考勤记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:worktimes:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaPomsWorkmarksWorktimes oaPomsWorkmarksWorktimes)
    {
        startPage();
        List<OaPomsWorkmarksWorktimes> list = oaPomsWorkmarksWorktimesService.selectOaPomsWorkmarksWorktimesList(oaPomsWorkmarksWorktimes);
        return getDataTable(list);
    }

    /**
     * 导出考勤记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:worktimes:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaPomsWorkmarksWorktimes oaPomsWorkmarksWorktimes)
    {
        List<OaPomsWorkmarksWorktimes> list = oaPomsWorkmarksWorktimesService.selectOaPomsWorkmarksWorktimesList(oaPomsWorkmarksWorktimes);
        ExcelUtil<OaPomsWorkmarksWorktimes> util = new ExcelUtil<OaPomsWorkmarksWorktimes>(OaPomsWorkmarksWorktimes.class);
        util.exportExcel(response, list, "考勤记录数据");
    }

    /**
     * 获取考勤记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:worktimes:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(oaPomsWorkmarksWorktimesService.selectOaPomsWorkmarksWorktimesById(id));
    }

    /**
     * 新增考勤记录
     */
    @PreAuthorize("@ss.hasPermi('system:worktimes:add')")
    @PostMapping
    public AjaxResult add(@RequestBody OaPomsWorkmarksWorktimes oaPomsWorkmarksWorktimes)
    {
        return toAjax(oaPomsWorkmarksWorktimesService.insertOaPomsWorkmarksWorktimes(oaPomsWorkmarksWorktimes));
    }

    /**
     * 修改考勤记录
     */
    @PreAuthorize("@ss.hasPermi('system:worktimes:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody OaPomsWorkmarksWorktimes oaPomsWorkmarksWorktimes)
    {
        return toAjax(oaPomsWorkmarksWorktimesService.updateOaPomsWorkmarksWorktimes(oaPomsWorkmarksWorktimes));
    }

    /**
     * 删除考勤记录
     */
    @PreAuthorize("@ss.hasPermi('system:worktimes:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(oaPomsWorkmarksWorktimesService.deleteOaPomsWorkmarksWorktimesByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<OaPomsWorkmarksWorktimes> list = oaPomsWorkmarksWorktimesService.selectOaPomsWorkmarksWorktimesList(new OaPomsWorkmarksWorktimes());
        return success(list);
    }
}
