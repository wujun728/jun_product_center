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
import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import com.ruoyi.workflow.service.IFlowHandleService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qixing.domain.OaPomsWorkmarksClaimExpense;
import com.ruoyi.qixing.service.IOaPomsWorkmarksClaimExpenseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 费用报销Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/expense")
public class OaPomsWorkmarksClaimExpenseController extends BaseController
{
    @Autowired
    private IOaPomsWorkmarksClaimExpenseService oaPomsWorkmarksClaimExpenseService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询费用报销列表
     */
    @PreAuthorize("@ss.hasPermi('system:expense:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaPomsWorkmarksClaimExpense oaPomsWorkmarksClaimExpense)
    {
        startPage();
        List<OaPomsWorkmarksClaimExpense> list = oaPomsWorkmarksClaimExpenseService.selectOaPomsWorkmarksClaimExpenseList(oaPomsWorkmarksClaimExpense);
        return getDataTable(list);
    }

    /**
     * 导出费用报销列表
     */
    @PreAuthorize("@ss.hasPermi('system:expense:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaPomsWorkmarksClaimExpense oaPomsWorkmarksClaimExpense)
    {
        List<OaPomsWorkmarksClaimExpense> list = oaPomsWorkmarksClaimExpenseService.selectOaPomsWorkmarksClaimExpenseList(oaPomsWorkmarksClaimExpense);
        ExcelUtil<OaPomsWorkmarksClaimExpense> util = new ExcelUtil<OaPomsWorkmarksClaimExpense>(OaPomsWorkmarksClaimExpense.class);
        util.exportExcel(response, list, "费用报销数据");
    }

    /**
     * 获取费用报销详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:expense:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(oaPomsWorkmarksClaimExpenseService.selectOaPomsWorkmarksClaimExpenseById(id));
    }

    /**
     * 新增费用报销
     */
    @PreAuthorize("@ss.hasPermi('system:expense:add')")
    @PostMapping
    public AjaxResult add(@RequestBody OaPomsWorkmarksClaimExpense oaPomsWorkmarksClaimExpense)
    {
        return toAjax(oaPomsWorkmarksClaimExpenseService.insertOaPomsWorkmarksClaimExpense(oaPomsWorkmarksClaimExpense));
    }

    /**
     * 修改费用报销
     */
    @PreAuthorize("@ss.hasPermi('system:expense:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody OaPomsWorkmarksClaimExpense oaPomsWorkmarksClaimExpense)
    {
        return toAjax(oaPomsWorkmarksClaimExpenseService.updateOaPomsWorkmarksClaimExpense(oaPomsWorkmarksClaimExpense));
    }

    /**
     * 删除费用报销
     */
    @PreAuthorize("@ss.hasPermi('system:expense:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(oaPomsWorkmarksClaimExpenseService.deleteOaPomsWorkmarksClaimExpenseByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<OaPomsWorkmarksClaimExpense> list = oaPomsWorkmarksClaimExpenseService.selectOaPomsWorkmarksClaimExpenseList(new OaPomsWorkmarksClaimExpense());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody OaPomsWorkmarksClaimExpense entity, String templateId)
    {
        oaPomsWorkmarksClaimExpenseService.insertOaPomsWorkmarksClaimExpense(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("OaPomsWorkmarksClaimExpense-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}