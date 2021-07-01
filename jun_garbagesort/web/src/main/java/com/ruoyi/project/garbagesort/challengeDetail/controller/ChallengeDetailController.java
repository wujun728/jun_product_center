package com.ruoyi.project.garbagesort.challengeDetail.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.garbagesort.challengeDetail.domain.ChallengeDetail;
import com.ruoyi.project.garbagesort.challengeDetail.service.IChallengeDetailService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 挑战明细记录Controller
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
@Controller
@RequestMapping("/garbagesort/challengeDetail")
public class ChallengeDetailController extends BaseController
{
    private String prefix = "garbagesort/challengeDetail";

    @Autowired
    private IChallengeDetailService challengeDetailService;

    @RequiresPermissions("garbagesort:challengeDetail:view")
    @GetMapping()
    public String challengeDetail()
    {
        return prefix + "/challengeDetail";
    }

    /**
     * 查询挑战明细记录列表
     */
    @RequiresPermissions("garbagesort:challengeDetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ChallengeDetail challengeDetail)
    {
        startPage();
        List<ChallengeDetail> list = challengeDetailService.selectChallengeDetailList(challengeDetail);
        return getDataTable(list);
    }

    /**
     * 导出挑战明细记录列表
     */
    @RequiresPermissions("garbagesort:challengeDetail:export")
    @Log(title = "挑战明细记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ChallengeDetail challengeDetail)
    {
        List<ChallengeDetail> list = challengeDetailService.selectChallengeDetailList(challengeDetail);
        ExcelUtil<ChallengeDetail> util = new ExcelUtil<ChallengeDetail>(ChallengeDetail.class);
        return util.exportExcel(list, "挑战明细记录数据");
    }

    /**
     * 新增挑战明细记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存挑战明细记录
     */
    @RequiresPermissions("garbagesort:challengeDetail:add")
    @Log(title = "挑战明细记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ChallengeDetail challengeDetail)
    {
        return toAjax(challengeDetailService.insertChallengeDetail(challengeDetail));
    }

    /**
     * 修改挑战明细记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ChallengeDetail challengeDetail = challengeDetailService.selectChallengeDetailById(id);
        mmap.put("challengeDetail", challengeDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存挑战明细记录
     */
    @RequiresPermissions("garbagesort:challengeDetail:edit")
    @Log(title = "挑战明细记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ChallengeDetail challengeDetail)
    {
        return toAjax(challengeDetailService.updateChallengeDetail(challengeDetail));
    }

    /**
     * 删除挑战明细记录
     */
    @RequiresPermissions("garbagesort:challengeDetail:remove")
    @Log(title = "挑战明细记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(challengeDetailService.deleteChallengeDetailByIds(ids));
    }
}
