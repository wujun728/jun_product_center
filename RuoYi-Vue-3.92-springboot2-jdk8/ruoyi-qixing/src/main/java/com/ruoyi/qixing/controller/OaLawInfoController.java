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
import com.ruoyi.qixing.domain.OaLawInfo;
import com.ruoyi.qixing.service.IOaLawInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 政策法规Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/law")
public class OaLawInfoController extends BaseController
{
    @Autowired
    private IOaLawInfoService oaLawInfoService;

    /**
     * 查询政策法规列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaLawInfo oaLawInfo)
    {
        startPage();
        List<OaLawInfo> list = oaLawInfoService.selectOaLawInfoList(oaLawInfo);
        return getDataTable(list);
    }

    /**
     * 导出政策法规列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaLawInfo oaLawInfo)
    {
        List<OaLawInfo> list = oaLawInfoService.selectOaLawInfoList(oaLawInfo);
        ExcelUtil<OaLawInfo> util = new ExcelUtil<OaLawInfo>(OaLawInfo.class);
        util.exportExcel(response, list, "政策法规数据");
    }

    /**
     * 获取政策法规详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(oaLawInfoService.selectOaLawInfoById(id));
    }

    /**
     * 新增政策法规
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @PostMapping
    public AjaxResult add(@RequestBody OaLawInfo oaLawInfo)
    {
        return toAjax(oaLawInfoService.insertOaLawInfo(oaLawInfo));
    }

    /**
     * 修改政策法规
     */
    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody OaLawInfo oaLawInfo)
    {
        return toAjax(oaLawInfoService.updateOaLawInfo(oaLawInfo));
    }

    /**
     * 删除政策法规
     */
    @PreAuthorize("@ss.hasPermi('system:info:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(oaLawInfoService.deleteOaLawInfoByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<OaLawInfo> list = oaLawInfoService.selectOaLawInfoList(new OaLawInfo());
        return success(list);
    }
}
