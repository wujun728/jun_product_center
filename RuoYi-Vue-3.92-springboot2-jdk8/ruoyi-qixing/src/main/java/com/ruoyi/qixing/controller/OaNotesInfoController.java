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
import com.ruoyi.qixing.domain.OaNotesInfo;
import com.ruoyi.qixing.service.IOaNotesInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 公告通知Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/notes")
public class OaNotesInfoController extends BaseController
{
    @Autowired
    private IOaNotesInfoService oaNotesInfoService;

    /**
     * 查询公告通知列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaNotesInfo oaNotesInfo)
    {
        startPage();
        List<OaNotesInfo> list = oaNotesInfoService.selectOaNotesInfoList(oaNotesInfo);
        return getDataTable(list);
    }

    /**
     * 导出公告通知列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaNotesInfo oaNotesInfo)
    {
        List<OaNotesInfo> list = oaNotesInfoService.selectOaNotesInfoList(oaNotesInfo);
        ExcelUtil<OaNotesInfo> util = new ExcelUtil<OaNotesInfo>(OaNotesInfo.class);
        util.exportExcel(response, list, "公告通知数据");
    }

    /**
     * 获取公告通知详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(oaNotesInfoService.selectOaNotesInfoById(id));
    }

    /**
     * 新增公告通知
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @PostMapping
    public AjaxResult add(@RequestBody OaNotesInfo oaNotesInfo)
    {
        return toAjax(oaNotesInfoService.insertOaNotesInfo(oaNotesInfo));
    }

    /**
     * 修改公告通知
     */
    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody OaNotesInfo oaNotesInfo)
    {
        return toAjax(oaNotesInfoService.updateOaNotesInfo(oaNotesInfo));
    }

    /**
     * 删除公告通知
     */
    @PreAuthorize("@ss.hasPermi('system:info:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(oaNotesInfoService.deleteOaNotesInfoByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<OaNotesInfo> list = oaNotesInfoService.selectOaNotesInfoList(new OaNotesInfo());
        return success(list);
    }
}
