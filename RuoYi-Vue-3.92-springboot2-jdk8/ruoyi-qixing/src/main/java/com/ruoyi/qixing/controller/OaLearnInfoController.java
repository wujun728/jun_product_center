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
import com.ruoyi.qixing.domain.OaLearnInfo;
import com.ruoyi.qixing.service.IOaLearnInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 培训学习Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/learn")
public class OaLearnInfoController extends BaseController
{
    @Autowired
    private IOaLearnInfoService oaLearnInfoService;

    /**
     * 查询培训学习列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaLearnInfo oaLearnInfo)
    {
        startPage();
        List<OaLearnInfo> list = oaLearnInfoService.selectOaLearnInfoList(oaLearnInfo);
        return getDataTable(list);
    }

    /**
     * 导出培训学习列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaLearnInfo oaLearnInfo)
    {
        List<OaLearnInfo> list = oaLearnInfoService.selectOaLearnInfoList(oaLearnInfo);
        ExcelUtil<OaLearnInfo> util = new ExcelUtil<OaLearnInfo>(OaLearnInfo.class);
        util.exportExcel(response, list, "培训学习数据");
    }

    /**
     * 获取培训学习详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(oaLearnInfoService.selectOaLearnInfoById(id));
    }

    /**
     * 新增培训学习
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @PostMapping
    public AjaxResult add(@RequestBody OaLearnInfo oaLearnInfo)
    {
        return toAjax(oaLearnInfoService.insertOaLearnInfo(oaLearnInfo));
    }

    /**
     * 修改培训学习
     */
    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody OaLearnInfo oaLearnInfo)
    {
        return toAjax(oaLearnInfoService.updateOaLearnInfo(oaLearnInfo));
    }

    /**
     * 删除培训学习
     */
    @PreAuthorize("@ss.hasPermi('system:info:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(oaLearnInfoService.deleteOaLearnInfoByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<OaLearnInfo> list = oaLearnInfoService.selectOaLearnInfoList(new OaLearnInfo());
        return success(list);
    }
}
