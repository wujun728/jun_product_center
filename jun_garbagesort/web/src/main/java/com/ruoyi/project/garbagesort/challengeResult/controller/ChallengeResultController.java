package com.ruoyi.project.garbagesort.challengeResult.controller;

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
import com.ruoyi.project.garbagesort.challengeResult.domain.ChallengeResult;
import com.ruoyi.project.garbagesort.challengeResult.service.IChallengeResultService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 挑战结果+详情记录Controller
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
@Controller
@RequestMapping("/garbagesort/challengeResult")
public class ChallengeResultController extends BaseController
{
    private String prefix = "garbagesort/challengeResult";

    @Autowired
    private IChallengeResultService challengeResultService;

    @RequiresPermissions("garbagesort:challengeResult:view")
    @GetMapping()
    public String challengeResult()
    {
        return prefix + "/challengeResult";
    }

    /**
     * 查询挑战结果+详情记录列表
     */
    @RequiresPermissions("garbagesort:challengeResult:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ChallengeResult challengeResult)
    {
        startPage();
        List<ChallengeResult> list = challengeResultService.selectChallengeResultList(challengeResult);
        return getDataTable(list);
    }

    /**
     * 导出挑战结果+详情记录列表
     */
    @RequiresPermissions("garbagesort:challengeResult:export")
    @Log(title = "挑战结果+详情记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ChallengeResult challengeResult)
    {
        List<ChallengeResult> list = challengeResultService.selectChallengeResultList(challengeResult);
        ExcelUtil<ChallengeResult> util = new ExcelUtil<ChallengeResult>(ChallengeResult.class);
        return util.exportExcel(list, "挑战结果+详情记录数据");
    }

    /**
     * 新增挑战结果+详情记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存挑战结果+详情记录
     */
    @RequiresPermissions("garbagesort:challengeResult:add")
    @Log(title = "挑战结果+详情记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ChallengeResult challengeResult)
    {
        return toAjax(challengeResultService.insertChallengeResult(challengeResult));
    }

    /**
     * 修改挑战结果+详情记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ChallengeResult challengeResult = challengeResultService.selectChallengeResultById(id);
        mmap.put("challengeResult", challengeResult);
        return prefix + "/edit";
    }

    /**
     * 修改保存挑战结果+详情记录
     */
    @RequiresPermissions("garbagesort:challengeResult:edit")
    @Log(title = "挑战结果+详情记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ChallengeResult challengeResult)
    {
        return toAjax(challengeResultService.updateChallengeResult(challengeResult));
    }

    /**
     * 删除挑战结果+详情记录
     */
    @RequiresPermissions("garbagesort:challengeResult:remove")
    @Log(title = "挑战结果+详情记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(challengeResultService.deleteChallengeResultByIds(ids));
    }
}
